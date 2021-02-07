package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.DAO.ArtikalDAO;
import Database.DAO.NarudzbaDAO;
import Entity.Narudzba;
import Entity.DTO.ArtikalDTO;
import Entity.DTO.ProizvodDTO;

public class NarudzbaService {

	private NarudzbaDAO narudzbaDAO;
	private ArtikalDAO artikalDAO;
	private List<ProizvodDTO> narudzbeNaCekanju = new ArrayList<ProizvodDTO>();

	public NarudzbaService(NarudzbaDAO narudzbaDAO, ArtikalDAO artikalDAO) {
		this.narudzbaDAO = narudzbaDAO;
		this.artikalDAO = artikalDAO;
	}
	
	public List<ProizvodDTO> getNarudzbeNaCekanju() {
		return narudzbeNaCekanju;
	}

	public void setNarudzbeNaCekanju(List<ProizvodDTO> narudzbeNaCekanju) {
		this.narudzbeNaCekanju = narudzbeNaCekanju;
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
	
	public boolean add(Narudzba narudzba) {
		try {
			return narudzbaDAO.add(narudzba);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public boolean setOrderForShipping(String datumIsporuke, String napomena, int narudzbaId) {
		try {
			return narudzbaDAO.setOrderForShipping(datumIsporuke, napomena, narudzbaId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
