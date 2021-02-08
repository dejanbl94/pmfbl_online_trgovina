package Service;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.DAO.ArtikalDAO;
import Entity.DTO.ArtikalDTO;

public class ArtikalService {
	
    private static final Logger LOG = Logger.getLogger( ArtikalService.class.getSimpleName());

	public ArtikalService(ArtikalDAO artikalDAO) {
		this.artikalDAO = artikalDAO;
	}

	public boolean insert(ArtikalDTO artikal) {
		try {
			if (artikalDAO.add(artikal) == 1) {
				return true;
			}
		} catch (SQLException e) {
			LOG.log(Level.WARNING, e.getLocalizedMessage());
			e.printStackTrace();
		}
		return false;
	}
    
	private ArtikalDAO artikalDAO;
}
