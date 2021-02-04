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
import Entity.ArtikalNarudzbe;
import Entity.Proizvod;
import Entity.DTO.ArtikalDTO;
import QueryBuilder.QueryBuilder;

public class ArtikalDAO implements iDAO<ArtikalDTO> {
	static DbConnectionPool pool;

	public ArtikalDAO() {
		// pool = DbConnectionPool.getInstance();
	}

	@Override
	public List<ArtikalDTO> getAll(Object narudzbaId) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		List<ArtikalDTO> listaArtikala = new ArrayList<ArtikalDTO>();

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Narudzba.GET_ARTIKAL_NARUDZBE);

			statement.setInt(1, (Integer) narudzbaId);

			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ArtikalDTO artikal = new ArtikalDTO();

				artikal.setNarudzbaId(resultSet.getInt("narudzba_id"));
				artikal.setProizvodId(resultSet.getInt("proizvod_id"));
				artikal.setKolicina(resultSet.getInt("kolicina"));
				artikal.setCijenaKomad(resultSet.getDouble("cijena_po_komadu"));
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
	public ArtikalDTO getBy(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(ArtikalDTO artikal) throws SQLException {
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
			statement = connection.prepareStatement(QueryBuilder.Artikal.INSERT);

			statement.setInt(1,artikal.getNarudzbaId());
			statement.setInt(2, artikal.getId());
			statement.setInt(3, artikal.getKolicina());
			statement.setDouble(4, artikal.getCijenaKomad());

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

	@Override
	public boolean update(ArtikalDTO entity) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object predicate) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ArtikalDTO> get() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
