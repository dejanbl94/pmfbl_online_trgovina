package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionPool {

	private static final int MAX_CONNECTIONS = 20;
	private static final Object LOCK = new Object();

	private static volatile DbConnectionPool instance = null;
	private static Connection[] connections = new Connection[MAX_CONNECTIONS];
	private static int counter;

	private DbConnectionPool() {
	}

	// Singleton instanciranje connection pool-a.
	public static DbConnectionPool getInstance() {
		if (instance == null) {
			synchronized (LOCK) {
				if (instance == null) {
					instance = new DbConnectionPool();
					addConnections();
					counter = 0;
				}
			}
		}
		return instance;
	}

	private static void addConnections() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?serverTimezone=UTC";
			
			for (int i = 0; i < MAX_CONNECTIONS; i++) {
				connections[i] = DriverManager.getConnection(url, username, password);
			}
			
		} catch (ClassNotFoundException ex) {
			System.err.println(ex.getLocalizedMessage());
		} catch (SQLException ex) {
			System.err.println(ex.getLocalizedMessage());
		}
	}

	public static Connection getConnection() {
		counter++;
		if (counter == Integer.MAX_VALUE)
			counter = 0;

		// Uzmi slobodnu konekciju iz pool-a.
		Connection con =  connections[counter % MAX_CONNECTIONS];
		return con;
	}
	
	private static final String host = "localhost";
	private static final int port = 3306;
	private static final String database = "seminarski_ors1";
	private static final String username = "root";
	private static final String password = "root";

}
