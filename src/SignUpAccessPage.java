
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SignUpAccessPage extends AccessPage {

	private JLabel	lblSignUpPeriod;			// 수강 신청 기간을 화면에 출력하는 Label
	
	public SignUpAccessPage(SubPage subPage) {	// Constructor
		super(subPage);	

		this.initSignUpData();
		
		this.nPageType = this.SIGN_UP;	// 수강 신청 접속 페이지로 설정		
		this.thisPage = this;
	}
	
	public void initSignUpData() {		
		// 수강 신청 기간 초기화
		this.dtSignUpStart = new Date(117,11,2,10,0,0);
		this.dtSignUpEnd = new Date(117,11,2,18,0,0);
		
		// 수강 신청 기간을 화면에 표시
		this.lblSignUpPeriod = new JLabel("수강신청기간 : "+dayTime.format(this.dtSignUpStart)+" ~ "+dayTime.format(this.dtSignUpEnd));	
		this.lblSignUpPeriod.setBounds(0, 100, 980, 100);
		this.lblSignUpPeriod.setHorizontalAlignment(JLabel.CENTER);
		this.add(this.lblSignUpPeriod);					
	}
	
	public void setSignUpPeriod(Date dtSignUpStart, Date dtSignUpEnd) {	// 수강 신청 기간 설정
		this.dtSignUpStart = dtSignUpStart;
		this.dtSignUpEnd = dtSignUpEnd;
		// 수강 신청 기간 설정
		
		this.lblSignUpPeriod.setText("수강신청기간 : "+dayTime.format(this.dtSignUpStart)+" ~ "+dayTime.format(this.dtSignUpEnd));
		// 설정된 수강 신청 기간을 화면에 표시
	}
}