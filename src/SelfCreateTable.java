import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
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

    private tableListener listener;

    public JTable getTable() {
        return table;
    }

    // constructor
    public SelfCreateTable()                                    {    }
    public SelfCreateTable(String query)                        { this.makeTable(query); }
    public SelfCreateTable(Object[][] data, Object[] columName) { this.makeTable(data, columName); }

    // method
    public void makeTable(String query) {
        db = new DBController(DBconf.DB);
        db.executeQuery(query);

        try {
            this.tableModel = buildTableModel(db.getResultSet());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
        	db.disconnectDB();
        }

        this.table = new JTable(this.tableModel);
        this.table.getTableHeader().setReorderingAllowed(false);

        listener = new tableListener();
        table.addMouseListener(listener);
        this.setViewportView(this.table);
        db.disconnectDB();
    }
    public void makeTable(Object[][] data, Object[] columName) {

        this.tableModel = new DefaultTableModel(data, columName);

        this.table = new JTable(this.tableModel);
        this.table.getTableHeader().setReorderingAllowed(false);
//        this.table.setTableHeader(null);    // 컬럼 이름 안보이게 처리

        listener = new tableListener();
        table.addMouseListener(listener);
        this.setViewportView(this.table);
    }
    public void makeTable(DefaultTableModel tableModel) {

        this.tableModel = tableModel;
        this.table = new JTable(this.tableModel);
        this.table.getTableHeader().setReorderingAllowed(false);

        listener = new tableListener();
        table.addMouseListener(listener);
        this.setViewportView(this.table);
    }

    public void makeCheckboxTable(String query) {
        db = new DBController(DBconf.DB);
        db.executeQuery(query);

        try {
            this.tableModel = buildTableModel(db.getResultSet());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
        	db.disconnectDB();
        }

        this.table = new JTable(this.tableModel) {

            @Override
            public Class<?> getColumnClass(int column) {
                switch(column) {
                case 0:
                    return Boolean.class;
                default:
                    return String.class;
                }
            }

        };
        this.table.getTableHeader().setReorderingAllowed(false);

        listener = new tableListener();
        table.addMouseListener(listener);
        this.setViewportView(this.table);
        db.disconnectDB();
    }
    public void makeCheckboxTable(Object[][] data, Object[] columName) {

        this.tableModel = new DefaultTableModel(data, columName);

        this.table = new JTable(this.tableModel) {

            @Override
            public Class<?> getColumnClass(int column) {
                switch(column) {
                case 0:
                    return Boolean.class;
                default:
                    return String.class;
                }
            }

        };
        this.table.getTableHeader().setReorderingAllowed(false);

        listener = new tableListener();
        table.addMouseListener(listener);
        this.setViewportView(this.table);
    }
    public void makeCheckboxTable(DefaultTableModel tableModel) {

        this.tableModel = tableModel;
        this.table = new JTable(this.tableModel) {

            @Override
            public Class<?> getColumnClass(int column) {
                switch(column) {
                case 0:
                    return Boolean.class;
                default:
                    return String.class;
                }
            }

        };
        this.table.getTableHeader().setReorderingAllowed(false);

        listener = new tableListener();
        table.addMouseListener(listener);
        this.setViewportView(this.table);
    }

    public void delColumnName() {
        this.table.setTableHeader(null);
    }

    public String[][] getChecked(){
        int nCol = this.table.getColumnCount();
        int nRow = 0;

        for(int i=0; i<this.table.getRowCount(); i++) {
            if(Boolean.valueOf(this.table.getValueAt(i, 0).toString())) {
                nRow++;
            }
        }

        String[] row = new String[nCol];
        int indexRow = 0;

        String[][] str = new String[nRow][];
//        str[indexRow] = {
//                row1 {
//                    "asf", "asdf","asdg"
//                },
//                row2 {
//                    "qwe","qwer","qwert"
//                }
//        }


        for(int i=0; i<this.table.getRowCount(); i++) {
            if(Boolean.valueOf(this.table.getValueAt(i, 0).toString())) {
                for(int j=0; j<nCol; j++) {
                    row[j] = new String(this.table.getValueAt(i, j).toString());
                }
                str[indexRow++] = row;
                row = new String[nCol];
            }
        }

        return str;
    }

    private class tableListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            int col = table.columnAtPoint(e.getPoint());

            /* 개별 수강신청을 처리하기 위한 부분 */
            if(row >= 0 && col == 1) {
                for(int i=0; i<table.getColumnCount(); i++)
                System.out.print("" + table.getValueAt(row, i) + " ");
                System.out.println("");

                // TODO: /* 여기에 수강신청을 하는 메소드 추가해서 신청 처리*/
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
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
