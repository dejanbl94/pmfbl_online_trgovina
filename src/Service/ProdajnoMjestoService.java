package Service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Database.DAO.ProdajnoMjestoDAO;
import Entity.ProdajnoMjesto;
import View.ProdajnoMjestoFrame;

public class ProdajnoMjestoService {
	
	private static final Logger LOG = Logger.getLogger( ProdajnoMjestoService.class.getSimpleName());
	
	public ProdajnoMjestoService(ProdajnoMjestoDAO dao) {
		this.prodajnoDAO = dao;
	}
	
	public List<ProdajnoMjesto> getAll() {
		try {
			return prodajnoDAO.get();
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return null;
	}
	
	public boolean add(ProdajnoMjestoFrame prodajno) {
		try {
			ProdajnoMjesto prodajnoMjesto = new ProdajnoMjesto(prodajno.getGradTxt(), prodajno.getDrzavaTxt(), prodajno.getAdresaTxt(), prodajno.getTelefonTxt());
			return prodajnoDAO.add(prodajnoMjesto) == 1;
		} catch (SQLException ex) {
			LOG.log(Level.WARNING, ex.getLocalizedMessage());
		}
		return false;
	}

	private ProdajnoMjestoDAO prodajnoDAO;
}
