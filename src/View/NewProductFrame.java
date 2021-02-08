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

public class NewProductFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public NewProductFrame() {
		setToolbarIcon();
		
		panel = new JPanel();
		btnAdd = new JButton("Dodaj proizvod");
		nazivLbl = new JLabel("Naziv");
		cijenaLbl = new JLabel("Cijena");
		opisLbl = new JLabel("Opis");
		nazivTxt = new JTextField(20);
		cijenaTxt = new JTextField(20);
		opisTxt = new JTextField(20);
		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Dodaj proizvod "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));
		
		this.getContentPane().setLayout(new BorderLayout());
		adjust();
		add(BorderLayout.CENTER, panel);
		add(BorderLayout.SOUTH, btnAdd);
	}
	
	private void adjust() {
		setSize(350, 250);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(5, 5, 5, 5);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(nazivLbl, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(nazivTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(opisLbl, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(opisTxt, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(cijenaLbl, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.PAGE_START;
		panel.add(cijenaTxt, gbc);
	}
	
	public void btnProductAddActionListener(ActionListener listener) {
		this.btnAdd.addActionListener(listener);
	}

	public String getNazivTxt() {
		return nazivTxt.getText();
	}

	public void setNazivTxt(JTextField nazivTxt) {
		this.nazivTxt = nazivTxt;
	}

	public String getOpisTxt() {
		return opisTxt.getText();
	}

	public void setOpisTxt(JTextField opisTxt) {
		this.opisTxt = opisTxt;
	}

	public String getCijenaTxt() {
		return cijenaTxt.getText();
	}

	public void setCijenaTxt(JTextField cijenaTxt) {
		this.cijenaTxt = cijenaTxt;
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
	private JButton btnAdd;
	private JLabel nazivLbl, opisLbl, cijenaLbl;
	private JTextField nazivTxt, opisTxt, cijenaTxt;
}
