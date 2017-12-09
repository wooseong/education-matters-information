import java.awt.Color;
import java.awt.Cursor;
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
		this.userInfo.setFont(new Font("나눔고딕", Font.PLAIN, 20));
		this.userInfo.setBounds(900, 10, 300, 20);
		this.add(userInfo);
		
		this.logout = new JLabel("로그아웃");
		this.logout.setBounds(1040, 40, 300, 20);
		this.logout.addMouseListener(logoutListener);
		this.logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
				logout.setForeground(Color.BLACK);
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
