import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class PassFindFunc implements ActionListener {
	Connection con;//DB연결
	PassFindFrame pframe;//텍스트필드에 담긴 정보를 담을 pFrame생성

	PassFindFunc(PassFindFrame pframe, Connection con) {
		this.pframe = pframe;
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ID = pframe.getIDField().getText();//IdField에 적힌 학번 텍스트 정보를 저장
		String Name = pframe.getNameField().getText();//nameField에 적힌 이름 텍스트 정보를 저장

		if (ID.equals("") || Name.equals(""))//텍스트에 빈칸일경우
			JOptionPane.showMessageDialog(null, "빈칸을 모두 입력하세요.");
		else {

			DBController db = new DBController(DBconf.DB); //내부에 저장되어있는 DB파일에 연결
			 db.executeQuery("select pw from student where id = " + ID + " and Name = " + Name);//DB에있는 비밀번호를 찾는다 학번과 이름을 비교한다
			//db.getResultSet();//그정보를 저장
			try {
				if (db.getResultSet().next()) {//입력한 정보가 DB에 있을경우만! 
					String idCheck = db.getResultSet().getString("id");//idCheck에 학번정보를 String로 저장
					String pw = db.getResultSet().getString("pw");     //pw에 비밀번호를 String로 저장

					if (ID.equals(idCheck)) {                          //DB에있는 정보와 내가 입력한값이 일치하는 값이 있는경우
						JOptionPane.showMessageDialog(null, "비밀번호 : " + pw);//비밀번호을 알려준다
						pframe.getIDField().setText("");
						pframe.getNameField().setText("");
					} else {//일치하는 정보가 없는 경우
						JOptionPane.showMessageDialog(null, "정보가 일치하지 않습니다.");
					}
				} else {//일치하는 정보가 없는 경우
					JOptionPane.showMessageDialog(null, "등록된것이 없습니다.");
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.disconnectDB(); //완료가 되었으면 DB를 종료시켜준다
		}
	}
}
