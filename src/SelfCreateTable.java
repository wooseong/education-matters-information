import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SelfCreateTable extends JScrollPane {
	private JTable table;
	private DBController db;
	private String[][] data;
	private String[] columName;
	
	public SelfCreateTable(String query) {
		db = new DBController("./DB/test.db");
		db.executeQuery(query);
		
		try {
			this.table = new JTable(buildTableModel(db.getResultSet()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setViewportView(this.table);
	}
	public SelfCreateTable(String[][] data, String[] columName) {
		this.data = data;
		this.columName = columName;
		this.table = new JTable(this.data, this.columName);
		this.setViewportView(this.table);
	}
	
	public String[][] getData(){
		return this.data;
	}
	public void setData(String[][] data) {
		this.data = data;
	}
	public String[] getColumName() {
		return this.columName;
	}
	public void setColumName(String[] columName) {
		this.columName = columName;
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
