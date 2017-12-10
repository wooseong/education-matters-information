import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * 수강신청용 페이지
 * 기능1 : 수업 목록 조회
 * 기능2 : 선택된 수업 수강신청
 * 기능3 : 개별 수강신청
 * 기능4 : 수강신청 후 즉시 아래쪽 테이블 생성
 * @author bang
 *
 */
public class Applicationlecture extends JPanel{//수강신청 페이지

	private JPanel						comboBoxPanel, applyJPanel, cancelJPanel;
	
	private JLabel 						yearLabel, departmentLabel, searchLabel, 
										lectureNameLabel, classNumberLabel,
										lectureNumberLabel, professorNameLabel,
										classifyLabel, leftCreditLabel,
										leftCreditNumberLabel;
	
	private JComboBox 					whatSearchCombo, classifyCombo;
	
	private JTextField					yearTextField, departmentTextField,
										lectureNameTextField, classNumberTextField,
										lectureNumberTextField, professorNameTextField;
	
	private JButton 					searchButton, applyAllLectureButton;
	private searchActionListener 		searchAction;
	private whatSearchActionListener 	whatSearchAction;
	private applyLectureListener		applyLectureAction;
	private SelfCreateTable 			lecturetable, mytable;
	private JCheckBox 					checkAllLecture;
	private allLectureCheckBoxListener  checkBoxAction;
	private int 						comboBoxIndex;
	private String 						query;
	
	public Applicationlecture() {
		setPreferredSize(new Dimension(1030, 570));

		setBackground(new Color(0xEEDDDD));
		setLayout(null);
		
		// 상단 조회 부분 패널로 묶음
		comboBoxPanel = new JPanel();
		comboBoxPanel.setBounds(5, 5, 1020, 100);
		comboBoxPanel.setBackground(new Color(0xEEEEEE));
		comboBoxPanel.setLayout(null);
		comboBoxPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), "수강신청"));
		add(comboBoxPanel);

		yearLabel = new JLabel("년도/학기");
		yearLabel.setBounds(20, 20, 60, 20);
		comboBoxPanel.add(yearLabel);

		yearTextField = new JTextField("2017/2학기");
		yearTextField.setBounds(85, 20, 120, 25);
		yearTextField.setEditable(false);
		comboBoxPanel.add(yearTextField);

		departmentLabel = new JLabel("개설학과전공");
		departmentLabel.setBounds(235, 20, 80, 20);
		comboBoxPanel.add(departmentLabel);

		departmentTextField = new JTextField("컴퓨터공학과 [2922 학부] 전자정보공학대학");
		departmentTextField.setBounds(320, 20, 300, 25);
		departmentTextField.setEditable(false);
		comboBoxPanel.add(departmentTextField);

		searchLabel = new JLabel("검색구분");
		searchLabel.setBounds(20, 60, 60, 20);
		comboBoxPanel.add(searchLabel);

		whatSearchAction = new whatSearchActionListener();
		
		whatSearchCombo = new JComboBox();
		whatSearchCombo.addItem("학수번호 검색");
		whatSearchCombo.addItem("과목명 검색");
		whatSearchCombo.addItem("교수명 검색");
		whatSearchCombo.addItem("이수구분 검색");
		whatSearchCombo.addItem("관심과목 검색");
		whatSearchCombo.setBounds(85, 60, 120, 25);
		whatSearchCombo.setEditable(false);
		whatSearchCombo.addActionListener(whatSearchAction);
		comboBoxPanel.add(whatSearchCombo);

		lectureNumberLabel = new JLabel("학수번호");
		lectureNumberLabel.setBounds(235, 60, 60, 20);
		lectureNumberLabel.setVisible(true);
		comboBoxPanel.add(lectureNumberLabel);

		// 학수번호 입력 란
		lectureNumberTextField = new JTextField("");
		lectureNumberTextField.setBounds(300, 60, 100, 25);
		lectureNumberTextField.setFont(new Font("나눔고딕", Font.BOLD, 18));
		lectureNumberTextField.setEditable(true);
		lectureNumberTextField.setVisible(true);
		comboBoxPanel.add(lectureNumberTextField);

		classNumberLabel = new JLabel("분반");
		classNumberLabel.setBounds(410, 60, 30, 20);
		classNumberLabel.setVisible(true);
		comboBoxPanel.add(classNumberLabel);

		// 분반 입력 란
		classNumberTextField = new JTextField("");
		classNumberTextField.setBounds(445, 60, 50, 25);
		classNumberTextField.setEditable(true);
		classNumberTextField.setVisible(true);
		comboBoxPanel.add(classNumberTextField);

		lectureNameLabel = new JLabel("과목명");
		lectureNameLabel.setBounds(235, 60, 45, 20);
		lectureNameLabel.setVisible(false);
		comboBoxPanel.add(lectureNameLabel);

		// 수업명 입력 란
		lectureNameTextField = new JTextField("");
		lectureNameTextField.setBounds(285, 60, 200, 25);
		lectureNameTextField.setFont(new Font("나눔고딕", Font.BOLD, 18));
		lectureNameTextField.setEditable(true);
		lectureNameTextField.setVisible(false);
		comboBoxPanel.add(lectureNameTextField);

		professorNameLabel = new JLabel("교수명");
		professorNameLabel.setBounds(235, 60, 45, 20);
		professorNameLabel.setVisible(false);
		comboBoxPanel.add(professorNameLabel);

		// 교수명 입력 
		professorNameTextField = new JTextField("");
		professorNameTextField.setBounds(285, 60, 100, 25);
		professorNameTextField.setFont(new Font("나눔고딕", Font.BOLD, 18));
		professorNameTextField.setEditable(true);
		professorNameTextField.setVisible(false);
		comboBoxPanel.add(professorNameTextField);
		
		classifyLabel = new JLabel("이수구분");
		classifyLabel.setBounds(235, 60, 60, 20);
		classifyLabel.setVisible(false);
		comboBoxPanel.add(classifyLabel);

		// 이수구분 선택 콤보박스
		classifyCombo = new JComboBox();
		classifyCombo.addItem("전체");
		classifyCombo.addItem("전공필수");
		classifyCombo.addItem("전공선택");
		classifyCombo.setBounds(300, 60, 100, 25);
		classifyCombo.setEditable(false);
		classifyCombo.setVisible(false);
		comboBoxPanel.add(classifyCombo);
		
		searchAction = new searchActionListener();
		
		searchButton = new JButton("검색");
		searchButton.setFont(new Font("나눔고딕", Font.BOLD, 15));
		searchButton.setBounds(900, 20, 80, 30);
		searchButton.addActionListener(searchAction);
		comboBoxPanel.add(searchButton);

		// 조회된 수업 목록 나오는 패널
		applyJPanel = new JPanel();
		applyJPanel.setBounds(5, 110, 1020, 30);
		applyJPanel.setLayout(null);
		add(applyJPanel);
		
		checkBoxAction = new allLectureCheckBoxListener();
		
		// 하단 수업 전부 체크
		checkAllLecture = new JCheckBox();
		checkAllLecture.setBounds(25, 5, 20, 20);
		checkAllLecture.addActionListener(checkBoxAction);
		applyJPanel.add(checkAllLecture);
		
		applyLectureAction = new applyLectureListener();
		
		applyAllLectureButton = new JButton("신청");
		applyAllLectureButton.setBounds(70, 5, 65, 20);
		applyAllLectureButton.setFont(new Font("나눔고딕", Font.BOLD, 15));
		applyAllLectureButton.addActionListener(applyLectureAction);
		applyJPanel.add(applyAllLectureButton);
		
		// 수업 목록 테이블
		// 생성시에는 데이터 없지만 조회 후 데이터 생성
		lecturetable = new SelfCreateTable();
		lecturetable.setBounds(5, 140, 1020, 200);
		this.add(lecturetable);

		// 수강신청 된 수업 목록 보여주는 패널
		cancelJPanel = new JPanel();
		cancelJPanel.setBounds(5, 345, 1020, 50);
		cancelJPanel.setLayout(null);
		cancelJPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), "수강내역"));
		add(cancelJPanel);
		
		leftCreditLabel = new JLabel("신청가능 학점");
		leftCreditLabel.setBounds(10, 25, 85, 20);
		cancelJPanel.add(leftCreditLabel);

		leftCreditNumberLabel = new JLabel("6 / 18");
		leftCreditNumberLabel.setBounds(100, 25, 60, 20);
		cancelJPanel.add(leftCreditNumberLabel);
		
		leftCreditLabel = new JLabel("신청 과목 수");
		leftCreditLabel.setBounds(180, 25, 80, 20);
		cancelJPanel.add(leftCreditLabel);

		leftCreditNumberLabel = new JLabel("4");
		leftCreditNumberLabel.setBounds(265, 25, 60, 20);
		cancelJPanel.add(leftCreditNumberLabel);
		
		// 신청 된 수업목록 보여주는 패널
		mytable = new SelfCreateTable();
		mytable.setBounds(5, 395, 1020, 200);
		this.add(mytable);

	}
	
	/**
	 * 
	 * @author wooseong
	 *
	 */
	public class whatSearchActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == whatSearchCombo)
			{
	            String str = (String)whatSearchCombo.getSelectedItem();
	            for(comboBoxIndex = 0; comboBoxIndex < whatSearchCombo.getItemCount() ; comboBoxIndex++)
	            {
	                if(((String)whatSearchCombo.getItemAt(comboBoxIndex)).equals(str))
	                {
	                	if(comboBoxIndex==0){ //학수번호
	                		lectureNumberLabel.setVisible(true);
	                		lectureNumberTextField.setVisible(true);
	                		classNumberLabel.setVisible(true);
	                		classNumberTextField.setVisible(true);
	                		
	                		lectureNameLabel.setVisible(false);
	                		lectureNameTextField.setVisible(false);
	                		
	                		professorNameTextField.setVisible(false);
	                		professorNameTextField.setVisible(false);
	                		
	                		classifyLabel.setVisible(false);
	                		classifyCombo.setVisible(false);
	                		break;
	                	}
	                	else if(comboBoxIndex==1) { //과목명
	                		lectureNumberLabel.setVisible(false);
	                		lectureNumberTextField.setVisible(false);
	                		classNumberLabel.setVisible(false);
	                		classNumberTextField.setVisible(false);
	                		
	                		lectureNameLabel.setVisible(true);
	                		lectureNameTextField.setVisible(true);
	                	
	                		professorNameLabel.setVisible(false);
	                		professorNameTextField.setVisible(false);

	                		classifyLabel.setVisible(false);
	                		classifyCombo.setVisible(false);
	                		break;
	                	}
	                	else if(comboBoxIndex==2) { //교수명
	                		lectureNumberLabel.setVisible(false);
	                		lectureNumberTextField.setVisible(false);
	                		classNumberLabel.setVisible(false);
	                		classNumberTextField.setVisible(false);
	                		
	                		lectureNameLabel.setVisible(false);
	                		lectureNameTextField.setVisible(false);
	                	
	                		professorNameLabel.setVisible(true);
	                		professorNameTextField.setVisible(true);
	                		
	                		classifyLabel.setVisible(false);
	                		classifyCombo.setVisible(false);
	                		break;
	                	}
	                	else if(comboBoxIndex==3) { //이수구분
	                		lectureNumberLabel.setVisible(false);
	                		lectureNumberTextField.setVisible(false);
	                		classNumberLabel.setVisible(false);
	                		classNumberTextField.setVisible(false);
	                		
	                		lectureNameLabel.setVisible(false);
	                		lectureNameTextField.setVisible(false);
	                	
	                		professorNameLabel.setVisible(false);
	                		professorNameTextField.setVisible(false);
	                		
	                		classifyLabel.setVisible(true);
	                		classifyCombo.setVisible(true);
	                		break;
	                	}
	                	else if(comboBoxIndex==4) { //과심과목
	                		lectureNumberLabel.setVisible(false);
	                		lectureNumberTextField.setVisible(false);
	                		classNumberLabel.setVisible(false);
	                		classNumberTextField.setVisible(false);
	                		
	                		lectureNameLabel.setVisible(false);
	                		lectureNameTextField.setVisible(false);
	                	
	                		professorNameLabel.setVisible(false);
	                		professorNameTextField.setVisible(false);
	                		
	                		classifyLabel.setVisible(false);
	                		classifyCombo.setVisible(false);
	                		break;
	                	}
	                }
	            }				
			}
		}
	}
	
	/**
	 * 수업 검색 이벤트 핸들러
	 * 쿼리 생성 후 조회된 데이터 테이블에 보여주는 형식
	 * @author bang
	 *
	 */
	public class searchActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String 	stryear_semester	= (String) yearTextField.getText(); // 학기/년도
			String select = "number, class, name, classify, grade, credit, credit_theory, credit_practice, maxStudent, lectureTime, lecture_room, prof, language";
			if(comboBoxIndex==0) { //검색하고자 하는 것이 학수번호인 경우
				String strlectureNumber	= (String) lectureNumberTextField.getText(), // 학수번호
						strclassNumber	= (String) classNumberTextField.getText(); // 분반
				query = "select " + select + " from lecture where year='" + stryear_semester.substring(0, 6) + "' and number like '" + strlectureNumber + "%' and class like '" + strclassNumber + "%'";
			}
			else if(comboBoxIndex==1) { //검색하고자 하는 것이 과목명인 경우
				String strlectureName = (String) lectureNameTextField.getText(); //과목명
				query = "select " + select + " from lecture where year='" + stryear_semester.substring(0, 6) + "' and name like '" + strlectureName  + "%'";
			}
			else if(comboBoxIndex==2) { //검색하고자 하는 것이 교수명인 경우
				String strprofessorName = (String) professorNameTextField.getText(); //교수명
				query = "select " + select + " from lecture where year='" + stryear_semester.substring(0, 6) + "' and prof like '" + strprofessorName  + "%'";
			}
			else if(comboBoxIndex==3) { //검색하고자 하는 것이 이수구분인 경우
				String strClassify = (String) classifyCombo.getSelectedItem(); // 이수구분
				if(strClassify.equals("전체")){
					query = "select " + select + " from lecture where year='" + stryear_semester.substring(0, 6)+ "'";
				}
				else {
					query = "select " + select + " from lecture where year='" + stryear_semester.substring(0, 6) + "' and classify='" + strClassify+ "'";
				}
			}
			else if(comboBoxIndex==4) { //검색하고자 하는 것이 관심과목인 경우
        	}			
			
			DBController db = new DBController(DBconf.DB);
			
			System.out.println(query);
			
			db.executeQuery(query);
			String[][] str=null;
			try {
				str = DBController.toStringList(db.getResultSet());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Object[][] newStr = new Object[str.length][str[0].length+2];
			
			for(int i=0; i<newStr.length; i++) {
				newStr[i][0] = false;
				newStr[i][1] = "신청";
				for(int j=2; j<newStr[i].length; j++) {
					newStr[i][j] = str[i][j-2];
				}
			}
			Object[] columName = {"체크", "신청", "학수번호", "분반", "수업명", "이수구분", "대상학년", "학점", "이론", "실습", "정원","수업시간", "강의실", "교수", "언어"};
			lecturetable.makeCheckboxTable(newStr, columName);
			db.disconnectDB();

		}
	}

	/**
	 * 수업 전체 체크용 리스너
	 * @author bang
	 *
	 */
	public class allLectureCheckBoxListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(checkAllLecture.isSelected())
			{
                for(int i=0;i<lecturetable.getTable().getRowCount();i++)
                	lecturetable.getTable().getModel().setValueAt(true, i, 0);
			}else {
                for(int i=0;i<lecturetable.getTable().getRowCount();i++)
                	lecturetable.getTable().getModel().setValueAt(false, i, 0);
			}
		}
	}

	/**
	 * 수강신청용 이벤트 리스너
	 * 수강신청 후 하단 수강신청된 테이블에 신청 목록 업데이트해서 보여줌
	 * @author bang
	 *
	 */
	public class applyLectureListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[][] applyLectures = lecturetable.getChecked();
			String 	stryear_semester	= (String) yearTextField.getText(); // 학기/년도
			for(String[] temp:applyLectures) {
				try {
					// 실제 수강신청하는 쿼리 실행하는 static 메소드
					DBController.ClassEnroll("1", temp[2], temp[3], stryear_semester.substring(0, 6), "4");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				String select = "number, class, name, classify, grade, credit, credit_theory, credit_practice, maxStudent, lectureTime, lecture_room, prof, language";
				String query = "select " + select + " from lecture where number in (select lecture from '" + stryear_semester.substring(0, 6) + "' where id='1') and class in (select class from '" + stryear_semester.substring(0, 6) + "' where id='1')";
				mytable.makeTable(query);
			}			
		}
	}
}
