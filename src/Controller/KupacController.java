package Controller;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Database.DAO.KupacDAO;
import Entity.Kupac;
import Service.KupacService;
import View.KupacFrame;

public class KupacController extends BaseController {

	// Instanciramo singleton logger.
	private static final Logger LOGGER = Logger.getLogger(KupacController.class.getSimpleName());

	private KupacService kupacService;
	private KupacFrame kupacFrame;

	public KupacController(KupacFrame frame) {
		super();
		setKupacFrame(frame);
		this.kupacService = new KupacService(new KupacDAO());
	}

	public boolean exists(String username, char[] charedPassword) {
		try {
			return kupacService.kupacExists(username, charedPassword);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		return false;
	}

	public Kupac getByUsername(String username) {
		Kupac kupac;
		try {
			kupac = kupacService.getByUsername(username);
			if (kupac != null) {
				getKupacFrame().setIme(kupac.getIme());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setKupacFrame(KupacFrame frame) {
		this.kupacFrame = frame;
	}
	
	public KupacFrame getKupacFrame() {
		return this.kupacFrame;
	}
}
