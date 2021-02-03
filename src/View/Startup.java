package View;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

import Controller.MainController;

public class Startup {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
			} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(Startup.class.getName()).log(Level.SEVERE, null, ex);
			}
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {

				InitialFrame mainFrame = new InitialFrame("Online trgovina");
				KupacFrame frame = new KupacFrame("Profil");
				MainController controller = new MainController(mainFrame, frame);
				mainFrame.setSize(new Dimension(300, 120));
				mainFrame.setResizable(false);
				mainFrame.setLocationRelativeTo(null);
				mainFrame.setVisible(true);
				mainFrame.setDefaultLookAndFeelDecorated(true);
			}
			
		} );
	}

}
