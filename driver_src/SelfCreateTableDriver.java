import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelfCreateTableDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(400,400));
		
		String[][] data = {
				{"15011107", "방일섭"},
				{"15011111", "김아무개"}
				};
		String[] columName = {"ID", "Name"};
		
//		SelfCreateTable table = new SelfCreateTable(data, columName);
//		SelfCreateTable table = new SelfCreateTable("select ID, Name from Student where ID like '15%'");
		SelfCreateTable table = new SelfCreateTable("select Name, ID from Student where ID like '15%'");
		table.setPreferredSize(new Dimension(300,300));
		
		panel.add(table);
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

}
