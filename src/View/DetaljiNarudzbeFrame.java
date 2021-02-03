package View;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class DetaljiNarudzbeFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public DetaljiNarudzbeFrame() {
		super();

		setToolbarIcon();

		artikliPanel = new ArtikliPanel();
		// Add login panel to the main frame.
		this.getContentPane().add(artikliPanel);
		// Adjust the layout.
		adjust();
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
}
