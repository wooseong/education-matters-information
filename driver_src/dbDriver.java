import java.sql.SQLException;

public class dbDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBController db = new DBController(DBconf.DB);
		
		db.executeQuery("select id, name from student where id like '%'");
		try {
			String[][] toPrint = DBController.toStringList(db.getResultSet());

			for(String[] inner: toPrint) {
				for(String str: inner) {
					System.out.print(str + " ");
				}
				System.out.println("");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			DBController.ClassEnroll("3", "001112", "001", "2017/2", "4");
			DBController.ClassEnroll("4", "001112", "001", "2017/2", "4");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.disconnectDB();
//		try {
//			DBController.ClassEnroll("15011107", "001111", "001", "2017/2", "5");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

}
