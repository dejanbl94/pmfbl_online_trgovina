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

public class AddTrgovacFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public AddTrgovacFrame() {
		setToolbarIcon();
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Dodaj trgovca "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
		
		addClerkBtn = new JButton("Registracija");
		korisnickoLbl = new JLabel("Korisnicko ime: ");
		imeLbl = new JLabel("Ime: ");
		prezimeLbl = new JLabel("Prezime: ");
		lozinkaLbl = new JLabel("Lozinka: ");
		polLbl = new JLabel("Pol");
		telefonLbl = new JLabel("Telefon: ");
		emailLbl = new JLabel("Email: ");
		prodajnoMjestoLbl = new JLabel("Prodajno mjesto: ");
		
		korisnickoTxt = new JTextField(20);
		imeTxt = new JTextField(20);
		prezimeTxt = new JTextField(20);
		lozinkaTxt = new JTextField(20);
		polTxt = new JTextField(20);
		telefonTxt = new JTextField(20);
		emailTxt = new JTextField(20);
		prodajnoMjestoTxt = new JTextField(20);
		
		this.getContentPane().setLayout(new BorderLayout());
		adjust();
		add(BorderLayout.CENTER, panel);
		add(BorderLayout.SOUTH, addClerkBtn);
		
	}
	
	public void addClerkActionListener(ActionListener listener) {
		addClerkBtn.addActionListener(listener);
	}
	
	private void setToolbarIcon() {
		try {
			setIconImage(ImageIO.read(new File("src/resources/laptop.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private void adjust() {
		setSize(450, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		// Set layout.
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(korisnickoLbl, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(korisnickoTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(imeLbl, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(imeTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(prezimeLbl, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(prezimeTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(lozinkaLbl, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(lozinkaTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(polLbl, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(polTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(telefonLbl, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 6;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(telefonTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(emailLbl, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 7;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(emailTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(prodajnoMjestoLbl, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 8;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(prodajnoMjestoTxt, gbc);
	}
	
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JLabel getKorisnickoLbl() {
		return korisnickoLbl;
	}

	public void setKorisnickoLbl(JLabel korisnickoLbl) {
		this.korisnickoLbl = korisnickoLbl;
	}

	public JLabel getImeLbl() {
		return imeLbl;
	}

	public void setImeLbl(JLabel imeLbl) {
		this.imeLbl = imeLbl;
	}

	public JLabel getPrezimeLbl() {
		return prezimeLbl;
	}

	public void setPrezimeLbl(JLabel prezimeLbl) {
		this.prezimeLbl = prezimeLbl;
	}

	public JLabel getLozinkaLbl() {
		return lozinkaLbl;
	}

	public void setLozinkaLbl(JLabel lozinkaLbl) {
		this.lozinkaLbl = lozinkaLbl;
	}

	public JLabel getPolLbl() {
		return polLbl;
	}

	public void setPolLbl(JLabel polLbl) {
		this.polLbl = polLbl;
	}

	public JLabel getTelefonLbl() {
		return telefonLbl;
	}

	public void setTelefonLbl(JLabel telefonLbl) {
		this.telefonLbl = telefonLbl;
	}

	public JLabel getEmailLbl() {
		return emailLbl;
	}

	public void setEmailLbl(JLabel emailLbl) {
		this.emailLbl = emailLbl;
	}

	public JLabel getProdajnoMjestoLbl() {
		return prodajnoMjestoLbl;
	}

	public void setProdajnoMjestoLbl(JLabel prodajnoMjestoLbl) {
		this.prodajnoMjestoLbl = prodajnoMjestoLbl;
	}

	public String getKorisnickoTxt() {
		return korisnickoTxt.getText();
	}

	public void setKorisnickoTxt(JTextField korisnickoTxt) {
		this.korisnickoTxt = korisnickoTxt;
	}

	public String getImeTxt() {
		return imeTxt.getText();
	}

	public void setImeTxt(JTextField imeTxt) {
		this.imeTxt = imeTxt;
	}

	public String getPrezimeTxt() {
		return prezimeTxt.getText();
	}

	public void setPrezimeTxt(JTextField prezimeTxt) {
		this.prezimeTxt = prezimeTxt;
	}

	public String getLozinkaTxt() {
		return lozinkaTxt.getText();
	}

	public void setLozinkaTxt(JTextField lozinkaTxt) {
		this.lozinkaTxt = lozinkaTxt;
	}

	public String getPolTxt() {
		return polTxt.getText();
	}

	public void setPolTxt(JTextField polTxt) {
		this.polTxt = polTxt;
	}

	public String getTelefonTxt() {
		return telefonTxt.getText();
	}

	public void setTelefonTxt(JTextField telefonTxt) {
		this.telefonTxt = telefonTxt;
	}

	public String getEmailTxt() {
		return emailTxt.getText();
	}

	public void setEmailTxt(JTextField emailTxt) {
		this.emailTxt = emailTxt;
	}

	public String getProdajnoMjestoTxt() {
		return prodajnoMjestoTxt.getText();
	}

	public void setProdajnoMjestoTxt(JTextField prodajnoMjestoTxt) {
		this.prodajnoMjestoTxt = prodajnoMjestoTxt;
	}

	public JButton getAddClerkBtn() {
		return addClerkBtn;
	}

	public void setAddClerkBtn(JButton addClerkBtn) {
		this.addClerkBtn = addClerkBtn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private JPanel panel;
	private JLabel korisnickoLbl, imeLbl, prezimeLbl, lozinkaLbl, polLbl, telefonLbl, emailLbl, prodajnoMjestoLbl;
	private JTextField korisnickoTxt, imeTxt, prezimeTxt, lozinkaTxt, polTxt, telefonTxt, emailTxt, prodajnoMjestoTxt;
	private JButton addClerkBtn;
}
