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
	private JPanel 			informationPanel;
	private JLabel 			IDLabel, nameLabel, birthLabel, enterLabel, 
							collegeDepGradeLabel, isPauseLabel;
	
	private JTextField		IDTextField, nameTextField, birthTextField, enterTextField,
							collegeDepGradeTextField, isPauseTextField;
	
	public CompletionLectureMarks() {
		setPreferredSize(new Dimension(1030, 570));

		setBackground(new Color(0xEEDDDD));
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

		IDTextField = new JTextField("");
		IDTextField.setBounds(60, 20, 100, 30);
		IDTextField.setEditable(false);
		informationPanel.add(IDTextField);
		
		nameLabel = new JLabel("성명");
		nameLabel.setBounds(190, 20, 30, 30);
		informationPanel.add(nameLabel);

		nameTextField = new JTextField("");
		nameTextField.setBounds(230, 20, 100, 30);
		nameTextField.setEditable(false);
		informationPanel.add(nameTextField);
		
		birthLabel = new JLabel("생년월일");
		birthLabel.setBounds(370, 20, 60, 30);
		informationPanel.add(birthLabel);

		birthTextField = new JTextField("");
		birthTextField.setBounds(440, 20, 100, 30);
		birthTextField.setEditable(false);
		informationPanel.add(birthTextField);
		
		enterLabel = new JLabel("입학일자");
		enterLabel.setBounds(570, 20, 60, 30);
		informationPanel.add(enterLabel);

		enterTextField = new JTextField("");
		enterTextField.setBounds(640, 20, 100, 30);
		enterTextField.setEditable(false);
		informationPanel.add(enterTextField);
		
		collegeDepGradeLabel = new JLabel("소속 및 학년");
		collegeDepGradeLabel.setBounds(20, 60, 80, 30);
		informationPanel.add(collegeDepGradeLabel);

		collegeDepGradeTextField = new JTextField("");
		collegeDepGradeTextField.setBounds(110, 60, 250, 30);
		collegeDepGradeTextField.setEditable(false);
		informationPanel.add(collegeDepGradeTextField);

		//enterLabel, collegeLabel, DepLabel, GradeLabel, isPauseLabel;
		}
	public void init() {
		this.birthTextField.setText(User.BIRTH);
		this.collegeDepGradeTextField.setText(User.COLLEGE + "  " + User.MAJOR + "  " + User.GRADE);
		this.enterTextField.setText(User.ENTER);
		this.IDTextField.setText(User.LOGINID);
		this.nameTextField.setText(User.USERNAME);
	}
}
