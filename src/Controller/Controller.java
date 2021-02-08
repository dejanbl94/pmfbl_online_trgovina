package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import Helper.jsonParse;
import Service.ArtikalService;
import Service.KupacService;
import Service.NarudzbaService;
import Service.ProdajnoMjestoService;
import Service.ProizvodService;
import Service.TrgovacService;
import View.AddTrgovacFrame;
import View.ConfirmOrderFrame;
import View.DetaljiNarudzbeFrame;
import View.InitialFrame;
import View.KorpaFrame;
import View.KupacFrame;
import View.LoginFrame;
import View.NewProductFrame;
import View.ProdajnoMjestoFrame;
import View.ProizvodiFrame;
import View.TrgovacFrame;

public class Controller {

	public Controller(InitialFrame frame) {
		super();

		// Setting initial frame.
		setInitialFrame(frame);
		getInitialFrame().addNavigateToLoginListener(new LoginBtnListener());
		getInitialFrame().addComboListener(new ComboListener());
		this.kupacService = new KupacService(new KupacDAO());
		// Registering our services.
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

				if (Controller.role == "Kupac") {
					DetaljiNarudzbeFrame artikliFrame = new DetaljiNarudzbeFrame();
					populateProizvodiTable(artikliFrame, Integer.parseInt(narudzbaId));

					artikliFrame
							.addDiscardOrderBtnListener(new DiscardOrderListener(Integer.parseInt(narudzbaId), row));
					String statusNarudzbe = target.getModel().getValueAt(row, 1).toString();
					if (statusNarudzbe.contains("na")) {
						artikliFrame.enableRemoveBtn();
					}
					artikliFrame.setVisible(true);
				} else if (Controller.role == "Trgovac") {
					String x = target.getModel().getValueAt(row, 1).toString();
					if (x.contains("na")) {
						getTrgovacFrame().getConfirmOrderButton().setEnabled(true);
						trgovacService.setNarudzbaId(Integer.parseInt(narudzbaId));
					} else {
						getTrgovacFrame().getConfirmOrderButton().setEnabled(false);
					}
				}
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

			if (Controller.role == "Kupac") {

				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
					// Get selected row.
					int row = target.getSelectedRow();
					// Prepare order details.
					String naziv = target.getModel().getValueAt(row, 0).toString();
					String opis = target.getModel().getValueAt(row, 1).toString();
					int proizvodId = Integer.parseInt(target.getModel().getValueAt(row, 3).toString());
					int kupacId = getKupacFrame().getId();
					// Dodajemo listu trgovaca koji mogu da isporuče narudžbu, ukoliko je mjesto iz
					// kojeg je kupac u listi prodajnih mjesta za datu drzavu.
					List<Integer> trgovciId = new ArrayList<Integer>();
					for (int i = 0; i < prodajnaMjesta.size(); i++) {
						if (prodajnaMjesta.get(i).getGrad().equalsIgnoreCase(getKupacFrame().getGradTxt())) {
							try {
								Trgovac trgovac = trgovacService
										.getByProdajnoMjesto(String.valueOf(prodajnaMjesta.get(i).getId()));
								trgovciId.add(trgovac.getId());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
					// Ukoliko nije moguce isporuciti u dati grad posalji narudzbu na sva mjesta u
					// drzavi kupca.
					if (trgovciId.isEmpty()) {
						for (int i = 0; i < prodajnaMjesta.size(); i++) {
							if (prodajnaMjesta.get(i).getDrzava().equalsIgnoreCase(getKupacFrame().getDrzavaTxt())) {
								try {
									Trgovac trgovac = trgovacService
											.getByProdajnoMjesto(String.valueOf(prodajnaMjesta.get(i).getId()));
									trgovciId.add(trgovac.getId());
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}

					String datumNarudzbe = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
					Double cijena = Double.parseDouble(target.getModel().getValueAt(row, 2).toString());
					int kolicina = 1;
					String napomena = "";
					proizvodService.getNaruceniProizvodi().add(new ProizvodDTO(kupacId, 0, trgovciId, proizvodId,
							kolicina, naziv, opis, datumNarudzbe, napomena, cijena));

				}
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
			String narudzbeJSON;
			try {
				// Ako ne postoji drzava dodajemo narudzbu za svakog trgovca, datum isporuke
				// oznacava da li je trgovac preuzeo narudzbu ili ne, tako da je za sada setovan
				// na NULL.
				List<ProizvodDTO> productsArticles = proizvodService.getNaruceniProizvodi();
				Narudzba narudzba = new Narudzba(productsArticles.get(0).getKupacId(), 0,
						productsArticles.get(0).getDatumNarudzbe(), null, null);
				narudzbaService.add(narudzba);

				/** JSON serialization/deserialization **/
				int id = narudzbaService.getLastId();
				List<ProizvodDTO> listaNarucenihProizvoda = proizvodService.getNaruceniProizvodi();
				for (var x : listaNarucenihProizvoda) {
					x.setNarudzbaId(id);
				}
				JSONOrders = jsonParse.parseAndWrite(listaNarucenihProizvoda, "narudzbe_na_cekanju");
				List<ProizvodDTO> proizvodi = jsonParse.readJSONToList(JSONOrders,
						new TypeReference<List<ProizvodDTO>>() {
						});
				narudzbaService.setNarudzbeNaCekanju(proizvodi);

				// Insert product order item for the particular order.
				for (int i = 0; i < proizvodi.size(); i++) {
					ArtikalDTO artikal = new ArtikalDTO(id, proizvodi.get(i).getProizvodId(),
							proizvodi.get(i).getKolicina(), proizvodi.get(i).getCijenaKomad());
					artikalService.insert(artikal);
				}
				JOptionPane.showMessageDialog(null,
						"Uspješno kreirana narudžba, uskoro ćete biti obavješteni o datumu isporuke.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
				if (Controller.role == "Kupac") {
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
				} else if (Controller.role == "Trgovac") {
					// Get the list of ordered items.

					ObjectMapper mapper = new ObjectMapper();
					mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

					List<ProizvodDTO> narudzbeNaCekanju = new ArrayList<ProizvodDTO>();
					try (InputStream fileStream = new FileInputStream("src/resources/narudzbe_na_cekanju.json")) {
						narudzbeNaCekanju = mapper.readValue(fileStream, new TypeReference<List<ProizvodDTO>>() {
						});
						System.out.println(narudzbeNaCekanju);
						
					} catch (JsonParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JsonMappingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					List<ProizvodDTO> s = narudzbaService.getNarudzbeNaCekanju();

					// Check if trgovac exists.
					if (trgovacService.trgovacExists(this.loginFrame.getKorisnickoIme(),
							this.loginFrame.getLozinka())) {
						// Get trgovac from the database.
						Trgovac trgovac = trgovacService.getByUsername(this.loginFrame.getKorisnickoIme());

						// Ako se logujemo kao trgovac koji moze da odobri narudzbu, azuriramo tabelu sa
						// njegovim ID-em za tu narudzbu i azuriramo datum isporuke.
						// U suprotnom samo prikazujemo narudzbe za tog trgovca.
						int nadjeniTrgovacId = 0;
						int narudzbaId = 0;
						boolean flag = false;
						if (!narudzbeNaCekanju.isEmpty()) {
							narudzbaId = narudzbeNaCekanju.get(0).getNarudzbaId();
							for (int i = 0; i < narudzbeNaCekanju.get(0).getTrgovciId().size(); i++) {
								if (narudzbeNaCekanju.get(0).getTrgovciId().get(i) == trgovac.getId()) {
									nadjeniTrgovacId = trgovac.getId();
									flag = true;
									break;
								}
							}
						}
						// Azuriraj narudzbu postavi ID odgovornog trgovca.
						if (flag) {
							narudzbaService.updateTrgovac(nadjeniTrgovacId, narudzbaId);
							// Obrisi završenu narudžbu.
							List<ProizvodDTO> newProducts = new ArrayList<ProizvodDTO>();
							for (var x : narudzbeNaCekanju) {
								if (x.getNarudzbaId() != narudzbaId) {
									newProducts.add(x);
								}
							}
							// Add new orders to the file.
							JSONOrders = jsonParse.parseAndWrite(newProducts, "narudzbe_na_cekanju");
						}
						TrgovacFrame trgovacFrame = new TrgovacFrame("Trgovac");
						setTrgovacFrame(trgovacFrame);
						// Dodaj listener za potvrdu narudzbe.
						trgovacFrame.setVisible(true);
						trgovacFrame.addConfirmOrderListener(new ConfirmOrderListener());
						trgovacFrame.addNewClerkButton(new NewClerkButtonListener());
						trgovacFrame.addNewProductListener(new AddProductListener());
						trgovacFrame.addNewLocationListener(new AddLocationListener());
						setTrgovacInfo(trgovacFrame, trgovac);
						this.loginFrame.setErrorMessage("");
						trgovacFrame.setTrgovacId(trgovac.getId());
						populateTableTrgovac(trgovacFrame);
						if (flag)
							JOptionPane.showMessageDialog(null, "Nove narudžbe na čekanju");
						this.loginFrame.refresh();
					} else {
						this.loginFrame.setErrorMessage("Trgovac nije pronađen");
						return;
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	// Potvrdi narudzbu.
	class ConfirmOrderListener implements ActionListener {

		public ConfirmOrderListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			ConfirmOrderFrame frame = new ConfirmOrderFrame();
			setConfirmOrderFrame(frame);
			frame.addShipOrderActionListener(new ShipOrderListener());
			frame.setVisible(true);

		}
	}

	// Posalji narudzbu.
	class ShipOrderListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String shippingDate = getConfirmOrderFrame().getShippingDate();
			String shippingNote = getConfirmOrderFrame().getShippingNote();
			int narudzbaId = trgovacService.getNarudzbaId();
			if (narudzbaService.setOrderForShipping(shippingDate, shippingNote, narudzbaId)) {
				JOptionPane.showMessageDialog(null, "Narudžba " + narudzbaId + " je uspješno poslata!");
			} else {
				JOptionPane.showMessageDialog(null, "Narudžba " + narudzbaId + " nije uspješno poslata!");
			}
		}
	}

	class AddLocationListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ProdajnoMjestoFrame prodajnoMjestoFrame = new ProdajnoMjestoFrame();
			setProdajnoMjestoFrame(prodajnoMjestoFrame);
			prodajnoMjestoFrame.setVisible(true);
			prodajnoMjestoFrame.addNewLocationBtnListener(new AddNewProdajnoMjestoListener());
		}
	}

	class AddNewProdajnoMjestoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (prodajnoMjestoService.add(getProdajnoMjestoFrame())) {
				JOptionPane.showMessageDialog(null, "Novo prodajno mjesto uspješno dodato!");
				// Get prodajna mjesta.
				prodajnaMjesta = prodajnoMjestoService.getAll();
			} else {
				JOptionPane.showMessageDialog(null, "Neuspješno dodavanje novog prodajnog mjesta!");
			}
		}
	}

	class NewClerkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			AddTrgovacFrame trgovacFrame = new AddTrgovacFrame();
			setAddTrgovacFrame(trgovacFrame);
			trgovacFrame.setVisible(true);
			trgovacFrame.addClerkActionListener(new RegisterClerkActionListener());
		}
	}

	class AddProductListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			NewProductFrame product = new NewProductFrame();
			setNewProductFrame(product);
			product.setVisible(true);
			product.btnProductAddActionListener(new AddNewProductListener());
		}
	}

	// Dodaj proizvod.
	class AddNewProductListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (proizvodService.add(getNewProductFrame())) {
				JOptionPane.showMessageDialog(null, "Novi proizvod uspješno dodat!");
			} else {
				JOptionPane.showMessageDialog(null, "Neuspješno dodavanje novog proizvoda!");
			}
		}
	}

	// Dodaj trgovca.
	class RegisterClerkActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			List<ProdajnoMjesto> mjesta = prodajnoMjestoService.getAll();
			int prodajnoId = 0;
			for (int i = 0; i < mjesta.size(); i++) {
				if (mjesta.get(i).getGrad().equalsIgnoreCase(getAddTrgovacFrame().getProdajnoMjestoTxt())) {
					prodajnoId = mjesta.get(i).getId();
					break;
				}
			}
			trgovacService.createClerk(getAddTrgovacFrame(), prodajnoId);
		}
	}

	class ComboListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				Controller.role = item.toString();
			}
			if (Controller.role == "Trgovac") {
				getInitialFrame().getRegisterBtn().setEnabled(false);
			} else {
				getInitialFrame().getRegisterBtn().setEnabled(true);
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
			} else {
				JOptionPane.showMessageDialog(null, "Neuspješno otkazivanje narudžbe");
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
		frame.getNarudzbePanel().addMouseListener(new MouseTableListener());
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

	/** Accessors / Mutators **/
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

	public void setConfirmOrderFrame(ConfirmOrderFrame frame) {
		this.confirmOrderFrame = frame;
	}

	public ConfirmOrderFrame getConfirmOrderFrame() {
		return this.confirmOrderFrame;
	}

	public void setAddTrgovacFrame(AddTrgovacFrame frame) {
		this.addTrgovacFrame = frame;
	}

	public AddTrgovacFrame getAddTrgovacFrame() {
		return this.addTrgovacFrame;
	}

	public void setNewProductFrame(NewProductFrame frame) {
		this.newProductFrame = frame;
	}

	public NewProductFrame getNewProductFrame() {
		return this.newProductFrame;
	}

	public void setProdajnoMjestoFrame(ProdajnoMjestoFrame frame) {
		this.prodajnoMjestoFrame = frame;
	}

	public ProdajnoMjestoFrame getProdajnoMjestoFrame() {
		return this.prodajnoMjestoFrame;
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
	private ConfirmOrderFrame confirmOrderFrame;
	private AddTrgovacFrame addTrgovacFrame;
	private ProdajnoMjestoFrame prodajnoMjestoFrame;
	private NewProductFrame newProductFrame;

	private String narudzbaId;
	private static String role = "";
	private String JSONOrders = "";

	private static List<ProdajnoMjesto> prodajnaMjesta = new ArrayList<ProdajnoMjesto>();
}
