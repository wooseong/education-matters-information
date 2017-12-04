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
    
	public SubPage(MasterFrame master) {
		super();
		this.master = master;
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(Color.white);
		this.setLayout(null);

		listener = new LogoutListener();
		signUpAccessPage = new SignUpAccessPage(this);
	    searchLecture = new SearchLecture();
	    completionLectureMarks = new CompletionLectureMarks();
	    applicationlecture = new Applicationlecture();
	    interestLecture = new InterestLecture();
	    interestLectureAccessPage = new InterestLectureAccessPage(this);
		adminPage = new AdminPage();

		this.left = new MenuPanel(this, master.isAdmin());
		this.left.setBounds(0, 120, 250, 600);
		this.left.setBackground(Color.white);
		
		this.top = new JPanel();
		this.top.setBounds(0, 0, 1280, 120);
		this.top.setBackground(Color.white);
		
		this.body = new JPanel();
		this.body.setBounds(250, 120, 1030, 600);

		this.add(top);
		this.add(left);
		this.add(body);

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
