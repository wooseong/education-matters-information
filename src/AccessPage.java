import java.awt.LayoutManager;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccessPage extends JPanel{

	private JLabel				lblSignUpPeriod;			// 수강 신청 기간을 화면에 출력하는 Label
	private JLabel				lblAccessablePeriod;		// 접속 가능 기간을 화면에 출력하는 Label
	private Date				dtSignUpStart, dtSignUpEnd;	// 수강신청 시작/종료 기간
	private Date				dtAccessStart, dtAccessEnd;	// 접속가능 시작/종료 기간
	private SimpleDateFormat	dayTime;					// 화면에 날짜를 표시하기 위한 포맷
	
	public JLabel getLblSignUpPeriod() {
		return lblSignUpPeriod;
	}

	public void setLblSignUpPeriod(JLabel lblSignUpPeriod) {
		this.lblSignUpPeriod = lblSignUpPeriod;
	}

	public JLabel getLblAccessablePeriod() {
		return lblAccessablePeriod;
	}

	public void setLblAccessablePeriod(JLabel lblAccessablePeriod) {
		this.lblAccessablePeriod = lblAccessablePeriod;
	}

	public Date getDtSignUpStart() {
		return dtSignUpStart;
	}

	public void setDtSignUpStart(Date dtSignUpStart) {
		this.dtSignUpStart = dtSignUpStart;
	}

	public Date getDtSignUpEnd() {
		return dtSignUpEnd;
	}

	public void setDtSignUpEnd(Date dtSignUpEnd) {
		this.dtSignUpEnd = dtSignUpEnd;
	}

	public Date getDtAccessStart() {
		return dtAccessStart;
	}

	public void setDtAccessStart(Date dtAccessStart) {
		this.dtAccessStart = dtAccessStart;
	}

	public Date getDtAccessEnd() {
		return dtAccessEnd;
	}

	public void setDtAccessEnd(Date dtAccessEnd) {
		this.dtAccessEnd = dtAccessEnd;
	}

	public SimpleDateFormat getDayTime() {
		return dayTime;
	}

	public void setDayTime(SimpleDateFormat dayTime) {
		this.dayTime = dayTime;
	}

	public AccessPage() {
		super();
		// TODO Auto-generated constructor stub
		initData();
	}

	public AccessPage(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
		initData();
	}

	public AccessPage(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
		initData();
	}

	public AccessPage(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
		initData();
	}
	
	public void initData() {
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
}
