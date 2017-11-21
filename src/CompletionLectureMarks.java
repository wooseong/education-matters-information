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

public class CompletionLectureMarks extends JPanel{
	/*
	 * ID text, PW text, Name text, Birth date, Enter date, College text, Dep text, Grade text, Listening_Class text, Fin_class text, scholar text, isPause boolean)"
#     #   학번     비번                            이름          생일YMD      입학일YMD      대학                             학과                 학년      수강중{수업:[중간,기말]}기이수{학수번호:학점} 장학{날짜:금액}        is휴학 T/F
# )
	 */
	private JPanel informationPanel;
	private JLabel IDLabel, nameLabel, birthLabel, enterLabel, collegeLabel,
					classifyLabel, lectureNameLabel, professorLabel;
	private JTextField departmentTextField,
					lectureNameTextField, professorTextField, textField;
	private JButton searchButton;

	public CompletionLectureMarks() {
		setPreferredSize(new Dimension(1030, 570));

		setBackground(new Color(0xDDDDDD));
		setLayout(null);

		informationPanel = new JPanel();
		informationPanel.setBounds(5, 5, 1020, 100);
		informationPanel.setBackground(new Color(0xDDDDDD));
		informationPanel.setLayout(null);
		informationPanel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.lightGray), "학생 기초 정보"));
		add(informationPanel);

		yearLabel = new JLabel("학번");
		yearLabel.setBounds(20, 20, 60, 30);
		informationPanel.add(yearLabel);

		yearCombo = new JComboBox();
		yearCombo.addItem("2017/2학기");
		yearCombo.addItem("2017/1학기");
		yearCombo.setBounds(90, 20, 120, 30);
		yearCombo.setEditable(false);
		comboBoxPanel.add(yearCombo);

		departmentLabel = new JLabel("개설학과전공");
		departmentLabel.setBounds(240, 20, 80, 30);
		comboBoxPanel.add(departmentLabel);

		departmentTextField = new JTextField("컴퓨터공학과 [2922 학부] 전자정보공학대학");
		departmentTextField.setBounds(330, 20, 300, 30);
		departmentTextField.setEditable(false);
		comboBoxPanel.add(departmentTextField);

		classifyLabel = new JLabel("이수구분");
		classifyLabel.setBounds(20, 60, 60, 30);
		comboBoxPanel.add(classifyLabel);

		classifyCombo = new JComboBox();
		classifyCombo.addItem("전공필수");
		classifyCombo.addItem("전공선택");
		classifyCombo.setBounds(90, 60, 130, 30);
		classifyCombo.setEditable(false);
		comboBoxPanel.add(classifyCombo);

		lectureNameLabel = new JLabel("교과목명");
		lectureNameLabel.setBounds(250, 60, 60, 30);
		comboBoxPanel.add(lectureNameLabel);

		lectureNameTextField = new JTextField("");
		lectureNameTextField.setBounds(320, 60, 230, 30);
		lectureNameTextField.setEditable(true);
		comboBoxPanel.add(lectureNameTextField);

		professorLabel = new JLabel("교수명");
		professorLabel.setBounds(580, 60, 50, 30);
		comboBoxPanel.add(professorLabel);

		professorTextField = new JTextField("");
		professorTextField.setBounds(640, 60, 200, 30);
		professorTextField.setEditable(true);
		comboBoxPanel.add(professorTextField);

		searchButton = new JButton("조회");
		searchButton.setFont(new Font("나눔고딕", Font.BOLD, 15));
		searchButton.setBounds(900, 20, 80, 30);
		comboBoxPanel.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str1 = (String) yearCombo.getSelectedItem(),
						str2 = (String) classifyCombo.getSelectedItem(),
						str3 = (String) departmentTextField.getText(),
						str4 = (String) lectureNameTextField.getText(),
						str5 = (String) professorTextField.getText(),
						str = " " + str1 + " " + str2 + " " + str3 + " " + str4 + " " + str5;
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
