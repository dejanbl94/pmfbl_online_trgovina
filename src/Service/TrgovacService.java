package Service;

import java.sql.SQLException;
import java.util.List;

import Database.DAO.TrgovacDAO;
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
		Trgovac trgovac = trgovacDAO.getBy(username, null);
		if (trgovac == null)
			return null;
		return trgovac;
	}
	
	public Trgovac getByProdajnoMjesto(String mjestoId) throws SQLException {
		if (mjestoId.isEmpty()) {
			System.err.println("Mjesto id can't be empty string");
			return new Trgovac();
		}
		Trgovac trgovac = trgovacDAO.getBy(mjestoId, "mjesto");
		if (trgovac == null)
			return null;
		return trgovac;
	}
	
	public List<Trgovac> getAll() {
		try {
			trgovacDAO.get();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private int narudzbaId;

	public int getNarudzbaId() {
		return narudzbaId;
	}

	public void setNarudzbaId(int narudzbaId) {
		this.narudzbaId = narudzbaId;
	}
}
