import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class EducationMattersSchedule extends JPanel{

	public EducationMattersSchedule() {
		setPreferredSize(new Dimension(1030, 570));

		setBackground(new Color(0xDDDDDD));
		setLayout(null);
	
	String[][] data = {{"1", "2017-08-14 ∼ 2018-02-28" , "전체과정","2017/2학기", "[학생]장학선발내역조회(시작일)", "장학", "매일 00:00:00 ∼ 23:59:59"},
						{"2", "2017-08-14 ∼ 2017-12-31" , "전체과정","2017/2학기", "[학생]등록금고지서(전일제)출력기간", "등록", "매일 00:00:00 ∼ 23:59:59"},
						{"3", "2017-08-14 ∼ 2018-12-31" , "전체과정","2017/2학기", "[학생]등록금고지서(시간제)출력기간", "등록", "매일 00:00:00 ∼ 23:59:59"},
						{"4", "2017-08-21 ∼ 2017-12-31" , "전체과정","2017/2학기", "[학생]등록금납부확인서출력", "등록", "매일 00:00:00 ∼ 23:59:59"},
						{"5", "2017-10-16 ∼ 2017-12-04" , "전체과정","2017/2학기", "[학생]성적조회기간(중간)", "성적", "시작일 09:00:00 ∼ 종료일 23:59:59"},
						{"6", "2017-10-23 ∼ 2017-12-10" , "전체과정","2017/2학기", "[학생]공결신청기간", "수업", "시작일 09:00:00 ∼ 종료일 23:59:59"},
						{"7", "2017-11-21 ∼ 2017-12-01" , "전체과정","2017/2학기", "[학생]등록금고지서(분납4차)출력기간", "등록", "매일 00:00:00 ∼ 23:59:59"},
						{"8", "2017-12-04 ∼ 2017-12-20" , "전체과정","2017/2학기", "[학생]강의평가기간", "강평", "시작일 10:00:00 ∼ 종료일 17:00:00"}};
	String[] colum = {"NO","기간","과정그룹","년도/학기","학사일정","업무구분","시간"};
	DefaultTableModel model = new DefaultTableModel(data,colum);//데이터와 컬럼이름을 가지고 모델 객체 생성

	JTable table = new JTable(model);// 모델객체를 가지고 테이블 생성

	JScrollPane scroll = new JScrollPane(table);//스크롤 구현하기 위해 생성
	
	add(scroll);
	
	}
}