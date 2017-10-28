package test;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class myJFrame extends JFrame {
	public myJFrame(String str) {
		super(str);
	}
	
	public void changePage(JPanel inner) {
		this.getContentPane().removeAll();
		this.getContentPane().add(inner);
		this.revalidate();
		this.repaint();
	}
}
