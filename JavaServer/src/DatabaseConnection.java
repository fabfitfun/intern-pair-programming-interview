import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Use this for querying.
 */
public class DatabaseConnection {

	// TODO DEVIN REPLACE THIS WITH A REAL DATABASE
	private static final String DATASOURCE_CONTEXT = System.getenv("jdbc:mysql://localhost:3306/fff-interview");

	// This variable is the "singleton". We init it with the first call to
	// getConnection()
	// and save it for later.
	private static DataSource datasource;

	/**
	 * Get a connection to the database. Remember to close the connection after use.
	 */
	private static Connection getConnection() throws NamingException, SQLException {
		if (datasource == null) {
			InitialContext ctx = new InitialContext();
			// DataSource is backed up by a pool that the application server provides.
			datasource = (DataSource) ctx.lookup(DATASOURCE_CONTEXT);
		}
		return datasource.getConnection();
	}
	
	/** Uses DriverManager.  */
	private static Connection getSimpleConnection() throws NamingException, SQLException {
	    //See your driver documentation for the proper format of this string :
	    String DB_CONN_STRING = "jdbc:mysql://localhost:3306/fff-interview";
	    //Provided by your driver documentation. In this case, a MySql driver is used : 
	    String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	    String USER_NAME = "root";
	    String PASSWORD = "root";
	    
	    Connection result = null;
	    try {
	      Class.forName(DRIVER_CLASS_NAME);
	    }
	    catch (Exception ex){
	      log("Check classpath. Cannot load db driver: " + DRIVER_CLASS_NAME);
	    }
	   result = DriverManager.getConnection(DB_CONN_STRING, USER_NAME, PASSWORD);
	    
	    return result;
	  }

	  private static void log(Object aObject){
	    System.out.println(aObject);
	  }

	/**
	 * Does a SELECT statement. Output is cast to the class you give as input.
	 * Throws RuntimeException upon error.
	 */
	public static <T> T queryDatabase(String query, Class<T> outputType, String column) {
		try (Connection conn = DatabaseConnection.getSimpleConnection()) {
			try (Statement stmt = conn.createStatement()) {
				ResultSet rs = stmt.executeQuery(query);
				if (!rs.next()) {
					return null;
				}

				return outputType.cast(rs.getString(column));
			}
		} catch (SQLException | NamingException bubble) {
			// Exit if this happens
			throw new RuntimeException(bubble);
		}
	}
	
	public static int updateDatabase(String query) {
		try (Connection conn = DatabaseConnection.getSimpleConnection()) {
			try (Statement stmt = conn.createStatement()) {
				int rs = stmt.executeUpdate(query);


				return rs;
			}
		} catch (SQLException | NamingException bubble) {
			// Exit if this happens
			throw new RuntimeException(bubble);
		}
	}

	/**
	 * INSERT, UPDATE, OR DELETE statement. Throws RuntimeException upon error.
	 */
	public static int updateStatement(String statement) {
		try (Connection conn = DatabaseConnection.getConnection()) {
			try (Statement stmt = conn.createStatement()) {
				return stmt.executeUpdate(statement);
			}
		} catch (SQLException | NamingException bubble) {
			// Exit if this happens
			throw new RuntimeException(bubble);
		}
	}
}
