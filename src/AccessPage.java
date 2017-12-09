
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccessPage extends JPanel {
	// Instance Data
	public static final int		SIGN_UP = 1;				// 수강 신청 접속 페이지
	public static final int		INTEREST_LECTURE = 2;		// 관심 과목 접속 페이지
	
	private boolean				bIfManager;					// 관리자 여부
	protected int				nPageType;					// 수강 신청 접속 페이지인지 관심 과목 접속 페이지인지 구분
	private JLabel				lblAccessablePeriod;		// 접속 가능 기간을 화면에 출력하는 Label
	private CurrentTimeLabel	lblCurrentTime;				// 현재 시간을 화면에 출력하는 Label
	private Date				dtAccessStart, dtAccessEnd;	// 접속가능 시작/종료 일시
	protected Date				dtSignUpStart, dtSignUpEnd;	// 수강신청 시작/종료 일시
	private JButton				btnAccess, btnSetPeriod;	// 접속 버튼 / 기간 설정 버튼
	private ButtonListener		buttonListener;				// 버튼이 클릭되었을 때, 화면을 전환하는 Listener
	protected SimpleDateFormat	dayTime;					// 화면에 날짜를 표시하기 위한 Format
	
	private	SubPage				primary;					// Upcall 을 위한 상위 페이지 저장
	protected AccessPage		thisPage;					// ButtonListener 에서 this 를 넘겨주기 위함
	private SetPeriodBox		setPeriodBox;				// 기간 설정 창
	
	// Method
	public AccessPage(SubPage subPage) {	// Constructor
		super();

		this.primary = subPage;
		
		this.setBounds(300, 150, 980, 570);
		this.setBackground(Color.white);
		this.setLayout(null);
		
		initData();
	}
	
	public void setButtonVisible() {
		if(User.isAdmin)	this.btnSetPeriod.setVisible(true);		// 관리자 여부에 따라 표시 여부 결정
		else				this.btnSetPeriod.setVisible(false);
	}
	
	public void initData() {
		bIfManager = false;	// 관리자 계정인지 아닌지 판단해서 값 설정 (현재 default를 관리자로 설정)
		
		this.dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	// 화면에 날짜를 표시하기 위한 포맷 설정
		
		this.buttonListener = new ButtonListener();				// 버튼 작동을 위한 Listener 객체 생성
		
		// 접속가능기간 초기화
		this.dtAccessStart = new Date(117,11,2,10,0,0);
		this.dtAccessEnd = new Date(117,11,2,18,0,0);
		
		// 접속 가능 기간을 화면에 표시
		this.lblAccessablePeriod = new JLabel("접속가능기간 : "+dayTime.format(this.dtAccessStart)+" ~ "+dayTime.format(this.dtAccessEnd));	
		this.lblAccessablePeriod.setBounds(0, 200, 980, 100);
		this.lblAccessablePeriod.setHorizontalAlignment(JLabel.CENTER);
		this.add(this.lblAccessablePeriod);		
				
		// 현재 시간을 화면에 표시
		this.lblCurrentTime = new CurrentTimeLabel();	
		this.lblCurrentTime.setBounds(0, 300, 980, 100);
		this.lblCurrentTime.setHorizontalAlignment(JLabel.CENTER);
		this.add(this.lblCurrentTime);
		this.lblCurrentTime.start();
		
		// 접속 버튼을 화면에 부착
		this.btnAccess = new JButton("접속");
		this.btnAccess.setBounds(440, 420, 100, 30);
		this.btnAccess.addActionListener(buttonListener);
		this.add(this.btnAccess);
		
		// 기간 설정 버튼을 화면에 부착	
		this.btnSetPeriod = new JButton("기간설정");
		this.btnSetPeriod.setBounds(440, 470, 100, 30);
		this.btnSetPeriod.addActionListener(buttonListener);	
		this.add(this.btnSetPeriod);
	}
	
	public void setAccessPeriod(Date dtAccessStart, Date dtAccessEnd) {	// 접속 가능 기간 설정
		this.dtAccessStart = dtAccessStart;
		this.dtAccessEnd = dtAccessEnd;
		// 접속 가능 기간 설정

		this.lblAccessablePeriod.setText("접속가능기간 : "+dayTime.format(this.dtAccessStart)+" ~ "+dayTime.format(this.dtAccessEnd));
		// 설정된 접속 가능 기간을 화면에 표시
	}

	public void setSignUpPeriod(Date dtSignUpStart, Date dtSignUpEnd) {
		// SignUpAccessPage 에서 Overiding
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();

			if(obj == btnAccess) {	// 접속 버튼
				if(dtAccessStart.before(new Date()) && dtAccessEnd.after(new Date())) {	// 접속 가능 기간인 경우,
					if(nPageType == SIGN_UP) {												// 수강 신청 접속 페이지인 경우,
						primary.changeBody(primary.getApplicationlecture());				// SignUpPage로 페이지 전환						
					}else if(nPageType == INTEREST_LECTURE) {								// 관심 과목 접속 페이지인 경우,
						primary.changeBody(primary.getInterestLecture());										// IntersetLecturePage로 페이지 전환
					}
				}else {																	// 접속 가능 기간이 아닌 경우,
					JOptionPane.showMessageDialog(null, "접속 가능 기간이 아닙니다!");			// 알림창 표시				
				}
			}else {					// 기간 설정 버튼
				// 기간설정 버튼이 visible이면 이미 관리자 계정이라는 것이기 때문에 따로 조건 필요 x 
				if(nPageType == SIGN_UP) {												// 수강 신청 기간 설정인 경우
					setPeriodBox = new SetPeriodBox(thisPage,dtSignUpStart,dtSignUpEnd,dtAccessStart,dtAccessEnd);	// 수강 신청 기간 설정 BOX 생성
				}else if(nPageType == INTEREST_LECTURE) {								// 관심 과목 기간 설정인 경우
					setPeriodBox = new SetPeriodBox(thisPage,dtAccessStart,dtAccessEnd);							// 관심 과목 기간 설정 BOX 생성
				}
			}			
		}
	}
}