package Service;

import java.sql.SQLException;
import java.util.List;
import Database.DAO.ArtikalDAO;
import Database.DAO.NarudzbaDAO;
import Entity.Narudzba;
import Entity.DTO.ArtikalDTO;

public class NarudzbaService {

	private NarudzbaDAO narudzbaDAO;
	private ArtikalDAO artikalDAO;

	public NarudzbaService(NarudzbaDAO narudzbaDAO, ArtikalDAO artikalDAO) {
		this.narudzbaDAO = narudzbaDAO;
		this.artikalDAO = artikalDAO;
	}
	
	public List<Narudzba> getAll(int kupacId, String filter) {
		try {
			return narudzbaDAO.getAll(kupacId, filter);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ArtikalDTO> getAllArtikalNarudzbe(int narudzbaId) {
		try {
			return artikalDAO.getAll(narudzbaId, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean delete(int narudzbaId) {
		try {
			return narudzbaDAO.delete(narudzbaId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
