import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class IdFindFrameFunc implements ActionListener{
	Connection con;
	IdFindFrameFunc(Connection con){
		this.con = con;
	}
	//�й�ã���ư�� ��������� IdFrame�� ����(���ο� â ����)
	@Override
	public void actionPerformed(ActionEvent e) {
		new IdFindFrame(con);
	}
	
}
