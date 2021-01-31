package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Controller.KupacController;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private KupacController loginController;

	public LoginFrame(String title, KupacController controller) {
		super();
		
		setToolbarIcon(); 
		
		loginPanel = new JPanel();
		// Set controller.
		setLoginControler(controller);
		// Add login panel to the main frame.
		this.getContentPane().add(loginPanel);
		// Adjust the layout.
		adjust();

	}

	private void adjust() {
		setSize(400, 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// Set layout.
		loginPanel.setLayout(new GridBagLayout());
		loginPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Prijavi se "),
				BorderFactory.createEmptyBorder(15, 15, 15, 15)));

		// Create components.
		korisnickoImeLabel = new JLabel("Korisnicko ime: ");
		lozinkaLabel = new JLabel("Lozinka: ");
		korisnickoImeTxt = new JTextField(20);
		lozinkaTxt = new JPasswordField(20);
		loginBtn = new JButton("Prijava");
		this.loginBtn.setEnabled(false);

		// Add components.
		setGridBag(loginPanel);

		// Add event listeners.
		loginBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (getLoginController().exists(korisnickoImeTxt.getText(), lozinkaTxt.getPassword())) {
					new KupacFrame("Profil", korisnickoImeTxt.getText(), new KupacController(new KupacFrame())).setVisible(true);
					refresh();
				}
			}

		});

		// Add document listeners.
		addDocumentListeners();
	}

	public KupacController getLoginController() {
		return this.loginController;
	}

	public void setLoginControler(KupacController loginController) {
		this.loginController = loginController;
	}
	
	public String getUsername() {
		return korisnickoImeTxt.getText();
	}

	public char[] getPassword() {
		return lozinkaTxt.getPassword();
	}

	private void addDocumentListeners() {
		this.korisnickoImeTxt.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				validateCredentials();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				validateCredentials();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				validateCredentials();
			}

		});
	}
	
	private void validateCredentials() {
		if (this.korisnickoImeTxt.getText().equals("")) {
			this.loginBtn.setEnabled(false);
		} else {
			this.loginBtn.setEnabled(true);
		}
	}

	// Refresh form.
	private void refresh() {
		this.korisnickoImeTxt.setText("");
		this.lozinkaTxt.setText("");
	}

	private void setGridBag(JPanel loginPanel) {
		// Add components to the layout.
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.weightx = 1;

		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		loginPanel.add(korisnickoImeLabel, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		loginPanel.add(korisnickoImeTxt, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		loginPanel.add(lozinkaLabel, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		loginPanel.add(lozinkaTxt, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.CENTER;
		loginPanel.add(loginBtn, gbc);
	}
	
	private void setToolbarIcon() {
		try {
			setIconImage(ImageIO.read(new File("src/resources/laptop.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private JLabel korisnickoImeLabel, lozinkaLabel;
	private JTextField korisnickoImeTxt;
	private JPasswordField lozinkaTxt;
	private JButton loginBtn;
	private JPanel loginPanel;
}