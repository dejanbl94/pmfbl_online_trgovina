package View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TrgovacFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private NarudzbePanel panelNarudzbe;
	
	public TrgovacFrame(String title) {
		super();
		
		setToolbarIcon(); 
		trgovacPanel = new JPanel();
		panelNarudzbe = new NarudzbePanel();
		confirmOrderButton = new JButton("Potvrdi narudžbu");
		// Add login panel to the main frame.
		this.getContentPane().setLayout(new BorderLayout());
		add(BorderLayout.WEST, trgovacPanel);
		add(BorderLayout.EAST, panelNarudzbe);
		add(BorderLayout.SOUTH, confirmOrderButton);
		// Adjust the layout.
		adjust();
	}
	private void adjust() {
		setSize(900, 550);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		// Set layout.
		trgovacPanel.setLayout(new GridBagLayout());
		trgovacPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Pregled profila "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));

		// Create components.
		ime = new JLabel("Ime: ");
		prezime = new JLabel("Prezime: ");
		korisnicko = new JLabel("Korisnicko ime: ");
		pol = new JLabel("Pol: ");
		telefon = new JLabel("Telefon: ");
		email = new JLabel("Email: ");
		
		imeTxt = new JTextField(20);
		prezimeTxt = new JTextField(20);
		korisnickoTxt = new JTextField(20);
		polTxt = new JTextField(20);
		telefonTxt = new JTextField(20);
		emailTxt = new JTextField(20);

		// Add components.
		setGridBag(trgovacPanel);
	}
	
	public void addConfirmOrderListener(ActionListener listener) {
		this.confirmOrderButton.addActionListener(listener);
	}
	
	
	private void setToolbarIcon() {
		try {
			setIconImage(ImageIO.read(new File("src/resources/laptop.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void setGridBag(JPanel trgovacPanel) {
		// Add components to the layout.
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		trgovacPanel.add(ime, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		trgovacPanel.add(imeTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		trgovacPanel.add(prezime, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		trgovacPanel.add(prezimeTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		trgovacPanel.add(korisnicko, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		trgovacPanel.add(korisnickoTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		trgovacPanel.add(pol, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		trgovacPanel.add(polTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.PAGE_START;
		trgovacPanel.add(email, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.PAGE_START;
		trgovacPanel.add(emailTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.PAGE_START;
		trgovacPanel.add(telefon, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.PAGE_START;
		trgovacPanel.add(telefonTxt, gbc);
	}
	
	public JTextField getImeTxt() {
		return imeTxt;
	}

	public void setImeTxt(String ime) {
		this.imeTxt.setText(ime);
	}

	public JTextField getPrezimeTxt() {
		return prezimeTxt;
	}

	public void setPrezimeTxt(String prezime) {
		this.prezimeTxt.setText(prezime);
	}

	public JTextField getPolTxt() {
		return polTxt;
	}

	public void setAdresaTxt(String polTxt) {
		this.polTxt.setText(polTxt);
	}

	public JTextField getEmailTxt() {
		return emailTxt;
	}

	public void setEmailTxt(String emailTxt) {
		this.emailTxt.setText(emailTxt);
	}

	public JTextField getKorisnickoTxt() {
		return korisnickoTxt;
	}

	public void setKorisnickoTxt(String korisnickoTxt) {
		this.korisnickoTxt.setText(korisnickoTxt);
	}

	public JTextField getTelefonTxt() {
		return telefonTxt;
	}

	public void setTelefonTxt(String telefonTxt) {
		this.telefonTxt.setText(telefonTxt);
	}
	
	public JFrame getKupacFrame() {
		return this;
	}
	public void setTrgovacId(int trgovacId) {
		this.trgovacId = trgovacId;
	}
	
	public NarudzbePanel getNarudzbePanel() {
		return this.panelNarudzbe;
	}

	public int getId() {
		return this.trgovacId;
	}
	
	private JPanel trgovacPanel;
	private JLabel ime, prezime, korisnicko, lozinka, pol, email, telefon;
	private JTextField imeTxt, prezimeTxt, korisnickoTxt, polTxt, emailTxt, telefonTxt;
	private JButton confirmOrderButton;
	private int trgovacId;
}
