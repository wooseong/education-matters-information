import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuPanel extends JPanel {
	private JLabel base1; // 강좌조회 및 수강신청탭
	private JLabel base2; // 성적조회 탭
	private JLabel base3; // 장학이력 탭
	private JLabel base4; // 관리자 탭
	
	private JLabel inner11; // 강좌조회
	private JLabel inner12; // 관심과목설정
	private JLabel inner13; // 수강신청
	private JLabel inner21; // 기이수성적조회
	private JLabel inner22; // 당학기성적조회
	
	private Point base1Pt;
	private Point base2Pt;
	private Point base3Pt;
	private Point base4Pt;
	int width, height;
	
	private MenuListener menuListener; 
	
	private SubPage subPage;

	public MenuPanel(SubPage subPage, boolean isAdmin) {
		super();
		this.subPage = subPage;
		
		this.setLayout(null);
		
		menuListener = new MenuListener();

		this.width = 250;
		this.height = 31;
		
		this.base1Pt = new Point(0, 1);
		this.base2Pt = new Point(0, base1Pt.y+height);
		this.base3Pt = new Point(0, base2Pt.y+height);
		this.base4Pt = new Point(0, base3Pt.y+height);
		
		this.base1 = new JLabel("강좌조회 및 수강신청");
		this.base1.setBounds(this.base1Pt.x, this.base1Pt.y, width, height-1);
		this.base1.setHorizontalAlignment(SwingConstants.CENTER);
		this.base1.setOpaque(true);
		this.base1.setBackground(new Color(0x465680));
		this.base1.setForeground(Color.white);
		this.base1.addMouseListener(menuListener);
		this.add(base1);
		
		this.base2 = new JLabel("성적조회");
		this.base2.setBounds(this.base2Pt.x, this.base2Pt.y, width, height-1);
		this.base2.setHorizontalAlignment(SwingConstants.CENTER);
		this.base2.setOpaque(true);
		this.base2.setBackground(new Color(0x465680));
		this.base2.setForeground(Color.white);
		this.base2.addMouseListener(menuListener);
		this.add(this.base2);
		
		this.base3 = new JLabel("장학이력");
		this.base3.setBounds(this.base3Pt.x, this.base3Pt.y, width, height-1);
		this.base3.setHorizontalAlignment(SwingConstants.CENTER);
		this.base3.setOpaque(true);
		this.base3.setBackground(new Color(0x465680));
		this.base3.setForeground(Color.white);
		this.base3.addMouseListener(menuListener);
		this.add(this.base3);
		
		this.base4 = new JLabel("관리자");
		this.base4.setBounds(this.base4Pt.x, this.base4Pt.y, width, height-1);
		this.base4.setHorizontalAlignment(SwingConstants.CENTER);
		this.base4.setOpaque(true);
		this.base4.setBackground(new Color(0x465680));
		this.base4.setForeground(Color.white);
		this.base4.addMouseListener(menuListener);
		this.base4.setVisible(isAdmin);
		this.add(this.base4);
		
		
		
		this.inner11 = new JLabel("강좌조회");
		this.inner11.setBounds(this.base1Pt.x, this.base1Pt.y + height, width, height-1);
		this.inner11.setHorizontalAlignment(SwingConstants.CENTER);
//		this.inner11.setOpaque(true);
//		this.inner11.setBackground(new Color(0x465680));
//		this.inner11.setForeground(Color.white);
		this.inner11.addMouseListener(menuListener);
		
		this.inner12 = new JLabel("관심과목설정");
		this.inner12.setBounds(this.base1Pt.x, this.base1Pt.y + height*2, width, height-1);
		this.inner12.setHorizontalAlignment(SwingConstants.CENTER);
//		this.inner12.setOpaque(true);
//		this.inner12.setBackground(new Color(0x465680));
//		this.inner12.setForeground(Color.white);
		this.inner12.addMouseListener(menuListener);
		
		this.inner13 = new JLabel("수강신청");
		this.inner13.setBounds(this.base1Pt.x, this.base1Pt.y + height*3, width, height-1);
		this.inner13.setHorizontalAlignment(SwingConstants.CENTER);
//		this.inner13.setOpaque(true);
//		this.inner13.setBackground(new Color(0x465680));
//		this.inner13.setForeground(Color.white);
		this.inner13.addMouseListener(menuListener);
		
		this.inner21 = new JLabel("기이수성적조회");
		this.inner21.setBounds(this.base2Pt.x, this.base2Pt.y + height, width, height-1);
		this.inner21.setHorizontalAlignment(SwingConstants.CENTER);
//		this.inner21.setOpaque(true);
//		this.inner21.setBackground(new Color(0x465680));
//		this.inner21.setForeground(Color.white);
		this.inner21.addMouseListener(menuListener);
		
		this.inner22 = new JLabel("당학기성적조회");
		this.inner22.setBounds(this.base2Pt.x, this.base2Pt.y + height*2, width, height-1);
		this.inner22.setHorizontalAlignment(SwingConstants.CENTER);
//		this.inner22.setOpaque(true);
//		this.inner22.setBackground(new Color(0x465680));
//		this.inner22.setForeground(Color.white);
		this.inner22.addMouseListener(menuListener);
	}
	
	public void adminVisible(boolean isAdmin) {
		this.base4.setVisible(isAdmin);
	}
	
	private class MenuListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JLabel event = (JLabel) e.getSource();
			if(event == base1) {
				remove(inner21);
				remove(inner22);

				add(inner11);
				add(inner12);
				add(inner13);
				base2Pt.y = base1Pt.y + height*4;
				base3Pt.y = base2Pt.y + height;
				base4Pt.y = base3Pt.y + height;
				
				base2.setBounds(base2Pt.x, base2Pt.y, width, height-1);
				base3.setBounds(base3Pt.x, base3Pt.y, width, height-1);
				base4.setBounds(base4Pt.x, base4Pt.y, width, height-1);
			} else if(event == base2) {
				remove(inner11);
				remove(inner12);
				remove(inner13);
				base2Pt.y = base1Pt.y+height;
				base2.setBounds(base2Pt.x, base2Pt.y, width, height-1);
				
				inner21.setBounds(base2Pt.x, base2Pt.y + height, width, height-1);
				add(inner21);
				inner22.setBounds(base2Pt.x, base2Pt.y + height*2, width, height-1);
				add(inner22);

				base3Pt.y = base2Pt.y + height*3;
				base4Pt.y = base3Pt.y + height;
				
				base3.setBounds(base3Pt.x, base3Pt.y, width, height-1);
				base4.setBounds(base4Pt.x, base4Pt.y, width, height-1);
			} else if(event == base3) { // 장학
				subPage.getScholarPage().initPage();
				subPage.changeBody(subPage.getScholarPage());
			} else if(event == base4) { // 관리자페이지
				subPage.changeBody(subPage.getAdminPage());
			} else if(event == inner11) {	// 수업목록
				subPage.changeBody(subPage.getSearchLecture());
			} else if(event == inner12) {	// 관심과목
				subPage.getInterestLectureAccessPage().setButtonVisible();
				subPage.changeBody(subPage.getInterestLectureAccessPage());
			} else if(event == inner13) {	// 수강신청
				subPage.getSignUpAccessPage().setButtonVisible();
				subPage.changeBody(subPage.getSignUpAccessPage());
			} else if(event == inner21) {	// 기이수성적조회
				subPage.getCompletionLectureMarks().init();
				subPage.changeBody(subPage.getCompletionLectureMarks());
			} else if(event == inner22) {	// 당학시 성적조회
				subPage.getCurrentLectureMarks().init();
				subPage.changeBody(subPage.getCurrentLectureMarks());
			}

			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
