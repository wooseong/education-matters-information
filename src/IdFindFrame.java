import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
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
	private JTextField dobField;
	private JTextField questField;
	private JTextField nameField;
	

	IdFindFrame(Connection con) {
		setResizable(false);
		setSize(320, 250);
		setVisible(true);
		setTitle("아이디찾기");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500,300, 320, 250);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
	
		
		JLabel label_Name = new JLabel("이 름");
		label_Name.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.PLAIN, 14));
		label_Name.setBounds(40, 65, 64, 29);
		contentPane.add(label_Name);
	
		JLabel label_Sid = new JLabel("생년 월일");
		label_Sid.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.PLAIN, 14));
		label_Sid.setBounds(40, 20, 64, 25);
		contentPane.add(label_Sid);
		
		nameField = new JTextField("성명");
		nameField.setBounds(123, 17, 140, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		dobField = new JTextField("생년월일(예.920101)");
		dobField.setBounds(123, 65, 140, 30);
		contentPane.add(dobField);
		dobField.setColumns(10);
		
		JButton btnFind = new JButton("학번찾기");
		btnFind.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.BOLD, 18));
		btnFind.setBounds(80, 163, 159, 49);
		btnFind.setBorderPainted(false);
		btnFind.setFocusPainted(false);
		btnFind.setContentAreaFilled(false);
		contentPane.add(btnFind);
		
		
		IdFindFunc IdFindActionHandler = new IdFindFunc(this, con);
		btnFind.addActionListener(IdFindActionHandler);
	}

	public JTextField getSidField() {
		return dobField;
	}


	public JTextField getNameField() {
		return nameField;
	}
	
	
}
