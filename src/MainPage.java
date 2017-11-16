import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainPage extends JPanel {

	private Image img, resizeImage;
	private JLabel userLabel, passwordLabel;
	private JTextField userText;
	private JPasswordField passwordText;
	private JButton loginButton, registerButton;
	private Main frame;
	
	public MainPage(Main frame) {
		this.frame = frame;
		setPreferredSize(new Dimension(1280, 720));
		setBackground(Color.white);
		setLayout(null);

		img = new ImageIcon("./image/mainbackground.png").getImage();

		resizeImage = img.getScaledInstance(844, 713, Image.SCALE_SMOOTH);
		
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		add(userLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		add(userText);

		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 40, 80, 25);
		add(passwordLabel);

		passwordText = new JPasswordField(20);
		passwordText.setBounds(100, 40, 160, 25);
		add(passwordText);
		
		loginButton = new JButton("login");
		loginButton.setBounds(10, 80, 80, 25);
		add(loginButton);

		registerButton = new JButton("register");
		registerButton.setBounds(180, 80, 80, 25);
		add(registerButton);
		
		registerButton.addActionListener(new LoginListener());
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 171, 0,844,713, null);
	}
	class LoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frame.change("SubMainPage");
		}
	}
}
