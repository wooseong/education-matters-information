import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PassFindFrame extends JFrame {
	private JPanel contentPane;
	private JTextField IDField;
	private JTextField NameField;

	//���ã�� ���ο�â�� ���� ������ â�� ������ ��´�.
	PassFindFrame(Connection con) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logo.PNG"));
		setResizable(false);
		setSize(320, 250);
		setVisible(true);
		setTitle("��й�ȣã��");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500,300, 320, 250);
		contentPane = new JPanel();//Panle����
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		//�й� �󺧻���
		JLabel label_ID = new JLabel("�� ��");
		label_ID.setFont(new Font("ZESSTYPE �񰡿´� PT02", Font.PLAIN, 14));
		label_ID.setBounds(40, 20, 64, 25);
		contentPane.add(label_ID);
		
		//�̸� �󺧻���
		JLabel label_Name = new JLabel("����");
		label_Name.setFont(new Font("ZESSTYPE �񰡿´� PT02", Font.PLAIN, 14));
		label_Name.setBounds(40, 65, 64, 29);
		contentPane.add(label_Name);
		
		//�й� �ؽ�Ʈ�ʵ� ����
		IDField = new JTextField("�л��� �й�, ����/������ ���о��̵�");//������ �ؽ�Ʈ�ʵ忡 �л��� �й�, ����/������ ���о��̵��̶�� �����ְ� �ʱ�ȭ
		IDField.addMouseListener(new java.awt.event.MouseAdapter() {//�ؽ�Ʈ�ʵ忡 ���콺�̺�Ʈ�� �߻������� �ʱ�ȭ�Ǿ��ִ� �̸��̶�� �����ִ� �ؽ�Ʈ�ʵ尡 �ʱ�ȭ��
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				reSet(evt);
			}
		});
		IDField.setBounds(123, 17, 140, 30);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		//�̸� �ؽ�Ʈ�ʵ� ����
		NameField = new JTextField("����");//������ �ؽ�Ʈ�ʵ忡 �̸��̶�� �����ְ� �ʱ�ȭ
		NameField.addMouseListener(new java.awt.event.MouseAdapter() {//�ؽ�Ʈ�ʵ忡 ���콺�̺�Ʈ�� �߻������� �ʱ�ȭ�Ǿ��ִ� �̸��̶�� �����ִ� �ؽ�Ʈ�ʵ尡 �ʱ�ȭ��
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				BeSet(evt);
			}
		});
		NameField.setBounds(123, 65, 140, 30);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		//��й�ȣã�� ��ư ����
		JButton btnFind = new JButton("��й�ȣ ã��");
		btnFind.setFont(new Font("ZESSTYPE �񰡿´� PT02", Font.BOLD, 18));
		btnFind.setBounds(80, 163, 159, 49);
		btnFind.setBorderPainted(false);
		btnFind.setFocusPainted(false);
		btnFind.setContentAreaFilled(false);
		contentPane.add(btnFind);
		
		
		PassFindFunc passFindActionHandler = new PassFindFunc(this, con);//�й�ã���ư�� �������� ����ϴ�PassFindFunc�� ����
		btnFind.addActionListener(passFindActionHandler);//�̺�Ʈ�� �߻�������� ������passFindActionHandler�� ����
	}

	
	public void reSet(MouseEvent evt) {//�̺�Ʈ �߻�������� ��ĭ���� �ٲپ��ش�
		IDField.setText("");
	}
	public void BeSet(MouseEvent evt) {//�̺�Ʈ �߻�������� ��ĭ���� �ٲپ��ش�
		NameField.setText("");
	}
	
	public JTextField getIDField() {//�ؽ�Ʈ�ʵ忡 ��� �й������� �Ѱ��ش�
		return IDField;
	}
	
	public JTextField getNameField() {//�ؽ�Ʈ�ʵ忡 ��� �̸������� �Ѱ��ش�
		return NameField;
	}
}
