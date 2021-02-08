package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

public class InitialFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton loginBtn;
	private JButton registerBtn;
	@SuppressWarnings("rawtypes")
	private JComboBox combo;
	private LoginFrame loginFrame;
	static final String[] comboItems = { "Kupac", "Trgovac" };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public InitialFrame(String title) {
		super(title);

		setToolbarIcon();

		// Set layout
		setLayout(new GridBagLayout());

		// Create components
		loginBtn = new JButton("Prijava");
		registerBtn = new JButton("Registracija");
		combo = new JComboBox(comboItems);
		combo.setSelectedIndex(0);

		// Add components to the layout
		setGridBag();
	}

	public JButton getRegisterBtn() {
		return registerBtn;
	}

	public void setRegisterBtn(JButton registerBtn) {
		this.registerBtn = registerBtn;
	}

	// Register listeners, handling in the controller.
	public void addNavigateToLoginListener(ActionListener actionListener) {
		this.loginBtn.addActionListener(actionListener);
	}

	public void addComboListener(ItemListener itemListener) {
		this.combo.addItemListener(itemListener);
	}

	public LoginFrame getLoginFrame() {
		return this.loginFrame;
	}
	
	public String getComboDefaultValue() {
		return (String) combo.getSelectedItem();
	}

	private void setGridBag() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3, 3, 3, 3);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(combo, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		add(loginBtn, gbc);

		gbc.gridx = 3;
		gbc.gridy = 1;
		add(registerBtn, gbc);
	}

	private void setToolbarIcon() {
		try {
			setIconImage(ImageIO.read(new File("src/resources/laptop.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
