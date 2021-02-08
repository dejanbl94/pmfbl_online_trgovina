package Service;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.DAO.KupacDAO;
import Entity.Kupac;

public class KupacService {

	private static final Logger LOG = Logger.getLogger( KupacService.class.getSimpleName());

	public KupacService(KupacDAO kupacDAO) {
		this.kupacDAO = kupacDAO;
	}

	public Kupac getByUsername(String username) throws SQLException {
		if (username.isEmpty()) {
			LOG.log(Level.INFO, "Name can't be empty string");
			return new Kupac();
		}
		Kupac kupac = kupacDAO.getBy(username, null);
		if (kupac == null)
			return null;
		
		return kupac;
	}
	
	public boolean kupacExists(String username, char[] charedPassword) throws SQLException {
		String password = new String(charedPassword);
		
		if (username.isEmpty() || password.isEmpty()) {
			System.err.println("Name or email can't be empty!");
			return false;
		}
		try {
			return kupacDAO.exists(username, password);
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		} catch (Exception ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return false;
	}
	
	public boolean addKupac(Kupac kupac) throws SQLException {
		if (kupac == null) {
			return false;
		}
		return kupacDAO.add(kupac) == 1;
	}

	private KupacDAO kupacDAO;
}
