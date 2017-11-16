import java.sql.SQLException;

public class dbDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBController db = new DBController("./DB/test.db");
		
		db.executeQuery("select ID, Name from Student where ID like '15%'");
		try {
			for (int i=0;i<db.getResultSet().getMetaData().getColumnCount();i++) {
				System.out.print(db.getResultSet().getMetaData().getColumnName(i+1) + " ");
			}
			System.out.println("");
			while(db.getResultSet().next()) {
				for (int i=0;i<db.getResultSet().getMetaData().getColumnCount();i++) {
					System.out.print(db.getResultSet().getString(i+1) + " ");
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disconnectDB();
	}

}
