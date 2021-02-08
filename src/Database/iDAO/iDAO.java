package Database.iDAO;

import java.sql.SQLException;
import java.util.List;

public interface iDAO<T extends Object> {
	
	/** CRUD contracts **/
	public List<T> get() throws SQLException;
	public T getBy(Object token, String filter) throws SQLException;
	public List<T> getAll(Object predicate, Object filter) throws SQLException;
	public int add(T entity) throws SQLException;
	public boolean update(T entity) throws SQLException;
	public boolean delete(Object predicate) throws SQLException;
}
