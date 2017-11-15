import java.sql.*;

/**
 * Class for control DB file
 * 
 * @author Bang il sub
 * 
 * HOW TO USE
 *	1. Call class and connect db file
 *	2. execute your query
 *		2-1. for security, use '?' where data will placed
 *			@see java.sql.PreparedStatement.prepareStatement
 *	3. Get data what you want!
 *
 * 
 * Example
 * 	DBController db = new DBController("test.db");
 *  // ResultSet rs = db.executeQuery("select name from table where userID=" + userID + " and age=" + age); // insecure
 * 	ResultSet rs = db.executeQuery("select name from table where userID=? and age=?", userID, age);
 * 
 * 	// rs.getType("Subject what you want");
 * 	rs.getInt("studentID");
 * 	rs.getString("name");
 * 
 * 	db.disconnectDB();
 * 	try { rs.close(); } catch (Exception e) {  }
 */
public class DBController {

	// instance data
	private Connection conn;
	private ResultSet rs;
	
	//constructor
	public DBController(String filename) {
		this.connectDB(filename);
		this.rs = null;
	}
	public DBController() {
		this.conn = null;
		this.rs = null;
	}
	
	public ResultSet getResultSet() { return this.rs; }
	
	/**
	 * method for connection sqlite DB file
	 * @param filename DB file to connect
	 * @return true if success, false if fail
	 */
	public boolean connectDB(String filename) {
		try {
			String url = "jdbc:sqlite:" + filename;
			this.conn = DriverManager.getConnection(url);
			
			if (conn != null) { return true; }
			else { return false; }
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
			return false;
		}
	}
	
	/**
	 * method for disconnect sqlite DB file
	 * @return true if success, false if fail
	 */
	public void disconnectDB() {
		try { rs.close(); } catch (Exception e) { /* ignored */ }
//	    try { ps.close(); } catch (Exception e) { /* ignored */ }
	    try { conn.close(); } catch (Exception e) { /* ignored */ }
	}
	
	/**
	 * insecure method for execute query
	 * @param query query to execute
	 * @return ResultSet if success, null if fail
	 */
	public void executeQuery(String query) {
		try {
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} catch(Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	/**
	 * secure method for execute query
	 * @param query	Query to execute. String with '?'
	 * @param args 	Data for each '?' in query
	 * @return ResultSet if success, null if fail
	 */
	public void executeQuery(String query, String... args) {
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			int parameterIndex = 0;
			for (String str : args){
				stmt.setString(parameterIndex++, str);
			}
			rs = stmt.executeQuery();			
		} catch(Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}
	
	/**
	 * insecure method for execute query withdout declare
	 * @param filename	DB file to execute query
	 * @param query		Query to execute
	 * @return ResultSet if success, null if fail
	 */
	public static ResultSet executeQuery(String filename, String query) {
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			String url = "jdbc:sqlite:/" + filename;
			conn = DriverManager.getConnection(url);
			
			if (conn == null) {
				return null;
			}
			else {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query);
				
			    return rs;
			}
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
	}
	
	/**
	 * secure method for execute query without declare
	 * @param filename	DB file to execute query
	 * @param query		Query to execute. String with '?'
	 * @param args		Data for each '?' in query
	 * @return ResultSet if success, null if fail
	 */
	public static ResultSet executeQuery(String filename, String query, String... args) {
		// TODO change param(String... args) to param(Object... args)
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			String url = "jdbc:sqlite:" + filename;
			conn = DriverManager.getConnection(url);
			
			if (conn == null) {
				return null;
			}
			else {
				stmt = conn.prepareStatement(query);
				
				int parameterIndex = 0;
				for(String str : args) {
					stmt.setString(parameterIndex++, str);
				}
				rs = stmt.executeQuery();
				
				return rs;
			}
		} catch (Exception e) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			return null;
		}
	}
	
}
