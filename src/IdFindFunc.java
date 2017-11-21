import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class IdFindFunc implements ActionListener {
	Connection con;
	IdFindFrame pframe;

	IdFindFunc(IdFindFrame pframe, Connection con) {
		this.pframe = pframe;
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String dob = pframe.getSidField().getText();
		String name = pframe.getNameField().getText();

		if (dob.equals("") || name.equals("") )
			JOptionPane.showMessageDialog(null, "빈칸을 모두 입력하세요.");
		else {
			try {
				ResultSet rs;
				PreparedStatement query = con.prepareStatement("select sname, sid, question from student where dob = ?");

				query.setString(1, name);
				rs = query.executeQuery();

				if (rs.next()) {
					String nameCheck = rs.getString("sname");
					String id = rs.getString("sid");

					if (name.equals(nameCheck) ) {
						JOptionPane.showMessageDialog(null, "아이디 : " + id);
						pframe.getSidField().setText("");
						pframe.getNameField().setText("");
					} else
						JOptionPane.showMessageDialog(null, "정보가 일치하지 않습니다.");
				} else {
					JOptionPane.showMessageDialog(null, "등록된것이 없습니다.");
				}
			} catch (SQLException sqex) {
				System.out.println(sqex.getMessage());
				System.out.println(sqex.getSQLState());
			}
		}
	}
}
