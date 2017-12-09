import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class IdFindFrame extends JFrame {
	private JPanel contentPane;
	private JTextField birthField;
	private JTextField nameField;

	//�й�ã�� ���ο�â�� ���� ������ â�� ������ ��´�.
	IdFindFrame(Connection con) {
		setResizable(false);
		setSize(320, 250);
		setVisible(true);
		setTitle("���̵�ã��"); //��ܹ��� �̸� ���̵�ã��
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500, 300, 320, 250);
		contentPane = new JPanel(); //Panle����
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		//�̸� �󺧻���
		JLabel label_Name = new JLabel("�� ��");
		label_Name.setFont(new Font("ZESSTYPE �񰡿´� PT02", Font.PLAIN, 14));
		label_Name.setBounds(40, 20, 64, 25);
		contentPane.add(label_Name);

		//������� �� ����
		JLabel label_Birth = new JLabel("���� ����");
		label_Birth.setFont(new Font("ZESSTYPE �񰡿´� PT02", Font.PLAIN, 14));
		label_Birth.setBounds(40, 65, 64, 29);
		contentPane.add(label_Birth);

		//�̸� �ؽ�Ʈ�ʵ� ����
		nameField = new JTextField();
		nameField.setText("�� ��");//������ �ؽ�Ʈ�ʵ忡 �̸��̶�� �����ְ� �ʱ�ȭ
		nameField.addMouseListener(new java.awt.event.MouseAdapter() { //�ؽ�Ʈ�ʵ忡 ���콺�̺�Ʈ�� �߻������� �ʱ�ȭ�Ǿ��ִ� �̸��̶�� �����ִ� �ؽ�Ʈ�ʵ尡 �ʱ�ȭ��
			public void mouseClicked(java.awt.event.MouseEvent evt) {//����� ������ ����ȴ�.
				reSet(evt);
			}
		});
		nameField.setBounds(123, 17, 140, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);

		birthField = new JTextField("�������(��.920101)");//������ �ؽ�Ʈ�ʵ忡 �������(��.920101) �����ְ� �ʱ�ȭ
		birthField.addMouseListener(new java.awt.event.MouseAdapter() { //�ؽ�Ʈ�ʵ忡 ���콺�̺�Ʈ�� �߻������� �ʱ�ȭ�Ǿ��ִ� �������(��.920101)�̶�� �����ִ� �ؽ�Ʈ�ʵ尡 �ʱ�ȭ��
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				BeSet(evt);
			}
		});
		birthField.setBounds(123, 65, 140, 30);
		contentPane.add(birthField);
		birthField.setColumns(10);

		//�й�ã�� ��ư ����
		JButton btnFind = new JButton("�й�ã��");
		btnFind.setFont(new Font("ZESSTYPE �񰡿´� PT02", Font.BOLD, 18));
		btnFind.setBounds(80, 163, 159, 49);
		btnFind.setBorderPainted(false);
		btnFind.setFocusPainted(false);
		btnFind.setContentAreaFilled(false);
		contentPane.add(btnFind);

		IdFindFunc IdFindActionHandler = new IdFindFunc(this, con);//�й�ã���ư�� �������� ����ϴ�IdFindFunc�� ����
		btnFind.addActionListener(IdFindActionHandler);//�̺�Ʈ�� �߻�������� ������IdFindActionHandler�� ����
	}

	public void reSet(MouseEvent evt) {//�̺�Ʈ �߻�������� ��ĭ���� �ٲپ��ش�
		nameField.setText("");
	}
	public void BeSet(MouseEvent evt) {//�̺�Ʈ �߻�������� ��ĭ���� �ٲپ��ش�
		birthField.setText("");
	}

	public JTextField getBirthField() {//�ؽ�Ʈ�ʵ忡 ��� ������� ������ �Ѱ��ش�
		return birthField;
	}

	public JTextField getNameField() {//�ؽ�Ʈ�ʵ忡 �ۼ��� �̸� ������ �Ѱ��ش�
		return nameField;
	}
}
