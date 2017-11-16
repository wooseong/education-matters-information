import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SubMainPage extends JPanel implements ActionListener {
	private JPanel comboBoxPanel;
	private TitledBorder studentInformTitle;// 테두리 상자
	private JLabel yearLabel, departmentLabel,
					classifyLabel, lectureNameLabel, professorLabel;
	private JComboBox yearCombo, classifyCombo;
	private JTextField departmentTextField,
					lectureNameTextField, professorTextField, textField;
	private JButton searchButton;

	private Main frame;

	public SubMainPage(Main frame) {
		this.frame = frame;
		setPreferredSize(new Dimension(1280, 720));

		setBackground(new Color(0xDDDDDD));
		setLayout(null);

		comboBoxPanel = new JPanel();
		comboBoxPanel.setBounds(5, 5, 1270, 140);
		comboBoxPanel.setBackground(new Color(0xDDDDDD));
		comboBoxPanel.setLayout(null);
		add(comboBoxPanel);

		studentInformTitle = new TitledBorder(new LineBorder(Color.black), "학생 상세 정보");
		studentInformTitle.setTitleColor(Color.black);
		comboBoxPanel.setBorder(studentInformTitle);

		yearLabel = new JLabel("년도/학기");
		yearLabel.setBounds(30, 30, 60, 30);
		comboBoxPanel.add(yearLabel);

		yearCombo = new JComboBox();
		yearCombo.addItem("2017/2학기");
		yearCombo.addItem("2017/1학기");
		yearCombo.setBounds(100, 30, 150, 30);
		yearCombo.setEditable(false);
		comboBoxPanel.add(yearCombo);

		departmentLabel = new JLabel("개설학과전공");
		departmentLabel.setBounds(280, 30, 80, 30);
		comboBoxPanel.add(departmentLabel);

		departmentTextField = new JTextField("컴퓨터공학과 [3210 학부] 소프트웨어융합대학");
		departmentTextField.setBounds(370, 30, 300, 30);
		departmentTextField.setEditable(false);
		comboBoxPanel.add(departmentTextField);

		classifyLabel = new JLabel("이수구분");
		classifyLabel.setBounds(30, 80, 60, 30);
		comboBoxPanel.add(classifyLabel);

		classifyCombo = new JComboBox();
		classifyCombo.addItem("전공필수");
		classifyCombo.addItem("전공선택");
		classifyCombo.setBounds(100, 80, 130, 30);
		classifyCombo.setEditable(false);
		comboBoxPanel.add(classifyCombo);

		lectureNameLabel = new JLabel("교과목명");
		lectureNameLabel.setBounds(260, 80, 60, 30);
		comboBoxPanel.add(lectureNameLabel);

		lectureNameTextField = new JTextField("");
		lectureNameTextField.setBounds(330, 80, 250, 30);
		lectureNameTextField.setEditable(true);
		comboBoxPanel.add(lectureNameTextField);

		professorLabel = new JLabel("교수명");
		professorLabel.setBounds(610, 80, 50, 30);
		comboBoxPanel.add(professorLabel);

		professorTextField = new JTextField("");
		professorTextField.setBounds(670, 80, 200, 30);
		professorTextField.setEditable(true);
		comboBoxPanel.add(professorTextField);

		searchButton = new JButton("조회");
		searchButton.setFont(new Font("나눔고딕", Font.BOLD, 30));
		searchButton.setBounds(1100, 30, 100, 60);
		comboBoxPanel.add(searchButton);
		
		textField = new JTextField();
		textField.setColumns(100);
		textField.setBounds(200, 500, 500, 30);
		textField.setEditable(false);
		add(textField);

		yearCombo.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == yearCombo) {
			// 항목의 반환형이 오브젝트의 참조값으로 반환되니 String으로 형변환 해줘야 합니다
			String str1 = (String) yearCombo.getSelectedItem(),
					str2 = (String) classifyCombo.getSelectedItem(),
					str3 = (String) departmentTextField.getText(),
					str4 = (String) lectureNameTextField.getText(),
					str5 = (String) professorTextField.getText(),
					str = str1 + " " + str2 + " " + str3 + " " + str4 + " " + str5;
			textField.setText(str);

			/*
			 * for(int i = 0; i < yearCombo.getItemCount() ; ++i) {
			 * if(((String)yearCombo.getItemAt(i)).compareTo(str) == 0) { //항목중 같은 이름이 있으면
			 * 추가하지 않습니다 return; } } yearCombo.addItem(str); //일치하는 항목이 없으면 입력한 내용을 콤보박스 항목에
			 * 추가합니다
			 */
		}
	}

}
