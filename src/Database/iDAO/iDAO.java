package Database.iDAO;

import java.sql.SQLException;
import java.util.List;

public interface iDAO<T extends Object> {
	
	// Define CRUD contracts.
	public List<T> get() throws SQLException;
	public T getBy(Object predicate) throws SQLException;
	public List<T> getAll(Object predicate) throws SQLException;
	public boolean add(T entity) throws SQLException;
	public boolean update(T entity) throws SQLException;
	public boolean delete(Object predicate) throws SQLException;
}
