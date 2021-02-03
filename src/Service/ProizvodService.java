package Service;

import java.sql.SQLException;
import java.util.List;

import Database.DAO.ProizvodDAO;
import Entity.Proizvod;

public class ProizvodService {
	
	private ProizvodDAO proizvodDAO;
	
	public ProizvodService(ProizvodDAO proizvodDAO) {
		this.proizvodDAO = proizvodDAO;
	}

	public List<Proizvod> getAll(int narudzbaId) {
		try {
			return proizvodDAO.getAll(narudzbaId);
		} catch (SQLException e) {
			System.err.print(e.getLocalizedMessage());
		}
		return null;
	}
}
