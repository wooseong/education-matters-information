import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * 로그인 페이지 다음으로 나오는 페이지
 * 크게 상단, 좌측, 바디 패널로 나뉨
 * 모든 페이지를 가지고 있으며 바디패널을 변경하는 changeBody 메소드를 가짐
 * @author bang
 *
 */
public class SubPage extends JPanel{

	private JPanel top;
	private JPanel left;
	private Component body;

	private SignUpAccessPage signUpAccessPage;						// 수강신청 접근 페이지
    private SearchLecture searchLecture;							// 수업목록 조회 페이지
    private CompletionLectureMarks completionLectureMarks;			// 기이수 성적조회 페이지
    private CurrentLectureMarks currentLectureMarks;				// 당학기 성적조회 페이지
    private Applicationlecture applicationlecture;					// 수강신청 페이지
    private InterestLecture interestLecture;						// 관심과목 페이지
    private InterestLectureAccessPage interestLectureAccessPage;	// 관심과목 접근 페이지
	private AdminPage adminPage;									// 관리자 페이지
	private ScholarPage scholarPage;								// 장학내역 페이지
	private EducationMattersSchedule defaultPage;					// 기본 페이지 (학사일정)
	
	// get methods
	public CurrentLectureMarks getCurrentLectureMarks() {
		return currentLectureMarks;
	}
	public EducationMattersSchedule getDefaultPage() {
		return defaultPage;
	}
	public ScholarPage getScholarPage() {
		return scholarPage;
	}
	public SignUpAccessPage getSignUpAccessPage() {
		return signUpAccessPage;
	}
    public SearchLecture getSearchLecture() {
        return searchLecture;
    }
    public CompletionLectureMarks getCompletionLectureMarks() {
        return completionLectureMarks;
    }
    public Applicationlecture getApplicationlecture() {
        return applicationlecture;
    }
    public InterestLecture getInterestLecture() {
        return interestLecture;
    }
    public InterestLectureAccessPage getInterestLectureAccessPage() {
        return interestLectureAccessPage;
    }
    public AdminPage getAdminPage() {
        return adminPage;
    }
    public MenuPanel getMenuPanel() {
    	return (MenuPanel) this.left;
    }
    public SubPageTop getSubPageTop() {
    	return (SubPageTop) this.top;
    }
    
	public SubPage(MasterFrame master) {
		super();
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(Color.white);
		this.setLayout(null);

		// 모든 페이지 생성
		signUpAccessPage = new SignUpAccessPage(this);
	    searchLecture = new SearchLecture();
	    completionLectureMarks = new CompletionLectureMarks();
	    applicationlecture = new Applicationlecture();
	    interestLecture = new InterestLecture();
	    interestLectureAccessPage = new InterestLectureAccessPage(this);
		adminPage = new AdminPage();
		scholarPage = new ScholarPage();
		defaultPage = new EducationMattersSchedule();
		currentLectureMarks = new CurrentLectureMarks();

		// 좌측 메뉴패널 생성
		this.left = new MenuPanel(this, User.isAdmin);
		this.left.setBounds(0, 70, 250, 650);
		this.left.setBackground(Color.white);
		
		// 상단 패널 생성
		this.top = new SubPageTop(master);
		this.top.setBounds(0, 0, 1280, 70);
		this.top.setBackground(Color.white);
		
		// 바디 부분 생성
		this.body = new JPanel();
		this.body.setBounds(250, 70, 1030, 650);

		this.add(top);
		this.add(left);
		this.add(body);

	}

	// 바디 부분 변환 메소드
	public void changeBody(Component panel) {
		this.remove(this.body);	// 현재 바디를 서브페이지 패널에서 제거한 후
		
		this.body = panel;		// 바디가 가르키는 페이지를 변환
		this.body.setBounds(250, 70, 1030, 650);
		this.add(this.body);	// 서브페이지에 바디 추가
		this.revalidate();		// 서브페이지 다시 그릴 준비
		this.repaint();			// 서브페이지 다시 그림
	}
}
