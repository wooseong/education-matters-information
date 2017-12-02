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
	
	String[][] data = {{"1", "2017-08-14 ∼ 2018-02-28" , "전체과정","2017/2학기", "[학생]장학선발내역조회(시작일)", "장학", "매일 00:00:00 ∼ 23:59:59"},
			{},{}};
	String[] colum = {"NO","기간","과정그룹","년도/학기","학사일정","업무구분","시간"};
//	DefaultTableModel model = new DefaultTableModel(data,colum);//데이터와 컬럼이름을 가지고 모델 객체 생성

//	JTable table = new JTable(model);// 모델객체를 가지고 테이블 생성

//	JScrollPane scroll = new JScrollPane(table);//스크롤 구현하기 위해 생성
	}
}