package View;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.KupacController;
import Entity.Kupac;

public class KupacFrame extends JFrame {
	private static final long serialVersionUID = -5049402758510944027L;
	
	private KupacController kupacController;
	
	public KupacFrame(String title, String username, KupacController controller) {
		super();
		
		setToolbarIcon(); 
		
		
		kupacPanel = new JPanel();
		// Set controller.
		setKupacController(controller);
		// Add login panel to the main frame.
		this.getContentPane().add(BorderLayout.LINE_START, kupacPanel);
		// Adjust the layout.
		adjust();
		
		// Populate fields.
		Kupac kupac = getKupacController().getByUsername(username);

	}
	
	private void adjust() {
		setSize(900, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// Set layout.
		kupacPanel.setLayout(new GridBagLayout());
		kupacPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Pregled profila "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));

		// Create components.
		ime = new JLabel("Ime: ");
		prezime = new JLabel("Prezime: ");
		grad = new JLabel("Grad: ");
		drzava = new JLabel("Drzava: ");
		adresa = new JLabel("Adresa: ");
		email = new JLabel("Email: ");
		postanskiBroj = new JLabel("Postanski broj: ");
		telefon = new JLabel("Telefon");
		
		imeTxt = new JTextField(20);
		prezimeTxt = new JTextField(20);
		gradTxt = new JTextField(20);
		drzavaTxt = new JTextField(20);
		adresaTxt = new JTextField(20);
		emailTxt = new JTextField(20);
		postanskiBrojTxt = new JTextField(20);
		telefonTxt = new JTextField(20);

		// Add components.
		setGridBag(kupacPanel);
	}
	
	private void setToolbarIcon() {
		try {
			setIconImage(ImageIO.read(new File("src/resources/laptop.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public KupacController getKupacController() {
		return this.kupacController;
	}

	public void setKupacController(KupacController kupacController) {
		this.kupacController = kupacController;
	}
	
	private void setGridBag(JPanel loginPanel) {
		// Add components to the layout.
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		loginPanel.add(ime, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		loginPanel.add(imeTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		loginPanel.add(prezime, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		loginPanel.add(prezimeTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(adresa, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(adresaTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(grad, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(gradTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(drzava, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(drzavaTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(email, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(emailTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(postanskiBroj, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(postanskiBrojTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(telefon, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.PAGE_START;
		loginPanel.add(telefonTxt, gbc);
	}
	
	public void setIme(String ime) {
		this.ime.setText(ime);
	}
	
	private JPanel kupacPanel;
	private JLabel ime, prezime, grad, adresa, drzava, email, postanskiBroj, telefon;
	private JTextField imeTxt, prezimeTxt, gradTxt, drzavaTxt, adresaTxt, emailTxt, postanskiBrojTxt, telefonTxt;
}
