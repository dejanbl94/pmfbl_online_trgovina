package Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Database.DAO.ProizvodDAO;
import Entity.Proizvod;
import Entity.DTO.ProizvodDTO;
import View.NewProductFrame;

public class ProizvodService {
	
	private static final Logger LOG = Logger.getLogger( ProizvodService.class.getSimpleName());
	
	public ProizvodService(ProizvodDAO proizvodDAO) {
		this.proizvodDAO = proizvodDAO;
	}

	public List<Proizvod> getAll(int narudzbaId) {
		try {
			return proizvodDAO.getAll(narudzbaId, null);
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return null;
	}
	
	public List<Proizvod> getProizvodi() {
		try {
			return proizvodDAO.get();
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return null;
	}
	
	public boolean add(NewProductFrame product) {
		Proizvod proizvod = new Proizvod(product.getNazivTxt(), product.getOpisTxt(), Double.parseDouble(product.getCijenaTxt()));
		try {
			return proizvodDAO.add(proizvod) == 1;
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return false;
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

	private ProizvodDAO proizvodDAO;
	private ProizvodDTO proizvodDTO;
	private List<ProizvodDTO> naruceniProizvodi = new ArrayList<ProizvodDTO>();
}
