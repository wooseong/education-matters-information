import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.*;
import java.sql.Connection;
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
	private JLabel lblID, lblPW;
	private DBController db;
	private Connection con;

	private MasterFrame master;
	private Font baseFnt, inputFnt;
	private LoginListener listener;

	public MainPage(MasterFrame master) {
		super();
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(Color.white);
		this.setLayout(null);
		this.master = master;
		baseFnt = new Font("나눔고딕", Font.PLAIN, 20);
		inputFnt = new Font("나눔고딕", Font.PLAIN, 13);

		img = new ImageIcon("./image/mainbackground.png").getImage();

		Point pt = new Point(600, 180);

		listener = new LoginListener();

		lblID = new JLabel("학번/아이디");
		lblID.setBounds(pt.x - 20, pt.y, 110, 30);
		lblID.setFont(baseFnt);
		this.add(lblID);

		jtfID = new JTextField(10);
		jtfID.setFont(inputFnt);
		jtfID.setBounds(pt.x + 100, pt.y, 180, 30);
		this.add(jtfID);

		lblPW = new JLabel("비밀번호");
		lblPW.setFont(baseFnt);
		lblPW.setBounds(pt.x - 20, pt.y + 30, 100, 30);
		this.add(lblPW);

		jtfPW = new JPasswordField();
		jtfPW.setEchoChar('*');
		jtfPW.setFont(inputFnt);
		jtfPW.setBounds(pt.x + 100, pt.y + 30, 180, 30);
		jtfPW.addActionListener(listener);
		this.add(jtfPW);
		jtfID.setFocusTraversalKeysEnabled(false);
		jtfID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					jtfPW.requestFocus();
				}
			}
		});

		btnLogin = new JButton("로그인");
		btnLogin.setBackground(new Color(199, 0, 39));
		btnLogin.setFont(new Font("나눔고딕", Font.BOLD, 20));
		btnLogin.setForeground(Color.white);
		btnLogin.setBounds(pt.x + 290, pt.y, 100, 60);
		btnLogin.addActionListener(listener);
		this.add(btnLogin);

	/*	// 회원가입
		JButton btn_Register = new JButton("회원가입");
		btn_Register.setFont(baseFnt);
		btn_Register.setBounds(400, 400, 106, 40);
		btn_Register.setForeground(Color.decode("#ED3E75"));
		btn_Register.setBorderPainted(false);
		btn_Register.setFocusPainted(false);
		btn_Register.setContentAreaFilled(false);
		this.add(btn_Register);

		RegisterFrameFunc registerbtnhandler = new RegisterFrameFunc(con);
		btn_Register.addActionListener(registerbtnhandler);*/

		// 학번찾기
		JButton btn_IdFind = new JButton();
		btn_IdFind.setFont(baseFnt);
		btn_IdFind.setBounds(180, 400, 200, 200);
		btn_IdFind.setSize(200, 200);
		btn_IdFind.setForeground(Color.decode("#ED3E75"));
		btn_IdFind.setBorderPainted(false);
		btn_IdFind.setFocusPainted(false);

		btn_IdFind.setContentAreaFilled(false);
		this.add(btn_IdFind);

		IdFindFrameFunc IdFindBtnHandler = new IdFindFrameFunc(con);
		btn_IdFind.addActionListener(IdFindBtnHandler);
		
		// 비밀번호찾기
		JButton btn_PassFind = new JButton();
		btn_PassFind.setFont(baseFnt);
		btn_PassFind.setBounds(450, 400, 200, 200);
		btn_PassFind.setSize(200, 200);
		btn_PassFind.setForeground(Color.decode("#ED3E75"));
		btn_PassFind.setBorderPainted(false);
		btn_PassFind.setFocusPainted(false);

		btn_PassFind.setContentAreaFilled(false);
		this.add(btn_PassFind);

		PassFindFrameFunc passFindBtnHandler = new PassFindFrameFunc(con);
		btn_PassFind.addActionListener(passFindBtnHandler);

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 171, 0, 844, 713, null);
	}

	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			char[] PW = jtfPW.getPassword();
			String ID = jtfID.getText();

			if (ID.equals("") )
			{
				JOptionPane.showMessageDialog(null, "학번/직번을 입력하시요.");
			}
			else if(PW==null)
			{
				JOptionPane.showMessageDialog(null, "비밀번호를 입력하시요.");	
			}			
			else {
				db = new DBController("./DB/test.db");
				db.executeQuery("select Name from Student where ID=" + ID + " and PW=" + new String(PW, 0, PW.length));
				try {
					if (db.getResultSet().next()) {
						master.pageDirection(master.getSubPage());
						// JOptionPane.showMessageDialog(btnLogin, "로그인 성공!");
					} else {
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
