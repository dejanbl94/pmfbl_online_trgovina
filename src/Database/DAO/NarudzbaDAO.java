package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.DbConnectionPool;
import Database.iDAO.iDAO;
import Entity.Narudzba;
import QueryBuilder.QueryBuilder;

public class NarudzbaDAO implements iDAO<Narudzba> {
	static DbConnectionPool pool;

	public NarudzbaDAO() {
		pool = DbConnectionPool.getInstance();
	}

	@Override
	public List<Narudzba> getAll(Object kupacId) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		List<Narudzba> listaNarudzbi = new ArrayList<Narudzba>();

		try (Connection connection = DbConnectionPool.getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Narudzba.GET_FOR_KUPAC);

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
		
		try (Connection connection = DbConnectionPool.getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Narudzba.DELETE);
			
			statement.setInt(1, (Integer)narudzbaId);
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
	public Narudzba getBy(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Narudzba entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Narudzba entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
