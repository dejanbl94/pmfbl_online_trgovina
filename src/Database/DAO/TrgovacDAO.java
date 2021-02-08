package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.DatabaseConnection;
import Database.DbConnectionPool;
import Database.iDAO.iDAO;
import Entity.Trgovac;
import QueryBuilder.QueryBuilder;

public class TrgovacDAO implements iDAO<Trgovac> {

	static DbConnectionPool pool;

	public TrgovacDAO() {
		// pool = DbConnectionPool.getInstance();
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
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
	}

	@Override
	public Trgovac getBy(Object token, String filter) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Trgovac trgovac = new Trgovac();

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			if (filter == null) {
				statement = connection.prepareStatement(QueryBuilder.Trgovac.GET_BY_IME);
			} else {
				statement = connection.prepareStatement(QueryBuilder.Trgovac.GET_BY_PRODAJNO_MJESTO);
			}

			statement.setString(1, token.toString());

			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				trgovac.setId(resultSet.getInt("id"));
				trgovac.setKorisnickoIme(resultSet.getString("korisnicko_ime"));
				trgovac.setIme(resultSet.getString("ime"));
				trgovac.setPrezime(resultSet.getString("prezime"));
				trgovac.setTelefon(resultSet.getString("telefon"));
				trgovac.setPol(resultSet.getString("pol"));
				trgovac.setEmail(resultSet.getString("email"));
			} else {
				return null;
			}
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
	public int add(Trgovac trgovac) throws SQLException {
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
				statement.setInt(12, trgovac.getProdajnoMjestId());

				return statement.executeUpdate();
			} else {
				return 0;
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
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
		return null;
	}

	@Override
	public List<Trgovac> get() throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		List<Trgovac> trgovci = new ArrayList<Trgovac>();

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {

			statement = (connection.prepareStatement(QueryBuilder.Trgovac.GET_ALL));

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Trgovac trgovac = new Trgovac();

				trgovac.setId(resultSet.getInt("id"));
				trgovac.setKorisnickoIme(resultSet.getString("korisnicko_ime"));
				trgovac.setIme(resultSet.getString("ime"));
				trgovac.setPrezime(resultSet.getString("prezime"));
				trgovac.setTelefon(resultSet.getString("telefon"));
				trgovac.setPol(resultSet.getString("pol"));
				trgovac.setEmail(resultSet.getString("email"));
				trgovci.add(trgovac);
			}
		} finally {
			if (statement != null) {
				statement.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
		}
		return trgovci;
	}
}
