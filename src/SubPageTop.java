import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SubPageTop extends JPanel {
	private JLabel logo;
	private ImageIcon logoImage;
	private JLabel userInfo;
	private JLabel logout;
    private JLabel guidance;
    private JLabel systemGuide;

	private MasterFrame master;
	
	private LogoutListener logoutListener;
	
	public SubPageTop(MasterFrame master) {
		super();
		this.master = master;
		
		logoutListener = new LogoutListener();
		
		this.setBackground(Color.white);
		this.setLayout(null);
		
		this.logoImage = new ImageIcon("./image/toplogo.gif");
		
		this.logo = new JLabel(logoImage);
		this.logo.setBounds(10, 10, 220, 50);
		this.add(logo);
		
		this.userInfo = new JLabel("");
        this.userInfo.setFont(new Font("나눔고딕", Font.PLAIN, 15));
        this.userInfo.setBounds(250, 20, 200, 20);
		this.add(userInfo);
		
        this.systemGuide = new JLabel("[학부생 학사정보시스템]");
        this.systemGuide.setFont(new Font("나눔고딕", Font.BOLD, 15));
        this.systemGuide.setForeground(new Color(0x921229));
        this.systemGuide.setBounds(250, 45, 200, 20);
        this.add(systemGuide);
        
        this.guidance = new JLabel("학사정보시스템의 오류는 lisa960310@naver.com로 보내주시기 바랍니다.");
        this.guidance.setFont(new Font("나눔고딕", Font.PLAIN, 15));
        this.guidance.setForeground(Color.lightGray);
        this.guidance.setBounds(700, 20, 600, 20);
        this.add(guidance);
        
        this.logout = new JLabel("Logout");
        this.logout.setFont(new Font("나눔고딕", Font.BOLD, 15));
        this.logout.setForeground(Color.gray);
        this.logout.setBounds(1200, 20, 60, 20);
        this.logout.addMouseListener(logoutListener);
        this.add(logout);
    }

	public void setUserInfo() {
		this.userInfo.setText("환영합니다! " + User.USERNAME + "님");
	}
	
	private class LogoutListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int result;

			result = JOptionPane.showConfirmDialog(master, "로그아웃 하시겠습니까?");

			if (result == JOptionPane.YES_OPTION) {
				master.pageDirection(master.getMainPage());
				master.pageDirection(master.getMainPage());
				master.getSubPage().changeBody(new JPanel());
			} else if (result == JOptionPane.NO_OPTION) {
				/* DO SOMETHING */
			} else {
				/* DO SOMETHING */
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			logout.setForeground(Color.BLUE);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			logout.setForeground(Color.BLACK);
		}
		
	}
}
