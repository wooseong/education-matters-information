import java.sql.*;
import java.util.ArrayList;

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

	public static String[][] toStringList(ResultSet rs) throws SQLException{		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> temp = new ArrayList<String>();
		while(rs.next()) {
			for(int i=0;i<rs.getMetaData().getColumnCount(); i++) {
				temp.add(rs.getString(i+1));
			}
			list.add(temp);
			temp = new ArrayList<String>();
		}
		
		String[][] str = new String[list.size()][];
		for (int i=0;i<str.length;i++) {
			str[i] = new String[list.get(i).size()];
			for(int j=0;j<str[i].length;j++) {
				str[i][j] = new String(list.get(i).get(j));
			}
		}
		
		return str;
	}

	/**
	 * 수강신청 메소드
	 * @param studentID    학번
	 * @param lectureNum   학수번호
	 * @param classNum     분반
	 * @param year         년도/학기
	 * @param semester     학생의 현재 학기
	 * @return             DBconf.state
	 * @throws SQLException
	 */
	public static int ClassEnroll(String studentID, String lectureNum, String classNum, String year, String semester)
			throws SQLException {

		String url = "jdbc:sqlite:" + DBconf.DB;
		int seat = 0;

		Connection conn = DriverManager.getConnection(url);
		Statement stmt = null;
		ResultSet rs = null;

		int resultStatement = DBconf.SQL_NO_DATA;

		stmt = conn.createStatement();
		rs = stmt.executeQuery("select maxStudent from lecture where number='" + lectureNum + "' and class='" + classNum + "' and year='" + year + "'");
		if(rs.next()) {
			seat = rs.getInt("maxStudent");

			if(seat != 0) {
				rs = stmt.executeQuery("select id from '" + year + "' where id='" + studentID + "' and lecture='" + lectureNum + "' and class='" + classNum + "' and semester='" + semester + "'");
				if(rs.next()) {
					System.out.println("COURCE_ENROLL_EXIST");
					resultStatement = DBconf.COURCE_ENROLL_EXIST;
				}else {
					stmt.execute("insert into '" + year + "' values ('" + studentID + "', '" + lectureNum + "', '" + classNum + "', '', '', '', '" + semester + "')");
					stmt.execute("update lecture set maxStudent=" + (seat-1) + " where number='" + lectureNum + "' and class='" + classNum + "' and year='" + year + "'");
					System.out.println("COURCE_ENROLL_SUCCESS");
					resultStatement = DBconf.COURCE_ENROLL_SUCCESS;
				}
			} else {
				System.out.println("COURCE_ENROLL_FAIL_NO_MORE_SEAT");
				resultStatement = DBconf.COURCE_ENROLL_FAIL_NO_MORE_SEAT;
			}
		} else {
			System.out.println("SQL_NO_DATA");
			resultStatement = DBconf.SQL_NO_DATA;
		}

		stmt.close();
		rs.close();
		conn.close();

		return resultStatement;
	}

}
