import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class PassFindFrameFunc implements ActionListener{
	Connection con;
	PassFindFrameFunc(Connection con){
		this.con = con;
	}
	//비밀번호찾기 버튼을 눌렀을경우 passFindFrame을 생성(새로운 창 생성)
	@Override
	public void actionPerformed(ActionEvent e) {
		new PassFindFrame(con);
	}
	
}
