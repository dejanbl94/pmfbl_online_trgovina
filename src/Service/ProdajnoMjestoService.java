package Service;

import java.sql.SQLException;
import java.util.List;
import Database.DAO.ProdajnoMjestoDAO;
import Entity.ProdajnoMjesto;
import View.ProdajnoMjestoFrame;

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
	
	public boolean add(ProdajnoMjestoFrame prodajno) {
		try {
			ProdajnoMjesto prodajnoMjesto = new ProdajnoMjesto(prodajno.getGradTxt(), prodajno.getDrzavaTxt(), prodajno.getAdresaTxt(), prodajno.getTelefonTxt());
			prodajnoDAO.add(prodajnoMjesto);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
