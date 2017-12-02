import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class InterestLecture extends JPanel{

	private JPanel						comboBoxPanel, applyJPanel, cancelJPanel;
	private JLabel 						yearLabel, departmentLabel, searchLabel, lectureNameLabel, classNumberLabel,
										lectureNumberLabel, professorNameLabel, classifyLabel, leftCreditLabel, leftCreditNumberLabel;
	private JComboBox 					whatSearchCombo, classifyCombo;
	private JTextField					yearTextField, departmentTextField, lectureNameTextField, classNumberTextField,
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
	
	public InterestLecture() {
		setPreferredSize(new Dimension(1030, 570));

		setBackground(new Color(0xEEDDDD));
		setLayout(null);
				
		comboBoxPanel = new JPanel();
		comboBoxPanel.setBounds(5, 5, 1020, 100);
		comboBoxPanel.setBackground(new Color(0xEEEEEE));
		comboBoxPanel.setLayout(null);
		comboBoxPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), "관심과목담기"));
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
		whatSearchCombo.setBounds(85, 60, 120, 25);
		whatSearchCombo.setEditable(false);
		whatSearchCombo.addActionListener(whatSearchAction);
		comboBoxPanel.add(whatSearchCombo);

		lectureNumberLabel = new JLabel("학수번호");
		lectureNumberLabel.setBounds(235, 60, 60, 20);
		lectureNumberLabel.setVisible(true);
		comboBoxPanel.add(lectureNumberLabel);

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

		classNumberTextField = new JTextField("");
		classNumberTextField.setBounds(445, 60, 50, 25);
		classNumberTextField.setEditable(true);
		classNumberTextField.setVisible(true);
		comboBoxPanel.add(classNumberTextField);

		lectureNameLabel = new JLabel("과목명");
		lectureNameLabel.setBounds(235, 60, 45, 20);
		lectureNameLabel.setVisible(false);
		comboBoxPanel.add(lectureNameLabel);

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

		classifyCombo = new JComboBox();
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

		applyJPanel = new JPanel();
		applyJPanel.setBounds(5, 110, 1020, 30);
		applyJPanel.setLayout(null);
		add(applyJPanel);
		
		checkBoxAction = new allLectureCheckBoxListener();
		
		checkAllLecture = new JCheckBox();
		checkAllLecture.setBounds(25, 5, 20, 20);
		checkAllLecture.addActionListener(checkBoxAction);
		applyJPanel.add(checkAllLecture);
		
		applyLectureAction = new applyLectureListener();
		
		applyAllLectureButton = new JButton("담기");
		applyAllLectureButton.setBounds(70, 5, 65, 20);
		applyAllLectureButton.setFont(new Font("나눔고딕", Font.BOLD, 15));
		applyAllLectureButton.addActionListener(applyLectureAction);
		applyJPanel.add(applyAllLectureButton);
		
		lecturetable = new SelfCreateTable();
		lecturetable.setBounds(5, 140, 1020, 200);
		this.add(lecturetable);

		cancelJPanel = new JPanel();
		cancelJPanel.setBounds(5, 345, 1020, 50);
		cancelJPanel.setLayout(null);
		cancelJPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), "관심과목내역"));
		add(cancelJPanel);
				
		leftCreditLabel = new JLabel("담긴과목 학점");
		leftCreditLabel.setBounds(10, 25, 85, 20);
		cancelJPanel.add(leftCreditLabel);

		leftCreditNumberLabel = new JLabel("6 / 24");
		leftCreditNumberLabel.setBounds(100, 25, 60, 20);
		cancelJPanel.add(leftCreditNumberLabel);
		
		leftCreditLabel = new JLabel("신청 과목 수");
		leftCreditLabel.setBounds(180, 25, 80, 20);
		cancelJPanel.add(leftCreditLabel);

		leftCreditNumberLabel = new JLabel("4");
		leftCreditNumberLabel.setBounds(265, 25, 60, 20);
		cancelJPanel.add(leftCreditNumberLabel);
				
		mytable = new SelfCreateTable();
		mytable.setBounds(5, 395, 1020, 200);
		this.add(mytable);

	}
	public class whatSearchActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == whatSearchCombo)
			{
	            String str = (String)whatSearchCombo.getSelectedItem();
	            for(int i = 0; i < whatSearchCombo.getItemCount() ; ++i)
	            {
	                if(((String)whatSearchCombo.getItemAt(i)).equals(str))
	                {
	                	if(i==0){ //학수번호
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
	                	}
	                	else if(i==1) { //과목명
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
	                	}
	                	else if(i==2) { //교수명
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
	                	}
	                	else if(i==3) { //이수구분
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
	                	}
	                }
	            }				
			}
		}
	}
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
	public class applyLectureListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[][] applyLectures = lecturetable.getChecked();
			String 	stryear_semester	= (String) yearTextField.getText(); // 학기/년도
			for(String[] temp:applyLectures) {
				try {
					System.out.println(1);
					System.out.println(DBController.ClassEnroll("1", temp[2], temp[3], stryear_semester.substring(0, 6), "4"));
					System.out.println(2);
//					DBController.ClassEnroll("1", temp[2], temp[3], stryear_semester.substring(0, 6), "4");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	
			}			
		}
	}
}