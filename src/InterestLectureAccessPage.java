import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InterestLectureAccessPage extends AccessPage {
	
	public InterestLectureAccessPage(SubPage subPage) {	// Constructor
		super(subPage);	

		this.nPageType = this.INTEREST_LECTURE;	// 관심 과목 접속 페이지로 설정		
		this.thisPage = this;
	}
}