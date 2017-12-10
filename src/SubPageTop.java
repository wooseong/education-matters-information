import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 서브페이지 상단 부분
 * 페이지 호출 전 setUserInfo() 메소드 호출 필요
 * @author bang
 *
 */
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
		
		// 학교 로고 추가
		this.logo = new JLabel(logoImage);
		this.logo.setBounds(10, 10, 220, 50);
		this.logo.addMouseListener(new MouseListener() {
			// 학교 로고를 클릭 시 기본 페이지로 전환하는 메소드 생성
			@Override
			public void mouseClicked(MouseEvent e) {
				master.getSubPage().changeBody(master.getSubPage().getDefaultPage());
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
		this.add(logo);
		
		// 유저 환영합니다 레이블
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
        
        // 로그아웃 버튼을 레이블로 처리함
        this.logout = new JLabel("Logout");
        this.logout.setFont(new Font("나눔고딕", Font.BOLD, 15));
        this.logout.setForeground(Color.gray);
        this.logout.setBounds(1200, 20, 60, 20);
        this.logout.addMouseListener(logoutListener);
        this.add(logout);
    }

	// 접속한 유저 설정하는 메소드
	public void setUserInfo() {
		this.userInfo.setText("환영합니다! " + User.USERNAME + "님");
	}
	
	/**
	 * 로그아웃 처리를 위한 마우스 리스너 클래스
	 * @author bang
	 *
	 */
	private class LogoutListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int result;
			
			logout.setForeground(Color.black);	// 파란 상태에서 클릭 했으니 검은색으로 변경해줌

			result = JOptionPane.showConfirmDialog(master, "로그아웃 하시겠습니까?", "",JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.YES_OPTION) {				// 로그아웃 시
				master.pageDirection(master.getMainPage());		// 로그인 페이지로 전환
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
