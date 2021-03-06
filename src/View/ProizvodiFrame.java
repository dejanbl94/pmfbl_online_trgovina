package View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ProizvodiFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public ProizvodiFrame() {
		setToolbarIcon();
		setLayout(new BorderLayout());
		panel = new ProizvodiPanel();
		btnKorpa = new JButton("Pregledaj korpu");
		add(BorderLayout.NORTH, panel);
		add(BorderLayout.SOUTH, btnKorpa);
		adjust();
	}
	
	public void addCartBtnListener(ActionListener listener) {
		this.btnKorpa.addActionListener(listener);
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
		setSize(600, 545);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	public ProizvodiPanel getProizvodiTablePanel() {
		return this.panel;
	}
	private ProizvodiPanel panel;
	private JButton btnKorpa;
}
