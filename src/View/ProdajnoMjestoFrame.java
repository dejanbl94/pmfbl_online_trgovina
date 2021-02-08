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

public class ProdajnoMjestoFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public ProdajnoMjestoFrame() {
		
		setToolbarIcon();
		
		panel = new JPanel();
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Dodaj prodajno mjesto "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
		gradLbl = new JLabel("Grad");
		drzavalbl = new JLabel("Drzava");
		adresaLbl = new JLabel("Adresa");
		telefonLbl = new JLabel("Telefon");
		gradTxt = new JTextField(20);
		drzavaTxt = new JTextField(20);
		adresaTxt = new JTextField(20);
		telefonTxt = new JTextField(20);
		updateMjestoBtn = new JButton("Dodaj");
		
		this.getContentPane().setLayout(new BorderLayout());
		adjust();
		add(BorderLayout.CENTER, panel);
		add(BorderLayout.SOUTH, updateMjestoBtn);
	}
	
	public void addNewLocationBtnListener(ActionListener listener) {
		this.updateMjestoBtn.addActionListener(listener);
	}
	
	public String getGradTxt() {
		return gradTxt.getText();
	}

	public String getDrzavaTxt() {
		return drzavaTxt.getText();
	}

	public String getAdresaTxt() {
		return adresaTxt.getText();
	}

	public void setAdresaTxt(JTextField adresaTxt) {
		this.adresaTxt = adresaTxt;
	}

	public String getTelefonTxt() {
		return telefonTxt.getText();
	}

	public void setTelefonTxt(JTextField telefonTxt) {
		this.telefonTxt = telefonTxt;
	}

	private void adjust() {
		setSize(350, 250);
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
		panel.add(gradLbl, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(gradTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(drzavalbl, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(drzavaTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(adresaLbl, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(adresaTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(telefonLbl, gbc);

		gbc.gridx = 2;
		gbc.gridy = 4;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(telefonTxt, gbc);
	}

	private void setToolbarIcon() {
		try {
			setIconImage(ImageIO.read(new File("src/resources/laptop.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private JPanel panel;
	private JButton updateMjestoBtn;
	private JLabel gradLbl, drzavalbl, adresaLbl, telefonLbl;
	private JTextField gradTxt, drzavaTxt, adresaTxt, telefonTxt;
}
