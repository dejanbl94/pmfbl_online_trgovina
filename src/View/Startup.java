package View;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
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

				InitialFrame main = new InitialFrame("Online trgovina");
				KupacFrame kupac = new KupacFrame("Profil");
				TrgovacFrame trgovac = new TrgovacFrame("Trgovac");
				MainController controller = new MainController(main, kupac, trgovac);
				main.setSize(new Dimension(300, 120));
				main.setResizable(false);
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				main.setDefaultLookAndFeelDecorated(true);
			}
			
		} );
	}

}
