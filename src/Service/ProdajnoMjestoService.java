package Service;

import Database.DAO.ProdajnoMjestoDAO;

public class ProdajnoMjestoService {
	
	ProdajnoMjestoDAO prodajnoDAO;
	
	public ProdajnoMjestoService(ProdajnoMjestoDAO dao) {
		this.prodajnoDAO = dao;
	}
	
	/*public List<ProdajnoMjest> getAll() {
		return prodajnoDAO.getAll(kupacId)
	}
*/
}
