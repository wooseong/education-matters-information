import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SignUpAccessPage extends JPanel {

	private JLabel				lblSignUpPeriod;			// 수강 신청 기간을 화면에 출력하는 Label
	private JLabel				lblAccessablePeriod;		// 접속 가능 기간을 화면에 출력하는 Label
	private Date				dtSignUpStart, dtSignUpEnd;	// 수강신청 시작/종료 기간
	private Date				dtAccessStart, dtAccessEnd;	// 접속가능 시작/종료 기간
	private CurrentTimeLabel	lblCurrentTime;				// 현재 시간을 화면에 출력하는 Label
	private JButton				btnAccess, btnSetPeriod;	// 접속 버튼 / 기간 설정 버튼
	private ButtonListener		buttonListener;				// 버튼이 클릭되었을 때, 화면을 전환하는 Listener
	private boolean				bIfManager;					// 관리자 여부
	private	SubPage				primary;					// UpCall을 위한 상위 패널
	private SetPeriodBox		setPeriodBox;				// 기간 설정 창
	private SimpleDateFormat	dayTime;					// 화면에 날짜를 표시하기 위한 포맷
	private SignUpAccessPage	thisPage;					// this를 ButtonListener 안에서 넘겨주기 위함

	public SignUpAccessPage(SubPage subPage) {	// Constructor
		super();

		this.primary = subPage;	// UpCall을 위해 상위 클래스 객체 저장
		this.thisPage = this;	// 자기 자신 저장

		bIfManager = true;	// 관리자 계정인지 아닌지 판단해서 값 설정 (현재 default를 관리자로 설정)

		this.setBounds(300, 150, 980, 570);
		this.setBackground(Color.white);
		this.setLayout(null);

		this.buttonListener = new ButtonListener();				// 버튼 작동을 위한 Listener 객체 생성

		dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	// 화면에 날짜를 표시하기 위한 포맷 설정

		// 여기서 수강신청기간과 접속가능기간을 설정함
		this.dtAccessStart = new Date(117,11,2,10,0,0);
		this.dtAccessEnd = new Date(117,11,2,18,0,0);
		this.dtSignUpStart = new Date(117,11,2,9,0,0);
		this.dtSignUpEnd = new Date(117,11,2,18,0,0);
		// 현재 임의로 기간 초기화함 (이후에는 관리자가 설정한 대로 수정됨)

		this.lblSignUpPeriod = new JLabel("수강신청기간 : "+dayTime.format(this.dtSignUpStart)+" ~ "+dayTime.format(this.dtSignUpEnd));
		this.lblSignUpPeriod.setBounds(0, 100, 980, 100);
		this.lblSignUpPeriod.setHorizontalAlignment(JLabel.CENTER);
		//this.lblSignUpPeriod.setFont(new Font("VERDANA",Font.BOLD,15));
		this.add(this.lblSignUpPeriod);
		// 수강 신청 기간을 화면에 표시

		this.lblAccessablePeriod = new JLabel("접속가능기간 : "+dayTime.format(this.dtAccessStart)+" ~ "+dayTime.format(this.dtAccessEnd));
		this.lblAccessablePeriod.setBounds(0, 200, 980, 100);
		this.lblAccessablePeriod.setHorizontalAlignment(JLabel.CENTER);
		//this.lblAccessablePeriod.setFont(new Font("VERDANA",Font.BOLD,15));
		this.add(this.lblAccessablePeriod);
		// 접속 가능 기간을 화면에 표시

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
		this.add(this.btnSetPeriod);
		// 기간 설정 버튼을 화면에 부착
		if(this.bIfManager)	this.btnSetPeriod.setVisible(true);		// 관리자 여부에 따라 표시 여부 결정
		else				this.btnSetPeriod.setVisible(false);
	}

	public void setPeriod(Date dtSignUpStart, Date dtSignUpEnd, Date dtAccessStart, Date dtAccessEnd) {	// 기간이 재설정 되었을 때 호출
		this.dtSignUpStart = dtSignUpStart;
		this.dtSignUpEnd = dtSignUpEnd;
		this.dtAccessStart = dtAccessStart;
		this.dtAccessEnd = dtAccessEnd;
		// 기간 재설정

		this.lblSignUpPeriod.setText("수강신청기간 : "+dayTime.format(this.dtSignUpStart)+" ~ "+dayTime.format(this.dtSignUpEnd));
		this.lblAccessablePeriod.setText("접속가능기간 : "+dayTime.format(this.dtAccessStart)+" ~ "+dayTime.format(this.dtAccessEnd));
		// 화면에 출력되는 기간 내용 수정
	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();

			if(obj == btnAccess) {	// 접속 버튼
				if(dtAccessStart.before(new Date()) && dtAccessEnd.after(new Date())) {	// 접속 가능 기간인 경우,
					primary.changeBody(primary.getSignUpPage());
				}else {																	// 접속 가능 기간이 아닌 경우,
					JOptionPane.showMessageDialog(null, "접속 가능 기간이 아닙니다!");	// 알림창 표시
				}
			}else {					// 기간 설정 버튼
				setPeriodBox = new SetPeriodBox(thisPage,dtSignUpStart,dtSignUpEnd,dtAccessStart,dtAccessEnd);
				// 현재 설정된 기간 정보를 넘겨주며 SetPeriodBox 를 생성해 화면에 띄움
				// 기간설정 버튼이 visible이면 이미 관리자 계정이라는 것이기 때문에 따로 조건 필요 x
			}
		}
	}
}
