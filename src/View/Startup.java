package View;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatLightLaf;

import Controller.Controller;

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

				InitialFrame main = new InitialFrame("Webshop");
				new Controller(main);
				main.setSize(new Dimension(300, 120));
				main.setResizable(false);
				main.setLocationRelativeTo(null);
				main.setVisible(true);
				JFrame.setDefaultLookAndFeelDecorated(true);
			}
			
		} );
	}

}
