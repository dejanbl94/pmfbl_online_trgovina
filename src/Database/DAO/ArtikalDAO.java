package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Database.DbConnectionPool;
import Database.iDAO.iDAO;
import Entity.ArtikalNarudzbe;
import QueryBuilder.QueryBuilder;

public class ArtikalDAO implements iDAO<ArtikalNarudzbe> {
	static DbConnectionPool pool;

	public ArtikalDAO() {
		pool = DbConnectionPool.getInstance();
	}

	@Override
	public List<ArtikalNarudzbe> getAll(Object narudzbaId) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		List<ArtikalNarudzbe> listaArtikala = new ArrayList<ArtikalNarudzbe>();

		try (Connection connection = DbConnectionPool.getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Narudzba.GET_ARTIKAL_NARUDZBE);

			statement.setInt(1, (Integer) narudzbaId);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ArtikalNarudzbe artikal = new ArtikalNarudzbe();
				
				artikal.setNarudzba_id(resultSet.getInt("narudzba_id"));
				artikal.setProizvod_id(resultSet.getInt("proizvod_id"));
				artikal.setKolicina(resultSet.getInt("kolicina"));
				artikal.setCijenaPoKomadu(resultSet.getDouble("cijena_po_komadu"));
				artikal.setId(resultSet.getInt("id"));
				listaArtikala.add(artikal);
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
		return listaArtikala;
	}

	@Override
	public ArtikalNarudzbe getBy(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(ArtikalNarudzbe entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ArtikalNarudzbe entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
}
