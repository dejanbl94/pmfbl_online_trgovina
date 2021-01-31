package Database.iDAO;

import java.sql.SQLException;
import java.util.List;

import Entity.Narudzba;

public interface iNarudzba {
	
	public List<Narudzba> getNarudzbeZaKupca(int kupacId) throws SQLException;
	// Dodaj narudzbe sa vezanom artiklima
}
