import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class RegisterFrameFunc implements ActionListener{
	Connection con;
	RegisterFrameFunc(Connection con) {
		this.con = con;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		new RegisterFrame(con);
	}
}
