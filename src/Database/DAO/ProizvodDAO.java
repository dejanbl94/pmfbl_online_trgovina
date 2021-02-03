package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.DbConnectionPool;
import Database.iDAO.iDAO;
import Entity.Proizvod;
import QueryBuilder.QueryBuilder;

public class ProizvodDAO implements iDAO<Proizvod> {
	static DbConnectionPool pool;

	public ProizvodDAO() {
			pool = DbConnectionPool.getInstance();
		}

	@Override
	public List<Proizvod> getAll(Object narudzbaId) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		List<Proizvod> proizvodi = new ArrayList<Proizvod>();

		try (Connection connection = DbConnectionPool.getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Proizvod.GET_PROIZVOD_NARUDZBE);

			statement.setInt(1, (Integer) narudzbaId);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Proizvod proizvod = new Proizvod();

				proizvod.setNaziv(resultSet.getString("naziv"));
				proizvod.setKolicina(resultSet.getInt("kolicina"));
				proizvod.setCijena(resultSet.getDouble("cijena_po_komadu"));
				proizvod.setOpis(resultSet.getString("opis"));
				proizvodi.add(proizvod);
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
		return proizvodi;
	}

	@Override
	public boolean add(Proizvod entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Proizvod entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Proizvod getBy(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
