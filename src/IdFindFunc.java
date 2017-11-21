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
			JOptionPane.showMessageDialog(null, "��ĭ�� ��� �Է��ϼ���.");
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
						JOptionPane.showMessageDialog(null, "���̵� : " + id);
						pframe.getSidField().setText("");
						pframe.getNameField().setText("");
					} else
						JOptionPane.showMessageDialog(null, "������ ��ġ���� �ʽ��ϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "��ϵȰ��� �����ϴ�.");
				}
			} catch (SQLException sqex) {
				System.out.println(sqex.getMessage());
				System.out.println(sqex.getSQLState());
			}
		}
	}
}
