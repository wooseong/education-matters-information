import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SelfCreateTable extends JScrollPane {
	
	// 상수 선언
	public static final boolean FRONT = true;
	public static final boolean BACK  = false;
	
	// instance data
	private JTable table;
	private DBController db;
	private DefaultTableModel tableModel; 
	
	// constructor
	public SelfCreateTable()									{	}
	public SelfCreateTable(String query)						{ this.makeTable(query); }
	public SelfCreateTable(Object[][] data, Object[] columName)	{ this.makeTable(data, columName); }

	// method
	public void makeTable(String query) {
		db = new DBController("./DB/test.db");
		db.executeQuery(query);
		
		try {
			this.tableModel = buildTableModel(db.getResultSet());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.table = new JTable(this.tableModel);
		this.setViewportView(this.table);
	}
	public void makeTable(Object[][] data, Object[] columName) {
		
		this.tableModel = new DefaultTableModel(data, columName);
		
		this.table = new JTable(this.tableModel);
		this.setViewportView(this.table);
	}
	public void makeTable(DefaultTableModel tableModel) {
		
		this.tableModel = tableModel;
		this.table = new JTable(this.tableModel);
		this.setViewportView(this.table);
	}
	
	public static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();

	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }

	    return new DefaultTableModel(data, columnNames);

	}
	
}
