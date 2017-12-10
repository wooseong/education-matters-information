import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class IdFindFunc implements ActionListener { 
	Connection con; //DB연결
	IdFindFrame Iframe; //텍스트필드에 담긴 정보를 담을 IFrame생성

	IdFindFunc(IdFindFrame pframe, Connection con) {//생성자
		this.Iframe = pframe;
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String Name = null;
		Name = Iframe.getNameField().getText();         //nameField에 적힌 이름 텍스트 정보를 저장
		String Birth = Iframe.getBirthField().getText();//BirthField에 적힌 생년월일 텍스트 정보를 저장

		if (Name == "" || Birth.equals("")) {  //텍스트에 빈칸일경우
			JOptionPane.showMessageDialog(null, "빈칸을 모두 입력하세요.");
		} else {

			DBController db = new DBController(DBconf.DB); //내부에 저장되어있는 DB파일에 연결
			 db.executeQuery("select id from student where birth = " + Birth + " and name = " + Name); //DB에있는 학번을 찾는다 이름과 생년월일을 비교한다
			//db.getResultSet();//그정보를 저장
			try {
				if (db.getResultSet().next()) { //입력한 정보가 DB에 있을경우만! 
					String nameCheck = db.getResultSet().getString("name"); //namecheck에 이름정보를 String로 저장
					String id = db.getResultSet().getString("id");          //id에 학번정보를 String로 저장

					if (Name.equals(nameCheck)) {                           //DB에있는 정보와 내가 입력한값이 일치하는 값이 있는경우
						JOptionPane.showMessageDialog(null, "아이디 : " + id);//학번을 알려준다
						Iframe.getNameField().setText("");
						Iframe.getBirthField().setText("");
					} else {                                               //일치하는 정보가 없는 경우
						JOptionPane.showMessageDialog(null, "정보가 일치하지 않습니다.");
					}
				} else {//일치하는 정보가 없는 경우
					JOptionPane.showMessageDialog(null, "등록된것이 없습니다.");
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.disconnectDB();//완료가 되었으면 DB를 종료시켜준다
		}
	}
}