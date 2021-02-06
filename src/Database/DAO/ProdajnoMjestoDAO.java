package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.DatabaseConnection;
import Database.iDAO.iDAO;
import Entity.ProdajnoMjesto;
import QueryBuilder.QueryBuilder;

public class ProdajnoMjestoDAO implements iDAO<ProdajnoMjesto> {
	
	public ProdajnoMjestoDAO() {
		//pool = DbConnectionPool.getInstance();
	}
	
	@Override
	public List<ProdajnoMjesto> get() throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		List<ProdajnoMjesto> prodajnaMjesta = new ArrayList<ProdajnoMjesto>();

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.ProdajnoMjesto.GET_ALL);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ProdajnoMjesto prodajnoMjesto = new ProdajnoMjesto();
				
				prodajnoMjesto.setId(resultSet.getInt("id"));
				prodajnoMjesto.setGrad(resultSet.getString("grad"));
				prodajnoMjesto.setDrzava(resultSet.getString("drzava"));
				prodajnoMjesto.setAdresa(resultSet.getString("adresa"));
				prodajnoMjesto.setTelefon(resultSet.getString("telefon"));
				prodajnaMjesta.add(prodajnoMjesto);
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
		return prodajnaMjesta;
	}

	@Override
	public List<ProdajnoMjesto> getAll(Object kupacId, Object filter) throws SQLException {
		return null;
	}

	@Override
	public boolean delete(Object narudzbaId) throws SQLException {
		return false;
	}

	@Override
	public ProdajnoMjesto getBy(Object token, String filter) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(ProdajnoMjesto entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ProdajnoMjesto entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
