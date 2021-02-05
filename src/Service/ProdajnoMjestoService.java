package Service;

import java.sql.SQLException;
import java.util.List;
import Database.DAO.ProdajnoMjestoDAO;
import Entity.ProdajnoMjesto;

public class ProdajnoMjestoService {
	
	ProdajnoMjestoDAO prodajnoDAO;
	
	public ProdajnoMjestoService(ProdajnoMjestoDAO dao) {
		this.prodajnoDAO = dao;
	}
	
	public List<ProdajnoMjesto> getAll() {
		try {
			return prodajnoDAO.get();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
