import javax.swing.*;

public class Main extends JFrame{

	public MainPage mainPagePanel;
	public SubMainPage subMainPagePanel;
	
	public void change(String panelName) {
		getContentPane().removeAll();
		if(panelName.equals("MainPage"))
		{
			getContentPane().add(subMainPagePanel);
		}else if(panelName.equals("SubMainPage")){
			getContentPane().add(subMainPagePanel);
		}
		revalidate();
		repaint();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Main frame = new Main();
		frame.setTitle("학사정보시스템");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		frame.mainPagePanel = new MainPage(frame);
		frame.subMainPagePanel = new SubMainPage(frame);
		
		frame.add(frame.mainPagePanel);
		
		frame.pack();
		frame.setVisible(true);
		
	}
}