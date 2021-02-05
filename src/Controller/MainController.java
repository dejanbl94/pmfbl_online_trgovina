package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import Database.DAO.ArtikalDAO;
import Database.DAO.KupacDAO;
import Database.DAO.NarudzbaDAO;
import Database.DAO.ProdajnoMjestoDAO;
import Database.DAO.ProizvodDAO;
import Database.DAO.TrgovacDAO;
import Entity.Kupac;
import Entity.Narudzba;
import Entity.ProdajnoMjesto;
import Entity.Proizvod;
import Entity.Trgovac;
import Entity.DTO.ArtikalDTO;
import Entity.DTO.ProizvodDTO;
import Service.ArtikalService;
import Service.KupacService;
import Service.NarudzbaService;
import Service.ProdajnoMjestoService;
import Service.ProizvodService;
import Service.TrgovacService;
import View.DetaljiNarudzbeFrame;
import View.InitialFrame;
import View.KorpaFrame;
import View.KupacFrame;
import View.LoginFrame;
import View.ProizvodiFrame;
import View.TrgovacFrame;

public class MainController {

	private static final Logger LOGGER = Logger.getLogger(MainController.class.getSimpleName());

	public MainController(InitialFrame frame) {
		super();

		setInitialFrame(frame);
		// setKupacFrame(kupacFrame);
		// setTrgovacFrame(trgovacFrame);
		getInitialFrame().addNavigateToLoginListener(new LoginBtnListener());
		getInitialFrame().addComboListener(new ComboListener());
		// getKupacFrame().addActionListener(new ProductsListener());
		this.kupacService = new KupacService(new KupacDAO());
		this.proizvodService = new ProizvodService(new ProizvodDAO());
		this.narudzbaService = new NarudzbaService(new NarudzbaDAO(), new ArtikalDAO());
		this.artikalService = new ArtikalService(new ArtikalDAO());
		this.trgovacService = new TrgovacService(new TrgovacDAO());
		this.prodajnoMjestoService = new ProdajnoMjestoService(new ProdajnoMjestoDAO());

		prodajnaMjesta = this.prodajnoMjestoService.getAll();
		role = getInitialFrame().getComboDefaultValue();
	}

	/** Event handling navigated by the controller. **/
	class MouseTableListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				JTable target = (JTable) e.getSource();
				// Get selected row.
				int row = target.getSelectedRow();
				narudzbaId = target.getModel().getValueAt(row, 4).toString();
				DetaljiNarudzbeFrame artikliFrame = new DetaljiNarudzbeFrame();
				populateProizvodiTable(artikliFrame, Integer.parseInt(narudzbaId));

				artikliFrame.addDiscardOrderBtnListener(new DiscardOrderListener(Integer.parseInt(narudzbaId), row));
				String statusNarudzbe = target.getModel().getValueAt(row, 1).toString();
				if (statusNarudzbe.contains("Na")) {
					artikliFrame.enableRemoveBtn();
				}
				artikliFrame.setVisible(true);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	class ProductsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setProizvodiFrame(new ProizvodiFrame());
			// setKorpaFrame(new KorpaFrame());
			proizvodi = proizvodService.getProizvodi();

			String[][] data = new String[proizvodi.size()][4];
			for (int i = 0; i < proizvodi.size(); i++) {
				data[i][0] = proizvodi.get(i).getNaziv();
				data[i][1] = proizvodi.get(i).getOpis() == null ? "-" : proizvodi.get(i).getOpis();
				data[i][2] = String.valueOf(proizvodi.get(i).getCijena());
				data[i][3] = String.valueOf(proizvodi.get(i).getId());
			}
			proizvodiFrame.getProizvodiTablePanel().setData(data);
			proizvodiFrame.getProizvodiTablePanel().setupTable();
			// Inside this mouse listener set the button to enabled and create new listener;
			proizvodiFrame.getProizvodiTablePanel().addProizvodiMouseListener(new OrdersMouseListener());
			proizvodiFrame.addCartBtnListener(new CartBtnListener());
			proizvodiFrame.setVisible(true);
		}
	}

	class CartBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setKorpaFrame(new KorpaFrame());
			getKorpaFrame().addOrderBtnListener(new OrderBtnListener());
			populateTableCart(getKorpaFrame());
			getKorpaFrame().setVisible(true);

		}

	}

	class OrdersMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				JTable target = (JTable) e.getSource();
				// Get selected row.
				int row = target.getSelectedRow();
				// Prepare order details.
				String naziv = target.getModel().getValueAt(row, 0).toString();
				String opis = target.getModel().getValueAt(row, 1).toString();
				int proizvodId = Integer.parseInt(target.getModel().getValueAt(row, 3).toString());
				int kupacId = getKupacFrame().getId();
				ProdajnoMjesto matching = prodajnaMjesta.stream()
						.filter(x -> x.getGrad().equalsIgnoreCase(getKupacFrame().getGradTxt())).findFirst()
						.orElseGet(() -> null);
				int trgovacId = matching == null ? 0 : matching.getId();
				String datumNarudzbe = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				Double cijena = Double.parseDouble(target.getModel().getValueAt(row, 2).toString());
				int kolicina = 1;
				String napomena = "";
				proizvodService.getNaruceniProizvodi().add(new ProizvodDTO(kupacId, trgovacId, proizvodId, kolicina,
						naziv, opis, datumNarudzbe, napomena, cijena));

			}

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	class LoginBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LoginFrame loginFrame = new LoginFrame("Prijava");
			loginFrame.setVisible(true);
			loginFrame.addLoginBtnListener(new LoginListener(loginFrame));
		}
	}

	class OrderBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String json = writeOrdersToJSON(proizvodService.getNaruceniProizvodi());
			List<ProizvodDTO> proizvodi = readJSONToList(json);
			for (int i = 0; i < proizvodi.size(); i++) {
				System.out.println(proizvodi.get(i).toString());
			}
		}
	}

	/** User role control. **/
	class LoginListener implements ActionListener {

		private LoginFrame loginFrame;

		public LoginListener(LoginFrame loginFrame) {
			this.loginFrame = loginFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			try {
				if (MainController.role == "Kupac") {
					if (kupacService.kupacExists(this.loginFrame.getKorisnickoIme(), this.loginFrame.getLozinka())) {
						KupacFrame kupacFrame = new KupacFrame("Kupac");
						setKupacFrame(kupacFrame);
						kupacFrame.addActionListener(new ProductsListener());
						kupacFrame.setVisible(true);
						Kupac kupac = kupacService.getByUsername(this.loginFrame.getKorisnickoIme());
						setKupacInfo(kupacFrame, kupac);
						// getProizvodiFrame().addOrderBtnListener(new OrderBtnListener());
						kupacFrame.setKupacId(kupac.getId());
						populateTable(kupacFrame);
						this.loginFrame.refresh();
						this.loginFrame.setErrorMessage("");
					} else {
						this.loginFrame.setErrorMessage("Kupac nije pronađen");
						return;
					}
				} else if (MainController.role == "Trgovac") {
					if (trgovacService.trgovacExists(this.loginFrame.getKorisnickoIme(),
							this.loginFrame.getLozinka())) {
						TrgovacFrame trgovacFrame = new TrgovacFrame("Trgovac");
						setTrgovacFrame(trgovacFrame);
						trgovacFrame.setVisible(true);
						Trgovac trgovac = trgovacService.getByUsername(this.loginFrame.getKorisnickoIme());
						setTrgovacInfo(trgovacFrame, trgovac);
						this.loginFrame.setErrorMessage("");
						trgovacFrame.setTrgovacId(trgovac.getId());
						populateTableTrgovac(trgovacFrame);
						this.loginFrame.refresh();
					} else {
						this.loginFrame.setErrorMessage("Trgovac nije pronađen");
						return;
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	class ComboListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				MainController.role = item.toString();
			}

		}

	}

	class DiscardOrderListener implements ActionListener {

		private int narudzbaId, row;

		public DiscardOrderListener(int narudzbaId, int row) {
			this.narudzbaId = narudzbaId;
			this.row = row;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (narudzbaService.delete(narudzbaId)) {
				JOptionPane.showMessageDialog(null, "Narudžba uspješno otkazana");
				getKupacFrame().getNarudzbePanel().refresh(row);
				populateTable(getKupacFrame());
			}

		}
	}

	/** Helper methods **/
	private void populateTable(KupacFrame frame) {
		listaNarudzbi = getAll(frame.getId());
		String[][] data = new String[listaNarudzbi.size()][5];
		for (int i = 0; i < listaNarudzbi.size(); i++) {
			listaArtikala = getArtikli(listaNarudzbi.get(i).getId());
			double sum = listaArtikala.stream().mapToDouble(ArtikalDTO::getCijenaKomad).sum();
			data[i][0] = listaNarudzbi.get(i).getDatumNarudzbe();
			data[i][1] = listaNarudzbi.get(i).getDatumIsporuke() == null ? "na čekanju..."
					: listaNarudzbi.get(i).getDatumIsporuke();
			data[i][2] = listaNarudzbi.get(i).getNapomena() == null ? "-" : listaNarudzbi.get(i).getNapomena();
			data[i][3] = String.valueOf(sum);
			data[i][4] = String.valueOf(listaNarudzbi.get(i).getId());
		}
		frame.getNarudzbePanel().setData(data);
		frame.getNarudzbePanel().setupTable();
		frame.getNarudzbePanel().addMouseListener(new MouseTableListener());
	}

	private void populateProizvodiTable(DetaljiNarudzbeFrame frame, int narudzbaId) {
		listaProizvoda = proizvodService.getAll(narudzbaId);
		String[][] data = new String[listaProizvoda.size()][5];
		for (int i = 0; i < listaProizvoda.size(); i++) {
			data[i][0] = listaProizvoda.get(i).getNaziv();
			data[i][1] = String.valueOf(listaProizvoda.get(i).getKolicina());
			data[i][2] = String.valueOf(listaProizvoda.get(i).getCijena());
			data[i][3] = listaProizvoda.get(i).getOpis() == null ? "-" : listaProizvoda.get(i).getOpis();
		}
		frame.getArtikliPanel().setData(data);
		frame.getArtikliPanel().setupTable();

	}

	private void populateTableTrgovac(TrgovacFrame frame) {
		listaNarudzbi = getAllForTrgovac(frame.getId());
		String[][] data = new String[listaNarudzbi.size()][5];
		for (int i = 0; i < listaNarudzbi.size(); i++) {
			listaArtikala = getArtikli(listaNarudzbi.get(i).getId());
			double sum = listaArtikala.stream().mapToDouble(ArtikalDTO::getCijenaKomad).sum();
			data[i][0] = listaNarudzbi.get(i).getDatumNarudzbe();
			data[i][1] = listaNarudzbi.get(i).getDatumIsporuke() == null ? "na čekanju..."
					: listaNarudzbi.get(i).getDatumIsporuke();
			data[i][2] = listaNarudzbi.get(i).getNapomena() == null ? "-" : listaNarudzbi.get(i).getNapomena();
			data[i][3] = String.valueOf(sum);
			data[i][4] = String.valueOf(listaNarudzbi.get(i).getId());
		}
		frame.getNarudzbePanel().setData(data);
		frame.getNarudzbePanel().setupTable();
		// kupacFrame.getNarudzbePanel().addMouseListener(new MouseTableListener());
	}

	private void populateTableCart(KorpaFrame frame) {
		List<ProizvodDTO> proizvodDTOList = proizvodService.getNaruceniProizvodi();
		String[][] data = new String[proizvodDTOList.size()][4];
		for (int i = 0; i < proizvodDTOList.size(); i++) {
			data[i][0] = proizvodDTOList.get(i).getNaziv();
			data[i][1] = proizvodDTOList.get(i).getOpis();
			data[i][2] = String.valueOf(proizvodDTOList.get(i).getCijenaKomad());
			data[i][3] = String.valueOf(proizvodDTOList.get(i).getProizvodId());
		}
		frame.getKorpaTablePanel().setData(data);
		frame.getKorpaTablePanel().setupTable();

	}

	private String writeOrdersToJSON(List<ProizvodDTO> naruceniProizvodi) {
		ObjectMapper objectMapper = new ObjectMapper();
		// Set pretty printing of json
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		//1. Convert List of Person objects to JSON
        String json;
		try {
			json = objectMapper.writeValueAsString(naruceniProizvodi);
	        objectMapper.writeValue(Paths.get("src/resources/narudzbe.json").toFile(), json);
	        return json;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private List<ProizvodDTO> readJSONToList(String jsonString) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<ProizvodDTO> participantJsonList = objectMapper.readValue(jsonString, new TypeReference<List<ProizvodDTO>>(){});
			return participantJsonList;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean exists(String username, char[] charedPassword) {
		try {
			return kupacService.kupacExists(username, charedPassword);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return false;
	}

	/** Accessors **/
	public List<Narudzba> getAll(int kupacId) {
		listaNarudzbi = narudzbaService.getAll(kupacId, "Kupac");
		return listaNarudzbi;
	}

	public List<Narudzba> getAllForTrgovac(int kupacId) {
		listaNarudzbi = narudzbaService.getAll(kupacId, "Trgovac");
		return listaNarudzbi;
	}

	public List<ArtikalDTO> getArtikli(int narudzbaId) {
		listaArtikala = narudzbaService.getAllArtikalNarudzbe(narudzbaId);
		return listaArtikala;
	}

	public void setInitialFrame(InitialFrame frame) {
		this.initialFrame = frame;
	}

	public InitialFrame getInitialFrame() {
		return this.initialFrame;
	}

	public void setProizvodiFrame(ProizvodiFrame frame) {
		this.proizvodiFrame = frame;
	}

	public ProizvodiFrame getProizvodiFrame() {
		return this.proizvodiFrame;
	}

	public void setKorpaFrame(KorpaFrame frame) {
		this.korpaFrame = frame;
	}

	public KorpaFrame getKorpaFrame() {
		return this.korpaFrame;
	}

	public void setKupacFrame(KupacFrame frame) {
		this.kupacFrame = frame;
	}

	public KupacFrame getKupacFrame() {
		return this.kupacFrame;
	}

	public void setTrgovacFrame(TrgovacFrame frame) {
		this.trgovacFrame = frame;
	}

	public TrgovacFrame getTrgovacFrame() {
		return this.trgovacFrame;
	}

	private void setKupacInfo(KupacFrame kupacFrame, Kupac kupac) {
		kupacFrame.setIme(kupac.getIme());
		kupacFrame.setPrezimeTxt(kupac.getPrezime());
		kupacFrame.setTelefonTxt(kupac.getTelefon());
		kupacFrame.setGradTxt(kupac.getGrad());
		kupacFrame.setDrzavaTxt(kupac.getDrzava());
		kupacFrame.setAdresaTxt(kupac.getAdresa());
		kupacFrame.setEmailTxt(kupac.getEmail());
		kupacFrame.setTelefonTxt(kupac.getTelefon());
		kupacFrame.setPostanskiBrojTxt(kupac.getPostanskiBroj());
	}

	private void setTrgovacInfo(TrgovacFrame trgovacFrame, Trgovac trgovac) {
		trgovacFrame.setKorisnickoTxt(trgovac.getKorisnickoIme());
		trgovacFrame.setImeTxt(trgovac.getIme());
		trgovacFrame.setPrezimeTxt(trgovac.getPrezime());
		trgovacFrame.setTelefonTxt(trgovac.getTelefon());
		trgovacFrame.setEmailTxt(trgovac.getEmail());
	}

	private List<Narudzba> listaNarudzbi = new ArrayList<Narudzba>();
	private List<ArtikalDTO> listaArtikala = new ArrayList<ArtikalDTO>();
	private List<Proizvod> listaProizvoda;
	private List<Proizvod> proizvodi;

	private KupacService kupacService;
	private NarudzbaService narudzbaService;
	private ProizvodService proizvodService;
	private ArtikalService artikalService;
	private TrgovacService trgovacService;
	private ProdajnoMjestoService prodajnoMjestoService;

	private InitialFrame initialFrame;
	private KupacFrame kupacFrame;
	private ProizvodiFrame proizvodiFrame;
	private TrgovacFrame trgovacFrame;
	private KorpaFrame korpaFrame;

	private String narudzbaId;
	private static String role = "";

	private static List<ProdajnoMjesto> prodajnaMjesta = new ArrayList<ProdajnoMjesto>();
}
