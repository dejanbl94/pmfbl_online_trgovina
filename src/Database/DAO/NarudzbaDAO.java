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
import Entity.Narudzba;
import QueryBuilder.QueryBuilder;

public class NarudzbaDAO implements iDAO<Narudzba> {
	static DbConnectionPool pool;

	public NarudzbaDAO() {
		// pool = DbConnectionPool.getInstance();
	}

	@Override
	public List<Narudzba> getAll(Object kupacId, Object filter) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		List<Narudzba> listaNarudzbi = new ArrayList<Narudzba>();

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {

			if ((String) filter == "Kupac") {
				statement = (connection.prepareStatement(QueryBuilder.Narudzba.GET_FOR_KUPAC));
			} else if ((String) filter == "Trgovac") {
				statement = (connection.prepareStatement(QueryBuilder.Narudzba.GET_FOR_TRGOVAC));
			}
			statement.setInt(1, (Integer) kupacId);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Narudzba narudzba = new Narudzba();

				narudzba.setId(resultSet.getInt("id"));
				narudzba.setKupacId(resultSet.getInt("kupac_id"));
				narudzba.setTrgovacId(resultSet.getInt("trgovac_id"));
				narudzba.setDatumIsporuke(resultSet.getString("datum_isporuke"));
				narudzba.setDatumNarudzbe(resultSet.getString("datum_narudzbe"));
				narudzba.setNapomena(resultSet.getString("napomena"));
				listaNarudzbi.add(narudzba);
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
		return listaNarudzbi;
	}

	@Override
	public boolean delete(Object narudzbaId) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Narudzba.DELETE);

			statement.setInt(1, (Integer) narudzbaId);
			statement.execute();
			return true;
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
	public Narudzba getBy(Object predicate, String filter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Narudzba narudzba) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Narudzba.INSERT);

			statement.setInt(1, narudzba.getKupacId());
			statement.setInt(2, narudzba.getTrgovacId());
			statement.setString(3, narudzba.getDatumNarudzbe());
			statement.setString(4, narudzba.getDatumIsporuke());
			statement.setString(5, narudzba.getNapomena());

			statement.executeUpdate();
			return false;
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
	
	public boolean setOrderForShipping(String datumIsporuke, String napomena, int narudzbaId) throws SQLException {
		PreparedStatement statement = null;
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			statement = (connection.prepareStatement(QueryBuilder.Narudzba.SET_ORDER_FOR_SHIPPING));
			statement.setString(1, datumIsporuke);
			statement.setString(2, napomena);
			statement.setInt(3, narudzbaId);
			
			statement.executeUpdate();
			return true;
		}
	}

	public int getLastId() throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		int id = 0;
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {

			statement = (connection.prepareStatement(QueryBuilder.Narudzba.GET_LAST_ID));
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			return id;
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
		return id;
	}

	public int updateTrgovac(int trgovacId, int narudzbaId) throws SQLException {
		PreparedStatement statement = null;
		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			statement = (connection.prepareStatement(QueryBuilder.Narudzba.UPDATE_TRGOVAC_FOR_NARUDZBA));
			statement.setInt(1, trgovacId);
			statement.setInt(2, narudzbaId);
			return statement.executeUpdate();
		}
	}

	@Override
	public boolean update(Narudzba entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Narudzba> get() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
