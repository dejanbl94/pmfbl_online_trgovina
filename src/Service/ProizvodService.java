package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DAO.ProizvodDAO;
import Entity.Proizvod;
import Entity.DTO.ProizvodDTO;

public class ProizvodService {
	
	private ProizvodDAO proizvodDAO;
	private ProizvodDTO proizvodDTO;
	private List<ProizvodDTO> naruceniProizvodi = new ArrayList<ProizvodDTO>();

	public ProizvodService(ProizvodDAO proizvodDAO) {
		this.proizvodDAO = proizvodDAO;
	}

	public List<Proizvod> getAll(int narudzbaId) {
		try {
			return proizvodDAO.getAll(narudzbaId, null);
		} catch (SQLException e) {
			System.err.print(e.getLocalizedMessage());
		}
		return null;
	}
	
	public List<Proizvod> getProizvodi() {
		try {
			return proizvodDAO.get();
		} catch (SQLException e) {
			System.err.print(e.getLocalizedMessage());
		}
		return null;
	}
	
	public void setProizvod(ProizvodDTO proizvod) {
		this.proizvodDTO = proizvod;
	}
	
	public ProizvodDTO getProizvod() {
		return this.proizvodDTO;
	}
	
	public List<ProizvodDTO> getNaruceniProizvodi() {
		return naruceniProizvodi;
	}

	public void setNaruceniProizvodi(List<ProizvodDTO> naruceniProizvodi) {
		this.naruceniProizvodi = naruceniProizvodi;
	}
}
