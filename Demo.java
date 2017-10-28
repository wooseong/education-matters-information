package test;

import java.awt.*;
import javax.swing.*;

public class Demo{
	
	public static void main(String args[]) {
		myJFrame frame = new myJFrame("test");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JPanel mainPage = initMainPage();
		JPanel subPage = initSubPage();
		
		frame.getContentPane().add(mainPage);

		frame.pack();
		frame.setVisible(true);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.changePage(subPage);
		
	}
	
	public static JPanel initMainPage() {
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(1280, 720));
		panel.setBackground(Color.WHITE);
		panel.setLayout(null);
		
		JPanel loginBox = new JPanel();
		loginBox.setBackground(Color.LIGHT_GRAY);
		loginBox.setBounds(440, 260, 400, 150);
		loginBox.setLayout(null);
		panel.add(loginBox);
		
		JLabel idText = new JLabel("Student ID");
		idText.setBounds(10, -5, 400, 50);
		loginBox.add(idText);
		
		JTextField id = new JTextField(20);
		id.setBounds(10, 30, 200, 40);
		loginBox.add(id);
		
		JLabel pwText = new JLabel("Password");
		pwText.setBounds(10, 65, 400, 50);
		loginBox.add(pwText);
		
		JTextField pw = new JTextField(20);
		pw.setBounds(10, 100, 200, 40);
		loginBox.add(pw);
		
		JButton login = new JButton("Login");
		login.setBounds(220, 30, 160, 110);
		loginBox.add(login);
		
		return panel;
	}
	
	public static JPanel initSubPage() {
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.pink);
		
		JLabel mytext = new JLabel("page redirect success");
		mytext.setFont(new Font("consolas", Font.BOLD, 30));
		panel.add(mytext);
		
		return panel;
	}
}