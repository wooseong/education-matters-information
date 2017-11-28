import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class SearchLecture extends JPanel {
	private JPanel comboBoxPanel;
	private JLabel yearLabel, departmentLabel,
					classifyLabel, lectureNameLabel, professorLabel;
	private JComboBox yearCombo, classifyCombo;
	private JTextField departmentTextField,
					lectureNameTextField, professorTextField, textField;
	private JButton searchButton;

	SelfCreateTable table;
	
	public SearchLecture() {
		setPreferredSize(new Dimension(1030, 570));

		setBackground(new Color(0xDDDDDD));
		setLayout(null);

		comboBoxPanel = new JPanel();
		comboBoxPanel.setBounds(5, 5, 1020, 100);
		comboBoxPanel.setBackground(new Color(0xDDDDDD));
		comboBoxPanel.setLayout(null);
		comboBoxPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.black), "강좌 조회"));
		add(comboBoxPanel);

		yearLabel = new JLabel("년도/학기");
		yearLabel.setBounds(20, 20, 60, 20);
		comboBoxPanel.add(yearLabel);

		yearCombo = new JComboBox();
		yearCombo.addItem("2017/2학기");
		yearCombo.addItem("2017/1학기");
		yearCombo.setBounds(90, 20, 120, 25);
		yearCombo.setEditable(false);
		comboBoxPanel.add(yearCombo);

		departmentLabel = new JLabel("개설학과전공");
		departmentLabel.setBounds(240, 20, 80, 20);
		comboBoxPanel.add(departmentLabel);

		departmentTextField = new JTextField("컴퓨터공학과 [2922 학부] 전자정보공학대학");
		departmentTextField.setBounds(330, 20, 300, 20);
		departmentTextField.setEditable(false);
		comboBoxPanel.add(departmentTextField);

		classifyLabel = new JLabel("이수구분");
		classifyLabel.setBounds(20, 60, 60, 20);
		comboBoxPanel.add(classifyLabel);

		classifyCombo = new JComboBox();
		classifyCombo.addItem("전공필수");
		classifyCombo.addItem("전공선택");
		classifyCombo.setBounds(90, 60, 120, 25);
		classifyCombo.setEditable(false);
		comboBoxPanel.add(classifyCombo);

		lectureNameLabel = new JLabel("교과목명");
		lectureNameLabel.setBounds(240, 60, 60, 20);
		comboBoxPanel.add(lectureNameLabel);

		lectureNameTextField = new JTextField("");
		lectureNameTextField.setBounds(310, 60, 230, 25);
		lectureNameTextField.setEditable(true);
		comboBoxPanel.add(lectureNameTextField);

		professorLabel = new JLabel("교수명");
		professorLabel.setBounds(570, 60, 50, 20);
		comboBoxPanel.add(professorLabel);

		professorTextField = new JTextField("");
		professorTextField.setBounds(630, 60, 200, 25);
		professorTextField.setEditable(true);
		comboBoxPanel.add(professorTextField);

		searchButton = new JButton("조회");
		searchButton.setFont(new Font("나눔고딕", Font.BOLD, 15));
		searchButton.setBounds(900, 20, 80, 30);
		comboBoxPanel.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String 	str1 			= (String) yearCombo.getSelectedItem(), // 학기/년도
						strClassify 	= (String) classifyCombo.getSelectedItem(), // 이수구분
						str3 			= (String) departmentTextField.getText(), // 개설학과전공
						strLectureName 	= (String) lectureNameTextField.getText(), // 교과목명
						strProfessor 	= (String) professorTextField.getText(), // 교수명
						str 			= " " + str1 + " " + strClassify + " " + str3 + " " + strLectureName + " " + strProfessor;
				
				String query = "select * from Lecture where Classify='" + strClassify + "', Name like '" + strLectureName + "%', prof like '" + strProfessor + "%'";
			
				textField.setText(str);
			}
		});
		
		textField = new JTextField();
		textField.setColumns(100);
		textField.setBounds(200, 500, 500, 30);
		textField.setEditable(false);
		add(textField);
	}
}
