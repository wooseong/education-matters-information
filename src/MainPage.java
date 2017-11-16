import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainPage extends JPanel {
	
	private Image img, resizeImage;
	private JButton btnLogin;
	private JTextField jtfID;
	private JPasswordField jtfPW;
	private JLabel	lblID, lblPW;
	private DBController db;
	
	private MasterFrame master;
	private Font baseFnt, inputFnt;
	private LoginListener  listener;
	
	public MainPage(MasterFrame master) {
		super();
		this.setPreferredSize(new Dimension(1280,720));
		this.setBackground(Color.white);
		this.setLayout(null);
		this.master = master;
		baseFnt = new Font("나눔고딕", Font.PLAIN, 20);
		inputFnt = new Font("나눔고딕", Font.PLAIN, 13);
		
		img = new ImageIcon("./image/mainbackground.png").getImage();

//		resizeImage = img.getScaledInstance(844, 713, Image.SCALE_SMOOTH);

		Point pt = new Point(600,180);
		
		listener = new LoginListener();
		
		lblID = new JLabel("학번/아이디");
		lblID.setBounds(pt.x-20, pt.y, 110, 30);
		lblID.setFont(baseFnt);
		this.add(lblID);
		
		jtfID = new JTextField(10);
		jtfID.setFont(inputFnt);
		jtfID.setBounds(pt.x + 100, pt.y, 180, 30);
		this.add(jtfID);
		
		lblPW = new JLabel("비밀번호");
		lblPW.setFont(baseFnt);
		lblPW.setBounds(pt.x-20, pt.y + 30, 100, 30);
		this.add(lblPW);
		
		jtfPW = new JPasswordField();
		jtfPW.setEchoChar('*');
		jtfPW.setFont(inputFnt);
		jtfPW.setBounds(pt.x + 100, pt.y + 30, 180, 30);
		jtfPW.addActionListener(listener);
		this.add(jtfPW);
		
		btnLogin = new JButton("로그인");
		btnLogin.setBackground(new Color(199,0,39));
		btnLogin.setFont(new Font("나눔고딕", Font.BOLD, 20));
		btnLogin.setForeground(Color.white);
		btnLogin.setBounds(pt.x + 290, pt.y, 100, 60);
		btnLogin.addActionListener(listener);
		this.add(btnLogin);
		
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 171, 0,844,713, null);
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
				db = new DBController("./DB/test.db");
				db.executeQuery("select Name from Student where ID=" + ID + " and PW=" + new String(PW, 0, PW.length));
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
