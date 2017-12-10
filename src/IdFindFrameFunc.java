import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class IdFindFrameFunc implements ActionListener{
	Connection con;//DB연결 함수
	IdFindFrameFunc(Connection con){//정보를 가져온다
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new IdFindFrame(con);//동시에 실행 학번찾기버튼을 눌렀을경우 IdFrame을 생성(새로운 창 생성)
	}
	
}
