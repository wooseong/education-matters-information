import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class IdFindFrame extends JFrame {
	private JPanel contentPane;
	private JTextField birthField;
	private JTextField nameField;

	//학번찾기 새로운창을 생성 생성된 창에 정보를 담는다.
	IdFindFrame(Connection con) {
		setResizable(false);
		setSize(320, 250);
		setVisible(true);
		setTitle("아이디찾기"); //상단바의 이름 아이디찾기
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(500, 300, 320, 250);
		contentPane = new JPanel(); //Panle생성
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		//이름 라벨생성
		JLabel label_Name = new JLabel("이 름");
		label_Name.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.PLAIN, 14));
		label_Name.setBounds(40, 20, 64, 25);
		contentPane.add(label_Name);

		//생년월일 라벨 생셩
		JLabel label_Birth = new JLabel("생년 월일");
		label_Birth.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.PLAIN, 14));
		label_Birth.setBounds(40, 65, 64, 29);
		contentPane.add(label_Birth);

		//이름 텍스트필드 생성
		nameField = new JTextField();
		nameField.setText("이 름");//기존에 텍스트필드에 이름이라고 쓰여있게 초괴화
		nameField.addMouseListener(new java.awt.event.MouseAdapter() { //텍스트필드에 마우스이벤트가 발생했을시 초기화되어있던 이름이라고 쓰여있던 텍스트필드가 초기화됨
			public void mouseClicked(java.awt.event.MouseEvent evt) {//사용자 정보를 쓰면된다.
				reSet(evt);
			}
		});
		nameField.setBounds(123, 17, 140, 30);
		contentPane.add(nameField);
		nameField.setColumns(10);

		birthField = new JTextField("생년월일(예.920101)");//기존에 텍스트필드에 생년월일(예.920101) 쓰여있게 초기화
		birthField.addMouseListener(new java.awt.event.MouseAdapter() { //텍스트필드에 마우스이벤트가 발생했을시 초기화되어있던 생년월일(예.920101)이라고 쓰여있던 텍스트필드가 초기화됨
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				BeSet(evt);
			}
		});
		birthField.setBounds(123, 65, 140, 30);
		contentPane.add(birthField);
		birthField.setColumns(10);

		//학번찾기 버튼 생성
		JButton btnFind = new JButton("학번찾기");
		btnFind.setFont(new Font("ZESSTYPE 비가온다 PT02", Font.BOLD, 18));
		btnFind.setBounds(80, 163, 159, 49);
		btnFind.setBorderPainted(false);
		btnFind.setFocusPainted(false);
		btnFind.setContentAreaFilled(false);
		contentPane.add(btnFind);

		IdFindFunc IdFindActionHandler = new IdFindFunc(this, con);//학번찾기버튼을 눌렀을때 사용하는IdFindFunc를 생성
		btnFind.addActionListener(IdFindActionHandler);//이벤트가 발생했을경우 생성된IdFindActionHandler를 실행
	}

	public void reSet(MouseEvent evt) {//이벤트 발생했을경우 빈칸으로 바꾸어준다
		nameField.setText("");
	}
	public void BeSet(MouseEvent evt) {//이벤트 발생했을경우 빈칸으로 바꾸어준다
		birthField.setText("");
	}

	public JTextField getBirthField() {//텍스트필드에 담긴 생년월일 정보를 넘겨준다
		return birthField;
	}

	public JTextField getNameField() {//텍스트필드에 작성된 이름 정보를 넘겨준다
		return nameField;
	}
}
