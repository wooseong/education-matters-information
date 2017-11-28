import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class SubPage extends JPanel{

	MasterFrame master;
	JPanel top;
	JPanel left;
//	SearchLecture body;
	CompletionLectureMarks body;
	
	LogoutListener listener;
	
	public SubPage(MasterFrame master) {
		super();
		this.master = master;
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(Color.white);
		this.setLayout(null);
		
		this.listener = new LogoutListener();
		
		this.initLeft();
		this.initTop();
		this.initBody();
		
		this.add(top);
		this.add(left);
		this.add(body);
	}
	
	public void initLeft() {
		this.left = new JPanel();
		this.left.setBounds(0, 150, 250, 1130);
		this.left.setBackground(Color.cyan);
	}
	public void initTop() {
		this.top = new JPanel();
		this.top.setBounds(0, 0, 1280, 150);
		this.top.setBackground(Color.pink);
	}
	public void initBody() {
//		this.body = new SearchLecture();
		this.body = new CompletionLectureMarks();
		this.body.setBounds(250, 150, 1030, 570);
		this.body.setBackground(Color.lightGray);
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
