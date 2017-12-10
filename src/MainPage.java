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

		
		
		
		jtfID = new JTextField(10);//ID TextField 생성
		jtfID.setFont(inputFnt);
		jtfID.setBounds(pt.x + 100, pt.y, 180, 30);
		this.add(jtfID);//패널에 추가

		lblPW = new JLabel("비밀번호");
		lblPW.setFont(baseFnt);
		lblPW.setBounds(pt.x - 20, pt.y + 30, 100, 30);
		this.add(lblPW);//패널에 추가

		
		jtfPW = new JPasswordField();//비밀번호 TextField생성
		jtfPW.setEchoChar('*');//비밀번호 입력했을경우 숫자가 보이지않게 *로쓰여지게한다
		jtfPW.setFont(inputFnt);
		jtfPW.setBounds(pt.x + 100, pt.y + 30, 180, 30);
		jtfPW.addActionListener(listener);
		this.add(jtfPW);//패널에 추가
		
		jtfID.setFocusTraversalKeysEnabled(false);//ID TextField 탭키를 비밀번호 TextField로 포커스를 이동시켜준다 
		jtfID.addKeyListener(new KeyAdapter() { 
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					jtfPW.requestFocus();
				}
			}
		});


		btnLogin = new JButton("로그인");//로그인 Button 생성
		btnLogin.setBackground(new Color(199, 0, 39));
		btnLogin.setFont(new Font("나눔고딕", Font.BOLD, 20));
		btnLogin.setForeground(Color.white);
		btnLogin.setBounds(pt.x + 290, pt.y, 100, 60);
		btnLogin.addActionListener(listener);
		this.add(btnLogin);//패널에 추가

		
		JButton btn_IdFind = new JButton();// 학번찾기 Button생성
		btn_IdFind.setFont(baseFnt);
		btn_IdFind.setBounds(255, 577, 147, 34);
		btn_IdFind.setForeground(Color.decode("#ED3E75"));
		btn_IdFind.setContentAreaFilled(false);//버튼의 내용을 채우지 않는다
		this.add(btn_IdFind);//패널에 추가
		IdFindFrameFunc IdFindBtnHandler = new IdFindFrameFunc(con);//IdFindFrameFunc를 생성
		btn_IdFind.addActionListener(IdFindBtnHandler);//학번찾기 버튼을 눌렀을때 생성된 IdFindBtnHandler를 실행 ->IdFindFrameFunc

	
		JButton btn_PassFind = new JButton();// 비밀번호찾기 Button생성
		btn_PassFind.setFont(baseFnt);
		btn_PassFind.setBounds(576, 576, 126, 34);
		btn_PassFind.setForeground(Color.decode("#ED3E75"));
		btn_PassFind.setContentAreaFilled(false);//버튼의 내용을 채우지 않는다
		this.add(btn_PassFind);//패널에 추가
		PassFindFrameFunc passFindBtnHandler = new PassFindFrameFunc(con);//PassFindFrameFunc를 생성
		btn_PassFind.addActionListener(passFindBtnHandler);//비밀번호찾기 버튼을 눌렀을때 생성된 passFindBtnHandler를 실행 ->passFindFrameFunc

	}

	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 171, 0,938,720, null);
	}
	
	private class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			char[] PW = jtfPW.getPassword();  //텍스트필드에 담김 비밀번호를 PW저장
			String ID = jtfID.getText();//텍스트필드에 학번을 ID를 저장
			if(PW == null) {//빈칸일경우
				JOptionPane.showMessageDialog(btnLogin, "학번/직번을 입력하시요.");
			}
			else if(ID == null) {//빈칸일경우
				JOptionPane.showMessageDialog(btnLogin, "비밀번호를 입력하시요.");
			}
			else {
				db = new DBController(DBconf.DB);//내부 DB에연결
				db.executeQuery("select id, name, birth, college, enter, grade, major, isAdmin from student where id='" + ID + "' and pw='" + new String(PW, 0, PW.length)+"'");//DB에있는(id, name, birth, college, enter, grade, major, isAdmin)통해 관리자와 학생을 구분한 후 비밀번 학번과 비밀번호를 비교한다
				try {//DB연결에 오류가있는경우 잡아준다
					if (db.getResultSet().next()) {
						User.LOGINID = db.getResultSet().getString("id");  //DB에있는 id정보를 LOGINDID에 담는다
						User.USERNAME = db.getResultSet().getString("name");//DB에있는 name정보를 USERNAME에 담는다
						User.BIRTH = db.getResultSet().getString("birth");//DB에있는 name정보를 USERNAME에 담는다
						User.COLLEGE = db.getResultSet().getString("college");//DB에있는 college정보를 COLLEGE에 담는다
						User.ENTER = db.getResultSet().getString("enter");//DB에있는 enter정보를 ENTER에 담는다
						User.GRADE = db.getResultSet().getString("grade");//DB에있는 grade정보를 GRADE에 담는다
						User.MAJOR = db.getResultSet().getString("major");//DB에있는 name정보를 MAJOR에 담는다
						
						if(db.getResultSet().getString("isAdmin").equals("true")) { //관리자정보인경우
							master.setAdmin(true);                                //관리자모드 true    
							System.out.println("Admin logged in"); //관리자로그를 찍는다
						} else {//아닐경우
							master.setAdmin(false);//관리자모드 false
							System.out.println("Student logged in");//학생로그를 찍는다
						}
						
						master.pageDirection(master.getSubPage());  //SubPage로이동
						jtfID.setText("");
						jtfPW.setText("");
					}
					else {//일치하는값이 없는경우
						JOptionPane.showMessageDialog(btnLogin, "학번 혹은 비밀번호가 틀립니다.");
					}
				} catch (SQLException e) {//DB연결 예외처리
					e.printStackTrace();
				} finally {
					db.disconnectDB();//모든작업이 마치면 DB를 종료시켜준다.
				}
			}
		}
	}
}
