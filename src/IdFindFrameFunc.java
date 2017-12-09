import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class IdFindFrameFunc implements ActionListener{
	Connection con;
	IdFindFrameFunc(Connection con){
		this.con = con;
	}
	//학번찾기버튼을 눌렀을경우 IdFrame을 생성(새로운 창 생성)
	@Override
	public void actionPerformed(ActionEvent e) {
		new IdFindFrame(con);
	}
	
}
