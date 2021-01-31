package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controller.KupacController;
import Database.DbConnectionPool;
import Database.iDAO.iDAO;
import Entity.Kupac;
import QueryBuilder.QueryBuilder;

public class KupacDAO implements iDAO<Kupac> {

	private static final Logger LOGGER = Logger.getLogger( KupacController.class.getSimpleName());
	static DbConnectionPool pool;

	public KupacDAO() {
		pool = DbConnectionPool.getInstance();
	}

	public boolean exists(String korisnickoIme, String password) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try (Connection connection = DbConnectionPool.getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Kupac.GET);
			
			statement.setString(1, korisnickoIme);
			statement.setString(2, password);
			
			resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException ex) {
			LOGGER.log(Level.SEVERE, ex.getLocalizedMessage());
		}
		finally {
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return false;
	}

	@Override
	public Kupac getBy(Object korisnickoIme) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Kupac kupac = new Kupac();

		try (Connection connection = DbConnectionPool.getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Kupac.GET_BY_IME);
			
			statement.setString(1, korisnickoIme.toString());
			
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				kupac.setKorisnickoIme(resultSet.getString("korisnicko_ime"));
				kupac.setIme(resultSet.getString("ime"));
				kupac.setPrezime(resultSet.getString("prezime"));
				kupac.setTelefon(resultSet.getString("telefon"));
				kupac.setAdresa(resultSet.getString("adresa"));
				kupac.setGrad(resultSet.getString("grad"));
				kupac.setDrzava(resultSet.getString("drzava"));
				kupac.setPostanskiBroj(resultSet.getString("postanski_broj"));
				kupac.setPol(resultSet.getString("pol"));
				kupac.setEmail(resultSet.getString("email"));
			} else {
				return null;
			}
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return kupac;
	}

	@Override
	public boolean add(Kupac kupac) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try (Connection connection = DbConnectionPool.getConnection()) {
			if (!exists(kupac.getKorisnickoIme(), kupac.getLozinka())) {
				statement = connection.prepareStatement(QueryBuilder.Kupac.INSERT);
				
				statement.setString(1, kupac.getKorisnickoIme());
				statement.setString(2, kupac.getIme());
				statement.setString(3, kupac.getPrezime());
				statement.setString(4, kupac.getLozinka());
				statement.setString(5, kupac.getTelefon());
				statement.setString(6, kupac.getAdresa());
				statement.setString(7, kupac.getGrad());
				statement.setString(8, kupac.getDrzava());
				statement.setString(9, kupac.getPostanskiBroj());
				statement.setString(10, kupac.getPol());
				statement.setString(11, kupac.getEmail());
				
				statement.executeUpdate();
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.err.println(ex.getLocalizedMessage());
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return true;
	}

	@Override
	public boolean update(Kupac entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Kupac> getAll(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
