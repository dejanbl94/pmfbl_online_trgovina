package Service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Database.DAO.TrgovacDAO;
import Entity.Trgovac;
import View.AddTrgovacFrame;

public class TrgovacService {

	private static final Logger LOG = Logger.getLogger(TrgovacService.class.getSimpleName());

	public TrgovacService(TrgovacDAO trgovacDAO) {
		this.trgovacDAO = trgovacDAO;
	}
	
	public boolean trgovacExists(String username, char[] charedPassword) throws SQLException {
		String password = new String(charedPassword);
		
		if (username.isEmpty() || password.isEmpty()) {
			LOG.log(Level.INFO, "Name or email can't be empty!");
			return false;
		}
		return trgovacDAO.exists(username, password);
	}
	
	public Trgovac getByUsername(String username) throws SQLException {
		if (username.isEmpty()) {
			LOG.log(Level.INFO, "Name or email can't be empty!");
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
			LOG.log(Level.INFO, "Mjesto id can't be empty string");
			System.err.println("Mjesto id can't be empty string");
			return new Trgovac();
		}
		Trgovac trgovac = trgovacDAO.getBy(mjestoId, "mjesto");
		if (trgovac == null)
			return null;
		return trgovac;
	}
	
	public boolean add(Trgovac trgovac) {
		try {
			trgovacDAO.add(trgovac);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Trgovac> getAll() {
		try {
			trgovacDAO.get();
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
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
	
	public boolean createClerk(AddTrgovacFrame frame, int prodajnoId) {
		Trgovac trgovac = new Trgovac(frame.getKorisnickoTxt(), frame.getImeTxt(), frame.getPrezimeTxt(), frame.getLozinkaTxt(), 
				frame.getPolTxt(), frame.getTelefonTxt(), frame.getEmailTxt(), prodajnoId);
		
		try {
			trgovacDAO.add(trgovac);
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return false;
	}

	private TrgovacDAO trgovacDAO;
}
