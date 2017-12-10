import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 장학 페이지
 * @author Bang
 *
 */
public class ScholarPage extends JPanel {
	private JLabel name;
	private SelfCreateTable table;
	
	/**
	 * 초기 생성시 데이터 하나도 없으므로 페이지 부르기 전 initPage() 호출해야 함
	 */
	public ScholarPage() {
		this.setLayout(null);
		
		this.name = new JLabel();
		this.name.setBounds(10, 10, 200, 20);
		this.add(name);
		
		this.table = new SelfCreateTable();
		this.table.setBounds(10, 40, 1010, 600);
		this.add(table);
		
	}
	
	/**
	 * 페이지에 데이터 추가하는 메소드
	 * 
	 * 이름 출력과 함께 장학 목록이 테이블 형식으로 출력됨
	 */
	public void initPage() {
		this.name.setText(User.USERNAME + "님 장학 내역");
		
		String[] colum = {"지급일", "내용", "금액 (원)"};
		String query = "select `when`,`content`,`money` from scholar where id='" + User.LOGINID + "'";
		this.table.makeTable(query);
		
		// 이 부분은 테이블 컬럼 헤더 이름 변경하는 부분
		this.table.getTable().getColumnModel().getColumn(0).setHeaderValue(colum[0]);
		this.table.getTable().getColumnModel().getColumn(1).setHeaderValue(colum[1]);
		this.table.getTable().getColumnModel().getColumn(2).setHeaderValue(colum[2]);
		this.table.getTable().repaint();
	}
}