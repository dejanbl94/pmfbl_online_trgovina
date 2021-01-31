package Database.iDAO;

import java.sql.SQLException;

import Entity.Kupac;

public interface iKupac {
	
	public Kupac getKupacByKorisnickoIme(String korisnickoIme) throws SQLException;
	public boolean kupacPostoji(String korisnickoIme, String password) throws SQLException;
	public boolean addKupac(Kupac kupac) throws SQLException;
}
