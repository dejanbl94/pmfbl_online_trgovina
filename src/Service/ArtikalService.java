package Service;

import java.sql.SQLException;

import Database.DAO.ArtikalDAO;
import Entity.DTO.ArtikalDTO;

public class ArtikalService {

	private ArtikalDAO artikalDAO;

	public ArtikalService(ArtikalDAO artikalDAO) {
		this.artikalDAO = artikalDAO;
	}

	public boolean insert(ArtikalDTO artikal) {
		try {
			if (artikalDAO.add(artikal)) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
