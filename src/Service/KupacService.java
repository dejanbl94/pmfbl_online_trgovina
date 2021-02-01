package Service;

import java.sql.SQLException;

import CustomExceptions.BlankInputException;
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
		Kupac kupac = kupacDAO.getBy(username);
		if (kupac == null)
			return null;
		
		return kupacDAO.getBy(username);
	}
	
	public boolean kupacExists(String username, char[] charedPassword) throws SQLException, BlankInputException {
		String password = new String(charedPassword);
		
		if (username.isEmpty() || password.isEmpty()) {
			throw new BlankInputException("Unesite sva polja.");
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
