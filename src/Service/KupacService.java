package Service;

import java.sql.SQLException;
import Database.DAO.KupacDAO;
import Entity.Kupac;

public class KupacService {

	private KupacDAO kupacDAO;

	public KupacService(KupacDAO kupacDAO) {
		this.kupacDAO = kupacDAO;
	}

	public Kupac getByUsername(String username) throws SQLException {
		if (username.isEmpty()) {
			System.err.println("Name can't be empty string");
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
		return kupacDAO.exists(username, password);
	}
	
	public boolean addKupac(Kupac kupac) throws SQLException {
		if (kupac == null) {
			return false;
		}
		return kupacDAO.add(kupac);
	}
}
