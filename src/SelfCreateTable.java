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

/**
 * 테이블 자동 생성해주는 클래스
 * @author bang
 *
 */
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

    /**
     * 쿼리로 테이블 생성
     * @param query	테이블에 불러올 쿼리
     */
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
    /**
     * 실 데이터로 테이블 생성
     * @param data		테이블 내용
     * @param columName	테이블 컬럼 이름
     */
    public void makeTable(Object[][] data, Object[] columName) {

        this.tableModel = new DefaultTableModel(data, columName);

        this.table = new JTable(this.tableModel);
        this.table.getTableHeader().setReorderingAllowed(false);
//        this.table.setTableHeader(null);    // 컬럼 이름 안보이게 처리

        listener = new tableListener();
        table.addMouseListener(listener);
        this.setViewportView(this.table);
    }
    /**
     * 테이블 모델로 테이블 생성
     * @param tableModel	생성할 테이블 모델
     */
    public void makeTable(DefaultTableModel tableModel) {

        this.tableModel = tableModel;
        this.table = new JTable(this.tableModel);
        this.table.getTableHeader().setReorderingAllowed(false);

        listener = new tableListener();
        table.addMouseListener(listener);
        this.setViewportView(this.table);
    }

    /**
     * 쿼리로 체크박스가 있는 테이블 생성
     * 체크박스는 무조건 컬럼 인덱스 0번에 들어감
     * @param query	테이블에 표시할 쿼리
     */
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
                    return Boolean.class;	// 여기서 체크박스로 처리가 됨
                default:
                    return String.class;
                }
            }

        };
        this.table.getTableHeader().setReorderingAllowed(false);	// 컬럼 재배치 거부

        listener = new tableListener();
        table.addMouseListener(listener);
        this.setViewportView(this.table);
        db.disconnectDB();
    }
    /**
     * 실 데이터로 체크박스가 있는 테이블 생성
     * @param data		테이블 내용
     * @param columName	테이블 컬럼 이름
     */
    public void makeCheckboxTable(Object[][] data, Object[] columName) {

        this.tableModel = new DefaultTableModel(data, columName);

        this.table = new JTable(this.tableModel) {

            @Override
            public Class<?> getColumnClass(int column) {
                switch(column) {
                case 0:						// 컬럼 0번째
                    return Boolean.class;	// 여기서 체크박스가 설정 됨
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
    /**
     * 테이블 모델을 이용하여 체크박스가 있는 테이블 생성
     * @param tableModel	생성하고자 하는 테이블 모델
     */
    public void makeCheckboxTable(DefaultTableModel tableModel) {

        this.tableModel = tableModel;
        this.table = new JTable(this.tableModel) {

            @Override
            public Class<?> getColumnClass(int column) {
                switch(column) {
                case 0:						// 컬럼 인덱스 0
                    return Boolean.class;	// 여기서 체크박스가 설정됨
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

    // 테이블의 컬럼 이름을 안보이게 처리
    public void delColumnName() {
        this.table.setTableHeader(null);
    }

    // 테이블에서 체크된 내용을 가져오는 메소드
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

    // 테이블에 버튼 처리를 하기위한 마우스 이벤트 리스너
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

    /**
     * 쿼리를 이용한 테이블 모델 생성하는 내부 클래스
     * @param rs	ResultSet
     * @return		DefaultTableModel
     * @throws SQLException
     */
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
