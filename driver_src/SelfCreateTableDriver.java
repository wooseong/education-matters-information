import java.awt.Dimension;

import javax.swing.JButton;
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
		
		Object[][] data = {
				{true, "신청","15011107", "방일섭"},
				{true, "신청", "15011111", "김아무개"}
				};
		Object[] columName = {"Check", "신청", "ID", "Name"};
		
		SelfCreateTable table = new SelfCreateTable();
		table.makeCheckboxTable(data, columName);
		
		String[][] str = table.getChecked();
		
		for(String[] inner:str) {
			for(String iiner:inner) {
				System.out.print(iiner + ' ');
			}
			System.out.println("");
		}
//		SelfCreateTable table = new SelfCreateTable(data, columName);
//		SelfCreateTable table = new SelfCreateTable("select ID, Name from Student where ID like '15%'");
//		SelfCreateTable table = new SelfCreateTable("select Name, ID from Student where ID like '15%'");
		table.setPreferredSize(new Dimension(300,300));
//		table.delColumnName();  // 컬럼 이름 안나오게(상단 이름 안나오게 처리)
		
		panel.add(table);
		
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}

}
