import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class PassFindFunc implements ActionListener {
	Connection con;
	PassFindFrame pframe;

	PassFindFunc(PassFindFrame pframe, Connection con) {
		this.pframe = pframe;
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sid = pframe.getSidField().getText();
		String name = pframe.getNameField().getText();

		if (sid.equals("") || name.equals("") )
			JOptionPane.showMessageDialog(null, "빈칸을 모두 입력하세요.");
		else {
			try {
				ResultSet rs;
				PreparedStatement query = con.prepareStatement("select sname, password, question from student where sid = ?");

				query.setString(1, sid);
				rs = query.executeQuery();

				if (rs.next()) {
					String nameCheck = rs.getString("sname");
					String password = rs.getString("password");

					if (name.equals(nameCheck)) {
						JOptionPane.showMessageDialog(null, "비밀번호 : " + password);
						pframe.getSidField().setText("");
						pframe.getNameField().setText("");
					} else
						JOptionPane.showMessageDialog(null, "정보가 일치하지 않습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "아이디가 없습니다.");
				}
			} catch (SQLException sqex) {
				System.out.println(sqex.getMessage());
				System.out.println(sqex.getSQLState());
			}
		}
	}
}
