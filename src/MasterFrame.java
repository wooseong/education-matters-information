import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MasterFrame extends JFrame {
	
	MainPage mainPage;
	SubPage subPage;
	String studentID;


	public MainPage getMainPage() {
		return mainPage;
	}
	public SubPage getSubPage() {
		return subPage;
	}
	public void setStudentID(String str) {
		this.studentID = str;
	}

	
	public MasterFrame() throws HeadlessException {
		super();
		this.mainPage = new MainPage(this);
		this.subPage = new SubPage(this);
		this.getContentPane().add(mainPage);
	}

	public MasterFrame(GraphicsConfiguration gc) {
		super(gc);
		this.mainPage = new MainPage(this);
		this.subPage = new SubPage(this);
		this.getContentPane().add(mainPage);
	}
	public MasterFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		this.mainPage = new MainPage(this);
		this.subPage = new SubPage(this);
		this.getContentPane().add(mainPage);
	}
	public MasterFrame(String title) throws HeadlessException {
		super(title);
		this.mainPage = new MainPage(this);
		this.subPage = new SubPage(this);
		this.getContentPane().add(mainPage);
	}
	public void pageDirection(JPanel panel) {
		this.getContentPane().removeAll();
		this.getContentPane().add(panel);
		this.revalidate();
		this.repaint();
	}
}
