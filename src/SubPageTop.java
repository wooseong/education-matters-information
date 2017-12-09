import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SubPageTop extends JPanel { // 학사정보시스템 페이지 상단바
	private JLabel logo; //세종대 로고를 받을 라벨
	private ImageIcon logoImage; //로고를 담을 이미지 아이콘
	private JLabel userInfo; // 사용자 이름을 띄울 라벨
	private JLabel logout; // "로그아웃" 라벨
    private JLabel guidance; // 가이드 글 
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

			result = JOptionPane.showConfirmDialog(master, "로그아웃 하시겠습니까?", "",JOptionPane.YES_NO_OPTION);// 팝업창 생성, "예 / 아니오" 2가지 버튼으로만 생성

			if (result == JOptionPane.YES_OPTION) {// "예"가 눌린경우
				master.pageDirection(master.getMainPage());
				master.getSubPage().changeBody(new JPanel());
			} else if (result == JOptionPane.NO_OPTION) {
				/* DO SOMETHING */
			} else {
				/* DO SOMETHING */
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {// 마우스가 지정 범위 안에 들어갔을 때
			logout.setForeground(Color.BLUE);// logout라벨 글씨색 파랑으로 변경
		}

		@Override
		public void mouseExited(MouseEvent e) {// 마우스가 지정 범위 안에서 나왔을 때
			logout.setForeground(Color.BLACK);//logout라벨 글씨색 검정으로 변경
		}
		@Override	
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
