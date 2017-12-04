import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class SubPage extends JPanel{

	private JPanel top;
	private JPanel left;
	private JPanel body;

	private SignUpAccessPage signUpAccessPage;
    private SearchLecture searchLecture;
    private CompletionLectureMarks completionLectureMarks;
    private Applicationlecture applicationlecture;
    private InterestLecture interestLecture;
    private InterestLectureAccessPage interestLectureAccessPage;
	private AdminPage adminPage;

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

		signUpAccessPage = new SignUpAccessPage(this);
	    searchLecture = new SearchLecture();
	    completionLectureMarks = new CompletionLectureMarks();
	    applicationlecture = new Applicationlecture();
	    interestLecture = new InterestLecture();
	    interestLectureAccessPage = new InterestLectureAccessPage(this);
		adminPage = new AdminPage();

		this.left = new MenuPanel(this, User.isAdmin);
		this.left.setBounds(0, 70, 250, 650);
		this.left.setBackground(Color.white);
		
		this.top = new SubPageTop(master);
		this.top.setBounds(0, 0, 1280, 70);
		this.top.setBackground(Color.white);
		
		this.body = new JPanel();
		this.body.setBounds(250, 70, 1030, 650);

		this.add(top);
		this.add(left);
		this.add(body);

	}

	public void changeBody(JPanel panel) {
		this.remove(this.body);
		this.body = panel;
		this.body.setBounds(250, 70, 1030, 650);
		this.add(this.body);
		this.revalidate();
		this.repaint();
	}
}
