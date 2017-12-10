import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class PassFindFrameFunc implements ActionListener{
	Connection con;//DB연결함수
	PassFindFrameFunc(Connection con){//정보를 가져온다
		this.con = con;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new PassFindFrame(con);//비밀번호찾기 버튼을 눌렀을경우 passFindFrame을 생성(새로운 창 생성)
	}
	
}