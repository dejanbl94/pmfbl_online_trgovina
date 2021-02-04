package Service;

import java.sql.SQLException;

import Database.DAO.TrgovacDAO;
import Entity.Kupac;
import Entity.Trgovac;

public class TrgovacService {

	private TrgovacDAO trgovacDAO;

	public TrgovacService(TrgovacDAO trgovacDAO) {
		this.trgovacDAO = trgovacDAO;
	}
	
	public boolean trgovacExists(String username, char[] charedPassword) throws SQLException {
		String password = new String(charedPassword);
		
		if (username.isEmpty() || password.isEmpty()) {
			System.err.println("Name or email can't be empty!");
			return false;
		}
		return trgovacDAO.exists(username, password);
	}
	
	public Trgovac getByUsername(String username) throws SQLException {
		if (username.isEmpty()) {
			System.err.println("Name can't be empty string");
			return new Trgovac();
		}
		Trgovac trgovac = trgovacDAO.getBy(username);
		if (trgovac == null)
			return null;
		return trgovac;
	}
}
