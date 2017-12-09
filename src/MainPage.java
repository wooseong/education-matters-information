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
		this.add(lblID);//패널에 추가

		
		
		//ID TextField 생성
		jtfID = new JTextField(10);
		jtfID.setFont(inputFnt);
		jtfID.setBounds(pt.x + 100, pt.y, 180, 30);
		this.add(jtfID);//패널에 추가

		lblPW = new JLabel("비밀번호");
		lblPW.setFont(baseFnt);
		lblPW.setBounds(pt.x - 20, pt.y + 30, 100, 30);
		this.add(lblPW);//패널에 추가

		//비밀번호 TextField생성
		jtfPW = new JPasswordField();
		jtfPW.setEchoChar('*');//비밀번호 입력했을경우 숫자가 보이지않게 *로쓰여지게한다
		jtfPW.setFont(inputFnt);
		jtfPW.setBounds(pt.x + 100, pt.y + 30, 180, 30);
		jtfPW.addActionListener(listener);
		this.add(jtfPW);//패널에 추가
		
		//ID TextField 탭키를 비밀번호 TextField로 포커스를 이동시켜준다
		jtfID.setFocusTraversalKeysEnabled(false); 
		jtfID.addKeyListener(new KeyAdapter() { 
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					jtfPW.requestFocus();
				}
			}
		});

		//로그인 Button 생성
		btnLogin = new JButton("로그인");
		btnLogin.setBackground(new Color(199, 0, 39));
		btnLogin.setFont(new Font("나눔고딕", Font.BOLD, 20));
		btnLogin.setForeground(Color.white);
		btnLogin.setBounds(pt.x + 290, pt.y, 100, 60);
		btnLogin.addActionListener(listener);
		this.add(btnLogin);//패널에 추가

		// 학번찾기 Button생성
		JButton btn_IdFind = new JButton();
		btn_IdFind.setFont(baseFnt);
		btn_IdFind.setBounds(180, 400, 200, 200);
		btn_IdFind.setSize(200, 200);
		btn_IdFind.setForeground(Color.decode("#ED3E75"));
		btn_IdFind.setBorderPainted(false); //버튼의 외각선(border) 없애줌
		btn_IdFind.setFocusPainted(false); //버튼이 선택 되었을때 생기는 테두리 사용 안함
		btn_IdFind.setContentAreaFilled(false); // 버튼의 내용영역 채우기 않음
		this.add(btn_IdFind);//패널에 추가
		IdFindFrameFunc IdFindBtnHandler = new IdFindFrameFunc(con);//IdFindFrameFunc를 생성
		btn_IdFind.addActionListener(IdFindBtnHandler);//학번찾기 버튼을 눌렀을때 생성된 IdFindBtnHandler를 실행 ->IdFindFrameFunc

		// 비밀번호찾기 Button생성
		JButton btn_PassFind = new JButton();
		btn_PassFind.setFont(baseFnt);
		btn_PassFind.setBounds(450, 400, 200, 200);
		btn_PassFind.setSize(200, 200);
		btn_PassFind.setForeground(Color.decode("#ED3E75"));
		btn_PassFind.setBorderPainted(false);
		btn_PassFind.setFocusPainted(false);
		btn_PassFind.setContentAreaFilled(false);
		this.add(btn_PassFind);//패널에 추가
		PassFindFrameFunc passFindBtnHandler = new PassFindFrameFunc(con);//PassFindFrameFunc를 생성
		btn_PassFind.addActionListener(passFindBtnHandler);//비밀번호찾기 버튼을 눌렀을때 생성된 passFindBtnHandler를 실행 ->passFindFrameFunc

		/*
		 * // 회원가입 JButton btn_Register = new JButton("회원가입");
		 * btn_Register.setFont(baseFnt); btn_Register.setBounds(400, 400, 106, 40);
		 * btn_Register.setForeground(Color.decode("#ED3E75"));
		 * btn_Register.setBorderPainted(false); btn_Register.setFocusPainted(false);
		 * btn_Register.setContentAreaFilled(false); this.add(btn_Register);
		 * 
		 * RegisterFrameFunc registerbtnhandler = new RegisterFrameFunc(con);
		 * btn_Register.addActionListener(registerbtnhandler);
		 */
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 171, 0,938,720, null);
	}
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			char[] PW = jtfPW.getPassword();
			String ID = jtfID.getText();
			if(PW == null) {
				JOptionPane.showMessageDialog(btnLogin, "학번/직번을 입력하시요.");
			}
			else if(ID == null) {
				JOptionPane.showMessageDialog(btnLogin, "비밀번호를 입력하시요.");
			}
			else {
				db = new DBController(DBconf.DB);
				db.executeQuery("select id, name, birth, college, enter, grade, major, isAdmin from student where id='" + ID + "' and pw='" + new String(PW, 0, PW.length)+"'");
				try {
					if (db.getResultSet().next()) {
						User.LOGINID = db.getResultSet().getString("id");
						User.USERNAME = db.getResultSet().getString("name");
						User.BIRTH = db.getResultSet().getString("birth");
						User.COLLEGE = db.getResultSet().getString("college");
						User.ENTER = db.getResultSet().getString("enter");
						User.GRADE = db.getResultSet().getString("grade");
						User.MAJOR = db.getResultSet().getString("major");
						
						if(db.getResultSet().getString("isAdmin").equals("true")) {
							master.setAdmin(true);
							System.out.println("Admin logged in");
						} else {
							master.setAdmin(false);
							System.out.println("Student logged in");
						}
						
						master.pageDirection(master.getSubPage());
						jtfID.setText("");
						jtfPW.setText("");
//						JOptionPane.showMessageDialog(btnLogin, "로그인 성공!");
					}
					else {
						JOptionPane.showMessageDialog(btnLogin, "학번 혹은 비밀번호가 틀립니다.");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					db.disconnectDB();
				}
			}
		}
	}
}