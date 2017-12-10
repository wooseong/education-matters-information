import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MasterFrame extends JFrame {
	
	private MainPage mainPage;
	private SubPage subPage;
	private boolean Admin;
	
	public void setAdmin(boolean admin) {
		User.isAdmin = admin;
		this.subPage.getMenuPanel().adminVisible(User.isAdmin);
		this.subPage.getSubPageTop().setUserInfo();
	}
	
	public MainPage getMainPage() {
		return mainPage;
	}
	public SubPage getSubPage() {
		return subPage;
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
