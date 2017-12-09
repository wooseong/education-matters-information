import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class PassFindFrame extends JFrame {
	private JPanel contentPane;
	private JTextField IDField;
	private JTextField NameField;

	//비밀찾기 새로운창을 생성 생성된 창에 정보를 담는다.
	PassFindFrame(Connection con) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/logo.PNG"));
		setResizable(false);
		setSize(320, 250);
		setVisible(true);
		setTitle("비밀번호찾기");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500,300, 320, 250);
		contentPane = new JPanel();//Panle생성
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		//학번 라벨생성
		JLabel label_ID = new JLabel("학 번");
		label_ID.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.PLAIN, 14));
		label_ID.setBounds(40, 20, 64, 25);
		contentPane.add(label_ID);
		
		//이름 라벨생성
		JLabel label_Name = new JLabel("성명");
		label_Name.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.PLAIN, 14));
		label_Name.setBounds(40, 65, 64, 29);
		contentPane.add(label_Name);
		
		//학번 텍스트필드 생성
		IDField = new JTextField("학생은 학번, 교수/직원은 포털아이디");//기존에 텍스트필드에 학생은 학번, 교수/직원은 포털아이디이라고 쓰여있게 초괴화
		IDField.addMouseListener(new java.awt.event.MouseAdapter() {//텍스트필드에 마우스이벤트가 발생했을시 초기화되어있던 이름이라고 쓰여있던 텍스트필드가 초기화됨
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				reSet(evt);
			}
		});
		IDField.setBounds(123, 17, 140, 30);
		contentPane.add(IDField);
		IDField.setColumns(10);
		
		//이름 텍스트필드 생성
		NameField = new JTextField("성명");//기존에 텍스트필드에 이름이라고 쓰여있게 초괴화
		NameField.addMouseListener(new java.awt.event.MouseAdapter() {//텍스트필드에 마우스이벤트가 발생했을시 초기화되어있던 이름이라고 쓰여있던 텍스트필드가 초기화됨
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				BeSet(evt);
			}
		});
		NameField.setBounds(123, 65, 140, 30);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		//비밀번호찾기 버튼 생성
		JButton btnFind = new JButton("비밀번호 찾기");
		btnFind.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.BOLD, 18));
		btnFind.setBounds(80, 163, 159, 49);
		btnFind.setBorderPainted(false);
		btnFind.setFocusPainted(false);
		btnFind.setContentAreaFilled(false);
		contentPane.add(btnFind);
		
		
		PassFindFunc passFindActionHandler = new PassFindFunc(this, con);//학번찾기버튼을 눌렀을때 사용하는PassFindFunc를 생성
		btnFind.addActionListener(passFindActionHandler);//이벤트가 발생했을경우 생성된passFindActionHandler를 실행
	}

	
	public void reSet(MouseEvent evt) {//이벤트 발생했을경우 빈칸으로 바꾸어준다
		IDField.setText("");
	}
	public void BeSet(MouseEvent evt) {//이벤트 발생했을경우 빈칸으로 바꾸어준다
		NameField.setText("");
	}
	
	public JTextField getIDField() {//텍스트필드에 담긴 학번정보를 넘겨준다
		return IDField;
	}
	
	public JTextField getNameField() {//텍스트필드에 담긴 이름정보를 넘겨준다
		return NameField;
	}
}
