import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class RegisterFrame extends JFrame {
	private Connection con;
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField sidField;
	private JComboBox<String> collegeComboBox;
	private JComboBox<String> deptComboBox;
	private JPasswordField passwordField;
	private JTextField questField;

	RegisterFrame(Connection con) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logo.PNG"));
		this.con = con;
		setResizable(false);
		setSize(320, 400);
		setVisible(true);
		setTitle("Member Register");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		JLabel label_Name = new JLabel("이름");
		label_Name.setBounds(29, 30, 84, 15);
		contentPane.add(label_Name);

		JLabel label_Sid = new JLabel("학번");
		label_Sid.setBounds(29, 70, 87, 21);
		contentPane.add(label_Sid);

		JLabel label_college = new JLabel("대학");
		label_college.setBounds(29, 150, 87, 21);
		contentPane.add(label_college);

		JLabel label_dept = new JLabel("학과");
		label_dept.setBounds(29, 190, 87, 21);
		contentPane.add(label_dept);

		JLabel label_pass = new JLabel("비밀번호");
		label_pass.setBounds(29, 110, 87, 21);
		contentPane.add(label_pass);

		JLabel label_Quest = new JLabel("보물 1호는?");
		label_Quest.setBounds(29, 230, 84, 15);
		contentPane.add(label_Quest);
		
		JLabel lblNewLabel = new JLabel("(비밀번호 찾을 때 사용)");
		lblNewLabel.setBounds(125, 261, 138, 18);
		contentPane.add(lblNewLabel);
		
		nameField = new JTextField();
		nameField.setBounds(125, 30, 116, 21);
		contentPane.add(nameField);
		nameField.setColumns(10);

		sidField = new JTextField(8);
		sidField.setBounds(125, 70, 116, 21);
		contentPane.add(sidField);
		sidField.setColumns(10);
		sidField.setDocument(new TextLimit(8));

		collegeComboBox = new JComboBox<String>();
		collegeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"전체", "교양학부", "대학", "문과대학", "이과대학", "공과대학", "법과대학", "정치행정대학", "상경대학","경영대학","의과대학","약학대학","자연자원대학","생명응용과학대학","생활과학대학","사범대학","디자인미술대학","음악대학","섬유패션학부","생명공학부","중국언어문화학부","건축학부","기초교육대학","국제학부","연계전공","야간강좌개설부"}));
		collegeComboBox.setBounds(125, 150, 116, 21);
		contentPane.add(collegeComboBox);

		deptComboBox = new JComboBox<String>();
		deptComboBox.setModel((new DefaultComboBoxModel<String>(new String[] {"선택"})));
		deptComboBox.setBounds(125, 190, 116, 21);
		collegeComboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch(collegeComboBox.getSelectedIndex()){
				case 0:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"선택"}));
					break;
				case 1:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"핵심교양","자유교양","인문과학","사회과학","자연과학","예술체육","세계문화와외국어"}));
					break;
				case 2:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"대학"}));
					break;
				case 3:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"문과대학","국어국문학과","중국언어문화학과","중국언어문화학부","중어중문과","일어일문학과","일어일문과","영어영문학부","영어영문학과","영어영문과","유럽언어문화학부","불어불문학과","독어독문학과","철학과","역사학과","국사학과","사학과","문화인류학과","심리학과","사회학과","언론정보학과"}));
					break;
				case 4:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"이과대학","수학과","통계학과","물리학과","화학생화학부","화학과","생명과학과","자연과학부"}));
					break;
				case 5:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"공과대학","건설시스템공학과","환경공학과","도시공학과","기계공학부","신소재공학부","전기공학과","전자공학과","컴퓨터공학과","모바일정보통신공학과","정보통신공학과","화학공학부","융합섬유공학과","나노메디컬유기재료공학과"}));
					break;
				case 6:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"법과대학","법학부"}));
					break;
				case 7:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"정치행정대학","정치외교학과","행정학과","새마을국제개발학과","지역및복지행정학과","경찰행정학과","군사학과"}));
					break;
				case 8:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"상경대학","경제금융학부","경영학부","경영학과(계약)","국제통상학부"}));
					break;
				case 9:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"경영대학","경영학과","경영학과(계약)","회계세무학과"}));
					break;
				case 10:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"의과대학","의예과","의학과"}));
					break;
				case 11:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"약학대학","약학부"}));
					break;
				case 12:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"자연자원대학","식품산업경영학과","원예생명과학과","조경학과","산림자원학과","식품공학과","외식산업학과"}));
					break;
				case 13:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"생명응용과학대학","식품경제외식학과","원예생명과학과","산림자원및조경학과","식품공학과","생명공학과","의생명공학과","식품자원경제학과","외식산업학과","조경학과","산림자원학과"}));
					break;
				case 14:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"생활과학대학","가족주거학과","식품영양학과","체육학부","의류패션학과"}));
					break;
				case 15:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"사범대학","교육학과","국어교육과","영어교육과","한문교육과","수학교육과","유아교육과","특수체육교육과"}));
					break;
				case 16:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"디자인미술대학","미술학부","시각디자인학과","산업디자인학과","시각커뮤니케이션디자인학과","산업인터랙션디자인학과","생활제품디자인학과","모바일영상디자인학과"}));
					break;
				case 17:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"음악대학","음악과","성악과","기악과","음악학부"}));
					break;
				case 18:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"섬유패션학부"}));
					break;
				case 19:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"생명공학부"}));
					break;
				case 20:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"중국언어문화학부"}));
					break;
				case 21:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"건축학부"}));
					break;
				case 22:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"기초교육대학","천마인재학부","자율전공학부","군사학과","인문자율전공학부","자연자율전공학부"}));
					break;
				case 23:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"국제학부"}));
					break;
				case 24:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"연계"}));
					break;
				case 25:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"야간강좌개설부","국어국문학과(야)","전기공학과(야)","정치외교학과(야)","행정학과(야)","새마을국제개발학과(야)","지역및복지행정학과(야)","경제금융학부(야)","경영학부(야)","국제통상학부(야)","경영학과(야)"}));
				}
			}	
		});
		contentPane.add(deptComboBox);

		passwordField = new JPasswordField();
		passwordField.setBounds(125, 110, 116, 21);
		contentPane.add(passwordField);

		questField = new JTextField();
		questField.setBounds(125, 230, 116, 21);
		contentPane.add(questField);
		questField.setColumns(10);
		
		JButton btn_Register = new JButton("Register");
		btn_Register.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.BOLD, 30));
		btn_Register.setBounds(81, 285, 149, 67);
		btn_Register.setBorderPainted(false);
		btn_Register.setFocusPainted(false);
		btn_Register.setContentAreaFilled(false);
		contentPane.add(btn_Register);
		
		RegisterFunc registerBtnHandler = new RegisterFunc(this, con);
		btn_Register.addActionListener(registerBtnHandler);
		
	}

	public JTextField getSidField() {
		return sidField;
	}
	public JTextField getNameField(){
		return nameField;
	}
	public JComboBox<String> getCollegeComboBox() {
		return collegeComboBox;
	}

	public JComboBox<String> getDetpComboBox() {
		return deptComboBox;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public JTextField getQuestField() {
		return questField;
	}
	public class TextLimit extends PlainDocument{
		private int limit;
		public TextLimit(int limit){
			super();
			this.limit = limit;
		}
		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
			if(str == null) return;
			if(getLength() + str.length() <= limit) super.insertString(offset, str, attr);
		}
	}
}
