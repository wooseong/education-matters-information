import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 관심과목 접근 페이지
 * 접근 가능 시간이 아닐 경우 접근이 제한 됨
 * 관리자로 접근시 접근 가능 시간 변경 가능
 * @author bang
 *
 */
public class InterestLectureAccessPage extends AccessPage {
	
	public InterestLectureAccessPage(SubPage subPage) {	// Constructor
		super(subPage);	

		this.nPageType = this.INTEREST_LECTURE;	// 관심 과목 접속 페이지로 설정		
		this.thisPage = this;
	}
}
