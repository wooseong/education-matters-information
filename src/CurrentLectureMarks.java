import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 * 당학기 성적 조회 페이지
 * 기본적인 내용은 기이수 성적 조회와 일치하나 2017/2 의 성적을 불러옴
 * 페이지 호출 전 init() 메소드 호출 필요
 * @author bang
 *
 */
public class CurrentLectureMarks extends JPanel {
	private JPanel 			informationPanel;
	private JLabel 			IDLabel, nameLabel, birthLabel, enterLabel, 
							collegeDepGradeLabel;
	
	private JTextField		IDTextField, nameTextField, birthTextField, enterTextField,
							collegeDepGradeTextField;
	
	private SelfCreateTable table;
	
	public CurrentLectureMarks() {
		setBackground(new Color(0xEEDDDD));
		setLayout(null);

		// 학생 기초정보 묶어둔 패널
		informationPanel = new JPanel();
		informationPanel.setBounds(5, 5, 1020, 100);
		informationPanel.setBackground(new Color(0xEEEEEE));
		informationPanel.setLayout(null);
		informationPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.lightGray), "학생 기초 정보"));
		add(informationPanel);

		IDLabel = new JLabel("학번");
		IDLabel.setBounds(20, 20, 30, 30);
		informationPanel.add(IDLabel);

		// 학번 표시용 텍스트 필드
		IDTextField = new JTextField("");
		IDTextField.setBounds(60, 20, 100, 30);
		IDTextField.setEditable(false);
		informationPanel.add(IDTextField);
		
		nameLabel = new JLabel("성명");
		nameLabel.setBounds(190, 20, 30, 30);
		informationPanel.add(nameLabel);

		// 이름 표시용 텍스트 필드
		nameTextField = new JTextField("");
		nameTextField.setBounds(230, 20, 100, 30);
		nameTextField.setEditable(false);
		informationPanel.add(nameTextField);
		
		birthLabel = new JLabel("생년월일");
		birthLabel.setBounds(370, 20, 60, 30);
		informationPanel.add(birthLabel);

		// 생일 표시용 텍스트필드
		birthTextField = new JTextField("");
		birthTextField.setBounds(440, 20, 100, 30);
		birthTextField.setEditable(false);
		informationPanel.add(birthTextField);
		
		enterLabel = new JLabel("입학일자");
		enterLabel.setBounds(570, 20, 60, 30);
		informationPanel.add(enterLabel);

		// 입학일 표시용 텍스트필드
		enterTextField = new JTextField("");
		enterTextField.setBounds(640, 20, 100, 30);
		enterTextField.setEditable(false);
		informationPanel.add(enterTextField);
		
		collegeDepGradeLabel = new JLabel("소속 및 학년");
		collegeDepGradeLabel.setBounds(20, 60, 80, 30);
		informationPanel.add(collegeDepGradeLabel);

		// 소속 및 학년 표시용 텍스트필드
		collegeDepGradeTextField = new JTextField("");
		collegeDepGradeTextField.setBounds(110, 60, 250, 30);
		collegeDepGradeTextField.setEditable(false);
		informationPanel.add(collegeDepGradeTextField);

		// 성적 표시용 테이블
		table = new SelfCreateTable();
		table.setBounds(5, 110, 1020, 535);
		this.add(table);
	}
	
	/**
	 * 페이지 데이터 추가하는 초기화 메소드
	 */
	public void init() {
		// 학생 정보 추가
		this.birthTextField.setText(User.BIRTH);
		this.collegeDepGradeTextField.setText(User.COLLEGE + "  " + User.MAJOR + "  " + User.GRADE);
		this.enterTextField.setText(User.ENTER);
		this.IDTextField.setText(User.LOGINID);
		this.nameTextField.setText(User.USERNAME);
		
		String query = "select `2017/2`.lecture, `2017/2`.class, `lecture`.name, `2017/2`.midterm, `2017/2`.final, `2017/2`.score ";
		query		+= "from `2017/2` inner join `lecture` on `2017/2`.lecture=`lecture`.number ";
		query		+= "where `2017/2`.id='" + User.LOGINID + "'";
		
		this.table.makeTable(query);
		
		// 테이블 컬럼 헤더의 내용 즉 제목 바꾸기 위한 부분
		String[] colum = {"학수번호", "분반", "수업명", "중간고사", "기말고사", "학점"};
		for(int i=0; i<colum.length; i++) {
			this.table.getTable().getColumnModel().getColumn(i).setHeaderValue(colum[i]);
		}
	}
}
