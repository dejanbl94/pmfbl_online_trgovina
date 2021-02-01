package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import CustomExceptions.BlankInputException;
import Database.DAO.KupacDAO;
import Service.KupacService;
import View.InitialFrame;
import View.KupacFrame;
import View.LoginFrame;

public class LoginController extends BaseController {

	// Instanciramo singleton logger.
	private static final Logger LOGGER = Logger.getLogger(KupacController.class.getSimpleName());

	private KupacService kupacService;
	
	private InitialFrame initialFrame;

	public LoginController(InitialFrame frame) {
		super();
		setInitialFrame(frame);
		getInitialFrame().addNavigateToLoginListener(new NavigateToLoginListener());
		getInitialFrame().addComboListener(new NavigateToLoginListener());
		this.kupacService = new KupacService(new KupacDAO());
	}

	public void setInitialFrame(InitialFrame frame) {
		this.initialFrame = frame;
	}

	public InitialFrame getInitialFrame() {
		return this.initialFrame;
	}

	// Event handling classes. Decoupling controller from UI.
	class NavigateToLoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LoginFrame loginFrame = new LoginFrame("Prijava");
			loginFrame.setVisible(true);
			loginFrame.addLoginBtnListener(new LoginListener(loginFrame));
		}
	}

	class LoginListener implements ActionListener {

		private LoginFrame loginFrame;
		
		public LoginListener(LoginFrame loginFrame) {
			this.loginFrame = loginFrame;
			
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (kupacService.kupacExists(this.loginFrame.getKorisnickoIme(), this.loginFrame.getLozinka())) {
					new KupacFrame("Profile").setVisible(true);
					this.loginFrame.refresh();
				} else {
					this.loginFrame.setErrorMessage("Kupac ne postoji");
					LOGGER.log(Level.INFO, "Kupac ne postoji");
					return;
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (BlankInputException e1) {
				this.loginFrame.setErrorMessage(e1.getMessage());
				return;
			}
		}
	}

	class ComboListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());

		}
	}
}
