package View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class DetaljiNarudzbeFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public DetaljiNarudzbeFrame() {
		super();

		setToolbarIcon();

		setLayout(new BorderLayout());
		artikliPanel = new ArtikliPanel();
		buttonOtkazi = new JButton("Otkaži narudžbu");
		buttonOtkazi.setEnabled(false);
		// Add login panel to the main frame.
		add(BorderLayout.NORTH, artikliPanel);
		add(BorderLayout.SOUTH, buttonOtkazi);
		// Adjust the layout.
		adjust();
	}
	
	public void addDiscardOrderBtnListener(ActionListener listener) {
		this.buttonOtkazi.addActionListener(listener);
	}
	
	public void enableRemoveBtn() {
		buttonOtkazi.setEnabled(true);
	}

	private void adjust() {
		setSize(550, 550);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public ArtikliPanel getArtikliPanel() {
		return this.artikliPanel;
	}

	private void setToolbarIcon() {
		try {
			setIconImage(ImageIO.read(new File("src/resources/laptop.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private ArtikliPanel artikliPanel;
	private JButton buttonOtkazi;
}
