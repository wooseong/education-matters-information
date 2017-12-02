import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainPage extends JPanel {
	JButton btnLogin;
	JTextField jtfID;
	JPasswordField jtfPW;
	JLabel	lblID, lblPW;
	DBController db;
	
	MasterFrame master;
	
	Font baseFnt, inputFnt;
	
	LoginListener  listener;
	
	public MainPage(MasterFrame master) {
		super();
		this.setPreferredSize(new Dimension(1280,720));
		this.setLayout(null);
		this.master = master;
		baseFnt = new Font("Consolas", Font.PLAIN, 20);
		inputFnt = new Font("Consolas", Font.PLAIN, 13);
		
		Point pt = new Point(300,300);
		
		listener = new LoginListener();
		
		lblID = new JLabel("학번");
		lblID.setBounds(pt.x, pt.y, 100, 30);
		lblID.setFont(baseFnt);
		this.add(lblID);
		
		jtfID = new JTextField(10);
		jtfID.setFont(inputFnt);
		jtfID.setBounds(pt.x + 100, pt.y, 120, 30);
		this.add(jtfID);
		
		lblPW = new JLabel("비밀번호");
		lblPW.setFont(baseFnt);
		lblPW.setBounds(pt.x, pt.y + 30, 100, 30);
		this.add(lblPW);
		
		jtfPW = new JPasswordField();
		jtfPW.setEchoChar('*');
		jtfPW.setFont(inputFnt);
		jtfPW.setBounds(pt.x + 100, pt.y + 30, 120, 30);
		jtfPW.addActionListener(listener);
		this.add(jtfPW);
		
		btnLogin = new JButton("로그인");
		btnLogin.setBounds(pt.x + 230, pt.y, 100, 60);
		btnLogin.setFont(baseFnt);
		btnLogin.addActionListener(listener);
		this.add(btnLogin);
		
	}
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			char[] PW = jtfPW.getPassword();
			String ID = jtfID.getText();
			if(PW == null) {
				JOptionPane.showMessageDialog(btnLogin, "패스워드를 입력해 주세요");
			}
			else if(ID == null) {
				JOptionPane.showMessageDialog(btnLogin, "패스워드를 입력해 주세요");
			}
			else {
				db = new DBController(DBconf.DB);
				db.executeQuery("select name from student where id='" + ID + "' and pw='" + new String(PW, 0, PW.length) + "'");
				try {
					if (db.getResultSet().next()) {
						master.pageDirection(master.getSubPage());
//						JOptionPane.showMessageDialog(btnLogin, "로그인 성공!");
					}
					else {
						JOptionPane.showMessageDialog(btnLogin, "학번 혹은 비밀번호가 틀립니다.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
