package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import Database.DAO.ArtikalDAO;
import Database.DAO.KupacDAO;
import Database.DAO.NarudzbaDAO;
import Database.DAO.ProizvodDAO;
import Entity.ArtikalNarudzbe;
import Entity.Kupac;
import Entity.Narudzba;
import Entity.Proizvod;
import Service.KupacService;
import Service.NarudzbaService;
import Service.ProizvodService;
import View.DetaljiNarudzbeFrame;
import View.InitialFrame;
import View.KupacFrame;
import View.LoginFrame;

public class MainController extends BaseController {

	// Instanciramo singleton logger.
	private static final Logger LOGGER = Logger.getLogger(KupacController.class.getSimpleName());

	private KupacService kupacService;
	private NarudzbaService narudzbaService;
	private ProizvodService proizvodService;

	private InitialFrame initialFrame;
	private KupacFrame kupacFrame;
	private DetaljiNarudzbeFrame narudzbaFrame;

	public MainController(InitialFrame frame, KupacFrame kupacFrame) {
		super();
		setInitialFrame(frame);
		setKupacFrame(kupacFrame);
		getInitialFrame().addNavigateToLoginListener(new NavigateToLoginListener());
		getInitialFrame().addComboListener(new NavigateToLoginListener());
		this.kupacService = new KupacService(new KupacDAO());
		this.proizvodService = new ProizvodService(new ProizvodDAO());
		this.narudzbaService = new NarudzbaService(new NarudzbaDAO(), new ArtikalDAO());
	}

	public boolean exists(String username, char[] charedPassword) {
		try {
			return kupacService.kupacExists(username, charedPassword);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return false;
	}

	public List<Narudzba> getAll(int kupacId) {
		listaNarudzbi = narudzbaService.getAll(kupacId);
		return listaNarudzbi;
	}

	public List<ArtikalNarudzbe> getArtikli(int narudzbaId) {
		listaArtikala = narudzbaService.getAllArtikalNarudzbe(narudzbaId);
		return listaArtikala;
	}

	public void setInitialFrame(InitialFrame frame) {
		this.initialFrame = frame;
	}

	public InitialFrame getInitialFrame() {
		return this.initialFrame;
	}

	public void setKupacFrame(KupacFrame frame) {
		this.kupacFrame = frame;
	}

	public KupacFrame getKupacFrame() {
		return this.kupacFrame;
	}

	class MouseTableListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println(e.getID());
			if (e.getClickCount() == 1) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				String narudzbaID = target.getModel().getValueAt(row, 4).toString();
				System.out.println(narudzbaID);
				DetaljiNarudzbeFrame artikliFrame = new DetaljiNarudzbeFrame();
				populateProizvodiTable(artikliFrame, Integer.parseInt(narudzbaID));
				
				artikliFrame.addDiscardOrderBtnListener(new DiscardOrderListener(Integer.parseInt(narudzbaID), row));
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

	// Event handling classes. Decoupling controller from UI.
	class NavigateToLoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LoginFrame loginFrame = new LoginFrame("Prijava");
			loginFrame.setVisible(true);
			loginFrame.addLoginBtnListener(new LoginListener(loginFrame));
		}
	}

	class LoginListener implements ActionListener {

		private LoginFrame loginFrame;

		public LoginListener(LoginFrame loginFrame) {
			this.loginFrame = loginFrame;

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				// Check if kupac exists.
				if (kupacService.kupacExists(this.loginFrame.getKorisnickoIme(), this.loginFrame.getLozinka())) {
					// kupacFrame = new KupacFrame("Profile", table);
					kupacFrame.setVisible(true);
					// Get kupac.
					Kupac kupac = kupacService.getByUsername(this.loginFrame.getKorisnickoIme());
					setKupacInfo(kupacFrame, kupac);
					kupacFrame.setKupacId(kupac.getId());
					populateTable(kupacFrame);
					this.loginFrame.refresh();
				} else {
					this.loginFrame.setErrorMessage("Kupac nije pronađen");
					return;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	class ComboListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

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
			if(narudzbaService.delete(narudzbaId)) {
				JOptionPane.showMessageDialog(null, "Narudžba uspješno otkazana");
				getKupacFrame().getNarudzbePanel().refresh(row);
				populateTable(getKupacFrame());
			}
			
		}
	}

	private void populateTable(KupacFrame frame) {
		listaNarudzbi = getAll(frame.getId());
		String[][] data = new String[listaNarudzbi.size()][5];
		for (int i = 0; i < listaNarudzbi.size(); i++) {
			listaArtikala = getArtikli(listaNarudzbi.get(i).getId());
			double sum = listaArtikala.stream().mapToDouble(ArtikalNarudzbe::getCijenaPoKomadu).sum();
			data[i][0] = listaNarudzbi.get(i).getDatumNarudzbe();
			data[i][1] = listaNarudzbi.get(i).getDatumIsporuke() == null ? "Na čekanju..."
					: listaNarudzbi.get(i).getDatumIsporuke();
			data[i][2] = listaNarudzbi.get(i).getNapomena() == null ? "-" : listaNarudzbi.get(i).getNapomena();
			data[i][3] = String.valueOf(sum);
			data[i][4] = String.valueOf(listaNarudzbi.get(i).getId());
		}
		frame.getNarudzbePanel().setData(data);
		frame.getNarudzbePanel().setupTable();
		kupacFrame.getNarudzbePanel().addMouseListener(new MouseTableListener());
	}
	
	private void populateProizvodiTable(DetaljiNarudzbeFrame frame, int narudzbaId) {
		listaProizvoda = proizvodService.getAll(narudzbaId);
		String[][] data = new String[listaProizvoda.size()][4];
		for (int i = 0; i < listaProizvoda.size(); i++) {
			data[i][0] = listaProizvoda.get(i).getNaziv();
			data[i][1] = String.valueOf(listaProizvoda.get(i).getKolicina());
			data[i][2] = String.valueOf(listaProizvoda.get(i).getCijena());
			data[i][3] = listaProizvoda.get(i).getOpis();
		}
		frame.getArtikliPanel().setData(data);
		frame.getArtikliPanel().setupTable();
		
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

	private List<Narudzba> listaNarudzbi;
	private List<ArtikalNarudzbe> listaArtikala;
	private List<Proizvod> listaProizvoda;
}
