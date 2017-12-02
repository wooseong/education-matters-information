import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Applicationlecture extends JPanel{

	private JPanel						comboBoxPanel, applyJPanel, cancelJPanel;
	private JLabel 						yearLabel, departmentLabel, searchLabel, lectureNameLabel, classNumberLabel,
										lectureNumberLabel, professorNameLabel, classifyLabel, leftCreditLabel, leftCreditNumberLabel;
	private JComboBox 					whatSearchCombo, classifyCombo;
	private JTextField					yearTextField, departmentTextField, lectureNameTextField, classNumberTextField,
										lectureNumberTextField, professorNameTextField;
	private JButton 					searchButton, applyAllLectureButton, cancelLectureButton;
	private searchActionListener 		searchAction;
	private whatSearchActionListener 	whatSearchAction;
	private SelfCreateTable 			lecturetable, mytable;
	private JCheckBox 					checkAllLecture;
	
	public Applicationlecture() {
		setPreferredSize(new Dimension(1030, 570));

		setBackground(new Color(0xEEDDDD));
		setLayout(null);
				
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
		
		checkAllLecture = new JCheckBox();
		checkAllLecture.setBounds(5, 5, 20, 20);
		applyJPanel.add(checkAllLecture);
		
		applyAllLectureButton = new JButton("신청");
		applyAllLectureButton.setBounds(35, 5, 80, 20);
		applyAllLectureButton.setFont(new Font("나눔고딕", Font.BOLD, 15));
		applyJPanel.add(applyAllLectureButton);
		
		lecturetable = new SelfCreateTable();
		lecturetable.setBounds(5, 140, 1020, 200);
		this.add(lecturetable);

		cancelJPanel = new JPanel();
		cancelJPanel.setBounds(5, 345, 1020, 50);
		cancelJPanel.setLayout(null);
		cancelJPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), "수강내역"));
		add(cancelJPanel);
		
		cancelLectureButton = new JButton("취소");
		cancelLectureButton.setBounds(35, 25, 80, 20);
		cancelLectureButton.setFont(new Font("나눔고딕", Font.BOLD, 15));
		cancelJPanel.add(cancelLectureButton);
		
		leftCreditLabel = new JLabel("신청가능 학점");
		leftCreditLabel.setBounds(145, 25, 85, 20);
		cancelJPanel.add(leftCreditLabel);

		leftCreditNumberLabel = new JLabel("6 / 18");
		leftCreditNumberLabel.setBounds(235, 25, 60, 20);
		cancelJPanel.add(leftCreditNumberLabel);
		
		leftCreditLabel = new JLabel("신청 과목 수");
		leftCreditLabel.setBounds(300, 25, 80, 20);
		cancelJPanel.add(leftCreditLabel);

		leftCreditNumberLabel = new JLabel("4");
		leftCreditNumberLabel.setBounds(385, 25, 60, 20);
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
	                	else if(i==4) { //과심과목
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
	                	}
	                }
	            }				
			}
		}
	}
	public class searchActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
//			String 	stryear_semester= (String) yearTextField.getText(), // 학기/년도
//					strClassify 	= (String) whatSearchCombo.getSelectedItem(), // 이수구분
//					strLectureName 	= (String) lectureNameTextField.getText(), // 교과목명
//					strProfessor 	= (String) professorTextField.getText(), // 교수명
//					str 			= " " + stryear_semester + " " + strClassify + " " + strLectureName + " " + strProfessor;
//			
//			String select = "number, class, name, classify, grade, credit, credit_theory, credit_practice, lectureTime, lecture_room, prof, language";
//			String query = "select " + select + " from lecture where classify='" + strClassify + "' and  year='" + stryear_semester.substring(0, 6) + "' and name like '" + strLectureName + "%' and prof like '" + strProfessor + "%'";
//			System.out.println(query);
//			
//			table.makeTable(query);

			}
	}
}