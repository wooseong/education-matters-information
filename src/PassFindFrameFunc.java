import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class PassFindFrameFunc implements ActionListener{
	Connection con;
	PassFindFrameFunc(Connection con){
		this.con = con;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new PassFindFrame(con);
	}
	
}
