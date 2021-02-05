package View;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

public class KorpaFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public KorpaFrame() {
		setToolbarIcon();
		setLayout(new BorderLayout());
		panel = new KorpaPanel();
		btnOrder = new JButton("Naruči");
		add(BorderLayout.NORTH, panel);
		add(BorderLayout.SOUTH, btnOrder);
		adjust();
	}
	public void addOrderBtnListener(ActionListener listener) {
		this.btnOrder.addActionListener(listener);
	}

	private void setToolbarIcon() {
		try {
			setIconImage(ImageIO.read(new File("src/resources/laptop.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public KorpaPanel getKorpaTablePanel() {
		return this.panel;
	}
	
	private void adjust() {
		setSize(550, 545);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private KorpaPanel panel;
	private JButton btnOrder;
}
