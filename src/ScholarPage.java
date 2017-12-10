import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScholarPage extends JPanel {
	private JLabel name;
	private SelfCreateTable table;
	
	public ScholarPage() {
		this.setLayout(null);
		
		this.name = new JLabel();
		this.name.setBounds(10, 10, 200, 20);
		this.add(name);
		
		this.table = new SelfCreateTable();
		this.table.setBounds(10, 40, 1010, 600);
		this.add(table);
		
	}
	
	public void initPage() {
		this.name.setText(User.USERNAME + "님 장학 내역");
		
		String[] colum = {"지급일", "내용", "금액 (원)"};
		String query = "select `when`,`content`,`money` from scholar where id='" + User.LOGINID + "'";
		this.table.makeTable(query);
		this.table.getTable().getColumnModel().getColumn(0).setHeaderValue(colum[0]);
		this.table.getTable().getColumnModel().getColumn(1).setHeaderValue(colum[1]);
		this.table.getTable().getColumnModel().getColumn(2).setHeaderValue(colum[2]);
		this.table.getTable().repaint();
	}
}
