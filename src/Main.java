import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * 프로젝트 드라이버 클래스
 * @author bang
 *
 */
public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MasterFrame frame = new MasterFrame();
		frame.setDefaultCloseOperation(MasterFrame.EXIT_ON_CLOSE);
		
		// frame.add(frame.getMainPage());
		
		frame.pack();
		frame.setVisible(true);
	}
}
