package Database.iDAO;

import java.sql.SQLException;

import Entity.Trgovac;

public interface iTrgovac {

	public Trgovac getTrgovacByKorisnickoIme(String korisnickoIme) throws SQLException;
	public boolean trgovacPostoji(String korisnickoIme, String password) throws SQLException;
	public boolean addTrgovac(Trgovac trgovac) throws SQLException;
}
