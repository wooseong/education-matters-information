import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CompletionLectureMarks extends JPanel {
	/*
	 * ID text, PW text, Name text, Birth date, Enter date, College text, Dep text,
	 * Grade text, # # 학번 비번 이름 생일YMD 입학일YMD 대학 학과 학년 # )Listening_Class text,
	 * Fin_class text, scholar text, isPause boolean)" 수강중{수업:[중간,기말]}기이수{학수번호:학점}
	 * 장학{날짜:금액} is휴학 T/F
	 * 
	 */
	private JPanel 			informationPanel;
	private JLabel 			IDLabel, nameLabel, birthLabel, enterLabel, 
							collegeDepGradeLabel, isPauseLabel;
	
	private JTextField		IDTextField, nameTextField, birthTextField, enterTextField,
							collegeDepGradeTextField, isPauseTextField;
	
	private String 			IDStr, nameStr, birthStr, enterStr, collegeStr,
							DepStr, gradeStr, isPauseStr;
	
	public CompletionLectureMarks() {
		init();
		setPreferredSize(new Dimension(1030, 570));

		setBackground(new Color(0xDDDDDD));
		setLayout(null);

		informationPanel = new JPanel();
		informationPanel.setBounds(5, 5, 1020, 100);
		informationPanel.setBackground(new Color(0xEEEEEE));
		informationPanel.setLayout(null);
		informationPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.lightGray), "학생 기초 정보"));
		add(informationPanel);

		IDLabel = new JLabel("학번");
		IDLabel.setBounds(20, 20, 30, 30);
		informationPanel.add(IDLabel);

		IDTextField = new JTextField(IDStr);
		IDTextField.setBounds(60, 20, 100, 30);
		IDTextField.setEditable(false);
		informationPanel.add(IDTextField);
		
		nameLabel = new JLabel("성명");
		nameLabel.setBounds(190, 20, 30, 30);
		informationPanel.add(nameLabel);

		nameTextField = new JTextField(nameStr);
		nameTextField.setBounds(230, 20, 100, 30);
		nameTextField.setEditable(false);
		informationPanel.add(nameTextField);
		
		birthLabel = new JLabel("생년월일");
		birthLabel.setBounds(370, 20, 60, 30);
		informationPanel.add(birthLabel);

		birthTextField = new JTextField(birthStr);
		birthTextField.setBounds(440, 20, 100, 30);
		birthTextField.setEditable(false);
		informationPanel.add(birthTextField);
		
		enterLabel = new JLabel("입학일자");
		enterLabel.setBounds(570, 20, 60, 30);
		informationPanel.add(enterLabel);

		enterTextField = new JTextField(enterStr);
		enterTextField.setBounds(640, 20, 100, 30);
		enterTextField.setEditable(false);
		informationPanel.add(enterTextField);
		
		collegeDepGradeLabel = new JLabel("소속 및 학년");
		collegeDepGradeLabel.setBounds(20, 60, 80, 30);
		informationPanel.add(collegeDepGradeLabel);

		collegeDepGradeTextField = new JTextField(collegeStr + "  " + DepStr + "  " + gradeStr);
		collegeDepGradeTextField.setBounds(110, 60, 250, 30);
		collegeDepGradeTextField.setEditable(false);
		informationPanel.add(collegeDepGradeTextField);

		//enterLabel, collegeLabel, DepLabel, GradeLabel, isPauseLabel;
		}
	private void init() {
		IDStr ="16011037";
		nameStr = "신우성";
		birthStr ="1996-03-10";
		enterStr = "2016-03-02";
		collegeStr = "전자정보공학대학";
		DepStr = "컴퓨터공학과";
		gradeStr = "2학년";
		isPauseStr = "2";
	}
}