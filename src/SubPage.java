import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class SubPage extends JPanel{

	private MasterFrame master;
	private JPanel top;
	private JPanel left;
	private JPanel body;

	private LogoutListener listener;
	private SignUpPage signUpPage;
	private SignUpAccessPage signUpAccessPage;

	//TODO: 페이지 두개 생성자에서 할당해주고 get 메소드 만들어주고 페이지 코드에서 페이지디렉션 만들어주기

	public SignUpPage getSignUpPage() {
		return signUpPage;
	}
	public SignUpAccessPage getSignUpAccessPage() {
		return signUpAccessPage;
	}

	public SubPage(MasterFrame master) {
		super();
		this.master = master;
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(Color.white);
		this.setLayout(null);

		this.listener = new LogoutListener();
		this.signUpPage = new SignUpPage(this);
		this.signUpAccessPage = new SignUpAccessPage(this);

		this.initLeft();
		this.initTop();
		this.initBody();

		this.add(top);
		this.add(left);
		this.add(body);

	}

	public void initLeft() {
		this.left = new MenuPanel(this);
		this.left.setBounds(0, 120, 250, 600);
		this.left.setBackground(Color.white);
	}
	public void initTop() {
		this.top = new JPanel();
		this.top.setBounds(0, 0, 1280, 120);
		this.top.setBackground(Color.pink);
	}
	public void initBody() {
		this.body = new JPanel();
//		this.body = this.signUpAccessPage;
		this.body.setBounds(250, 120, 1030, 600);
		// this.body.setBackground(Color.BLUE);
		
	}

	public void showSignUpPage() {
		this.signUpPage = new SignUpPage(this);
		this.add(this.signUpPage);
		// 여기서 수강신청 페이지로 전환
	}

	public void changeBody(JPanel panel) {
		this.remove(this.body);
		this.body = panel;
		this.body.setBounds(250, 120, 1030, 600);
		this.add(this.body);
		this.revalidate();
		this.repaint();
	}

	private class LogoutListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int result;

			result = JOptionPane.showConfirmDialog(master, "로그아웃 하시겠습니까?");

			if (result == JOptionPane.YES_OPTION) {
				System.out.println("YES");
				master.pageDirection(master.getMainPage());
			} else if (result == JOptionPane.NO_OPTION) {
				/* DO SOMETHING */
			} else {
				/* DO SOMETHING */
			}
		}
	}

}
