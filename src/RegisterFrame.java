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

		JLabel label_Name = new JLabel("�̸�");
		label_Name.setBounds(29, 30, 84, 15);
		contentPane.add(label_Name);

		JLabel label_Sid = new JLabel("�й�");
		label_Sid.setBounds(29, 70, 87, 21);
		contentPane.add(label_Sid);

		JLabel label_college = new JLabel("����");
		label_college.setBounds(29, 150, 87, 21);
		contentPane.add(label_college);

		JLabel label_dept = new JLabel("�а�");
		label_dept.setBounds(29, 190, 87, 21);
		contentPane.add(label_dept);

		JLabel label_pass = new JLabel("��й�ȣ");
		label_pass.setBounds(29, 110, 87, 21);
		contentPane.add(label_pass);

		JLabel label_Quest = new JLabel("���� 1ȣ��?");
		label_Quest.setBounds(29, 230, 84, 15);
		contentPane.add(label_Quest);
		
		JLabel lblNewLabel = new JLabel("(��й�ȣ ã�� �� ���)");
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
		collegeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"��ü", "�����к�", "����", "��������", "�̰�����", "��������", "��������", "��ġ��������", "������","�濵����","�ǰ�����","���д���","�ڿ��ڿ�����","����������д���","��Ȱ���д���","�������","�����ι̼�����","���Ǵ���","�����м��к�","������к�","�߱���ȭ�к�","�����к�","���ʱ�������","�����к�","��������","�߰����°�����"}));
		collegeComboBox.setBounds(125, 150, 116, 21);
		contentPane.add(collegeComboBox);

		deptComboBox = new JComboBox<String>();
		deptComboBox.setModel((new DefaultComboBoxModel<String>(new String[] {"����"})));
		deptComboBox.setBounds(125, 190, 116, 21);
		collegeComboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				switch(collegeComboBox.getSelectedIndex()){
				case 0:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"����"}));
					break;
				case 1:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�ٽɱ���","��������","�ι�����","��ȸ����","�ڿ�����","����ü��","���蹮ȭ�Ϳܱ���"}));
					break;
				case 2:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"����"}));
					break;
				case 3:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"��������","������а�","�߱���ȭ�а�","�߱���ȭ�к�","�߾��߹���","�Ͼ��Ϲ��а�","�Ͼ��Ϲ���","������к�","������а�","�������","������ȭ�к�","�Ҿ�ҹ��а�","������а�","ö�а�","�����а�","�����а�","���а�","��ȭ�η��а�","�ɸ��а�","��ȸ�а�","��������а�"}));
					break;
				case 4:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�̰�����","���а�","����а�","�����а�","ȭ�л�ȭ�к�","ȭ�а�","������а�","�ڿ����к�"}));
					break;
				case 5:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"��������","�Ǽ��ý��۰��а�","ȯ����а�","���ð��а�","�����к�","�ż�����к�","������а�","���ڰ��а�","��ǻ�Ͱ��а�","�����������Ű��а�","������Ű��а�","ȭ�а��к�","���ռ������а�","����޵������������а�"}));
					break;
				case 6:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"��������","���к�"}));
					break;
				case 7:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"��ġ��������","��ġ�ܱ��а�","�����а�","���������������а�","�����׺��������а�","���������а�","�����а�"}));
					break;
				case 8:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"������","���������к�","�濵�к�","�濵�а�(���)","��������к�"}));
					break;
				case 9:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�濵����","�濵�а�","�濵�а�(���)","ȸ�輼���а�"}));
					break;
				case 10:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�ǰ�����","�ǿ���","���а�"}));
					break;
				case 11:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"���д���","���к�"}));
					break;
				case 12:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�ڿ��ڿ�����","��ǰ����濵�а�","����������а�","�����а�","�긲�ڿ��а�","��ǰ���а�","�ܽĻ���а�"}));
					break;
				case 13:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"����������д���","��ǰ�����ܽ��а�","����������а�","�긲�ڿ��������а�","��ǰ���а�","������а�","�ǻ�����а�","��ǰ�ڿ������а�","�ܽĻ���а�","�����а�","�긲�ڿ��а�"}));
					break;
				case 14:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"��Ȱ���д���","�����ְ��а�","��ǰ�����а�","ü���к�","�Ƿ��м��а�"}));
					break;
				case 15:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�������","�����а�","�������","�������","�ѹ�������","���б�����","���Ʊ�����","Ư��ü��������"}));
					break;
				case 16:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�����ι̼�����","�̼��к�","�ð��������а�","����������а�","�ð�Ŀ�´����̼ǵ������а�","������ͷ��ǵ������а�","��Ȱ��ǰ�������а�","����Ͽ���������а�"}));
					break;
				case 17:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"���Ǵ���","���ǰ�","���ǰ�","��ǰ�","�����к�"}));
					break;
				case 18:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�����м��к�"}));
					break;
				case 19:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"������к�"}));
					break;
				case 20:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�߱���ȭ�к�"}));
					break;
				case 21:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�����к�"}));
					break;
				case 22:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"���ʱ�������","õ�������к�","���������к�","�����а�","�ι����������к�","�ڿ����������к�"}));
					break;
				case 23:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�����к�"}));
					break;
				case 24:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"����"}));
					break;
				case 25:
					deptComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"�߰����°�����","������а�(��)","������а�(��)","��ġ�ܱ��а�(��)","�����а�(��)","���������������а�(��)","�����׺��������а�(��)","���������к�(��)","�濵�к�(��)","��������к�(��)","�濵�а�(��)"}));
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
		btn_Register.setFont(new Font("ZESSTYPE �񰡿´� PT02", Font.BOLD, 30));
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
