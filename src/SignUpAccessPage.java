import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SignUpAccessPage extends AccessPage {

	private CurrentTimeLabel	lblCurrentTime;				// 현재 시간을 화면에 출력하는 Label
	private JButton				btnAccess, btnSetPeriod;	// 접속 버튼 / 기간 설정 버튼
	private ButtonListener		buttonListener;				// 버튼이 클릭되었을 때, 화면을 전환하는 Listener
	private boolean				bIfManager;					// 관리자 여부
	private	SubPage				primary;					// UpCall을 위한 상위 패널
	private SetPeriodBox		setPeriodBox;				// 기간 설정 창
	private SignUpAccessPage	thisPage;					// this를 ButtonListener 안에서 넘겨주기 위함

	public SignUpAccessPage(SubPage subPage) {	// Constructor
		super();

		this.primary = subPage;	// UpCall을 위해 상위 클래스 객체 저장
		this.thisPage = this;	// 자기 자신 저장

		bIfManager = true;	// 관리자 계정인지 아닌지 판단해서 값 설정 (현재 default를 관리자로 설정)

		this.setBounds(300, 150, 980, 570);
		this.setBackground(new Color(0xABCDEF));
		this.setLayout(null);

		this.buttonListener = new ButtonListener();				// 버튼 작동을 위한 Listener 객체 생성

		this.lblCurrentTime = new CurrentTimeLabel();
		this.lblCurrentTime.setBounds(0, 300, 980, 100);
		this.lblCurrentTime.setHorizontalAlignment(JLabel.CENTER);
		//this.lblCurrentTime.setFont(new Font("VERDANA",Font.BOLD,15));
		this.add(this.lblCurrentTime);
		this.lblCurrentTime.start();
		// 현재 시간을 화면에 표시

		this.btnAccess = new JButton("접속");
		this.btnAccess.setBounds(440, 420, 100, 30);
		this.btnAccess.addActionListener(buttonListener);
		this.add(this.btnAccess);
		// 접속 버튼을 화면에 부착

		this.btnSetPeriod = new JButton("기간설정");
		this.btnSetPeriod.setBounds(440, 470, 100, 30);
		this.btnSetPeriod.addActionListener(buttonListener);
		this.btnSetPeriod.setVisible(false);
		this.add(this.btnSetPeriod);
		// 기간 설정 버튼을 화면에 부착
	}
	
	public void setButtonVisible(boolean isAdmin) {
		this.btnSetPeriod.setVisible(isAdmin);
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();

			if(obj == btnAccess) {	// 접속 버튼
				if(thisPage.getDtAccessStart().before(new Date()) && getDtAccessEnd().after(new Date())) {	// 접속 가능 기간인 경우,
					primary.changeBody(primary.getApplicationlecture());
				}else {																	// 접속 가능 기간이 아닌 경우,
					JOptionPane.showMessageDialog(null, "접속 가능 기간이 아닙니다!");	// 알림창 표시
				}
			}else {					// 기간 설정 버튼
				setPeriodBox = new SetPeriodBox(thisPage,getDtSignUpStart(),getDtSignUpEnd(),getDtAccessStart(),getDtAccessEnd());
				// 현재 설정된 기간 정보를 넘겨주며 SetPeriodBox 를 생성해 화면에 띄움
				// 기간설정 버튼이 visible이면 이미 관리자 계정이라는 것이기 때문에 따로 조건 필요 x
			}
		}
	}
}
