package Database.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Database.DatabaseConnection;
import Database.DbConnectionPool;
import Database.iDAO.iDAO;
import Entity.Trgovac;
import QueryBuilder.QueryBuilder;

public class TrgovacDAO implements iDAO<Trgovac> {
	
	static DbConnectionPool pool;

	public TrgovacDAO() {
		//pool = DbConnectionPool.getInstance();
	}

	public boolean exists(String korisnickoIme, String password) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Trgovac.GET);
			
			statement.setString(1, korisnickoIme);
			statement.setString(2, password);

			resultSet = statement.executeQuery();
			if (!resultSet.next()) {
				return false;
			} else {
				return true;
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
		return false;
	}

	@Override
	public Trgovac getBy(Object korisnickoIme) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Trgovac trgovac = new Trgovac();

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Trgovac.GET_BY_IME);
			
			statement.setString(1, korisnickoIme.toString());
			
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				trgovac.setKorisnickoIme(resultSet.getString("korisnicko_ime"));
				trgovac.setIme(resultSet.getString("ime"));
				trgovac.setPrezime(resultSet.getString("prezime"));
				trgovac.setTelefon(resultSet.getString("telefon"));
				trgovac.setPol(resultSet.getString("pol"));
				trgovac.setEmail(resultSet.getString("email"));
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
		return trgovac;
	}

	@Override
	public boolean add(Trgovac trgovac) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			if (!exists(trgovac.getKorisnickoIme(), trgovac.getLozinka())) {
				statement = connection.prepareStatement(QueryBuilder.Trgovac.INSERT);
				
				statement.setString(1, trgovac.getKorisnickoIme());
				statement.setString(2, trgovac.getIme());
				statement.setString(3, trgovac.getPrezime());
				statement.setString(4, trgovac.getLozinka());
				statement.setString(5, trgovac.getTelefon());
				statement.setString(10, trgovac.getPol());
				statement.setString(11, trgovac.getEmail());
				
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
	public boolean update(Trgovac entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Trgovac> getAll(Object predicate, Object filter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trgovac> get() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
