package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Database.DAO.ArtikalDAO;
import Database.DAO.NarudzbaDAO;
import Entity.Narudzba;
import Entity.DTO.ArtikalDTO;
import Entity.DTO.ProizvodDTO;

public class NarudzbaService {

	private static final Logger LOG = Logger.getLogger( NarudzbaService.class.getSimpleName());

	public NarudzbaService(NarudzbaDAO narudzbaDAO, ArtikalDAO artikalDAO) {
		this.narudzbaDAO = narudzbaDAO;
		this.artikalDAO = artikalDAO;
	}

	public List<Narudzba> getAll(int kupacId, String filter) {
		try {
			return narudzbaDAO.getAll(kupacId, filter);
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return null;
	}
	
	public List<ArtikalDTO> getAllArtikalNarudzbe(int narudzbaId) {
		try {
			return artikalDAO.getAll(narudzbaId, null);
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return null;
	}
	
	public boolean delete(int narudzbaId) {
		try {
			System.out.println(narudzbaDAO.delete(narudzbaId));
			return narudzbaDAO.delete(narudzbaId);
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return false;
	}
	
	public boolean add(Narudzba narudzba) {
		try {
			return narudzbaDAO.add(narudzba) == 1;
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return false;
	}
	
	public List<ProizvodDTO> getNarudzbeNaCekanju() {
		return narudzbeNaCekanju;
	}

	public void setNarudzbeNaCekanju(List<ProizvodDTO> narudzbeNaCekanju) {
		this.narudzbeNaCekanju = narudzbeNaCekanju;
	}
	
	public int getLastId() {
		try {
			return narudzbaDAO.getLastId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public int updateTrgovac(int trgovacId, int narudzbaId) {
		try {
			return narudzbaDAO.updateTrgovac(trgovacId, narudzbaId);
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return 0;
	}
	
	public boolean setOrderForShipping(String datumIsporuke, String napomena, int narudzbaId) {
		try {
			return narudzbaDAO.setOrderForShipping(datumIsporuke, napomena, narudzbaId);
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return false;
	}

	private NarudzbaDAO narudzbaDAO;
	private ArtikalDAO artikalDAO;
	private List<ProizvodDTO> narudzbeNaCekanju = new ArrayList<ProizvodDTO>();
}
