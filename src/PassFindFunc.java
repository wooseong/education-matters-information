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
			JOptionPane.showMessageDialog(null, "��ĭ�� ��� �Է��ϼ���.");
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
						JOptionPane.showMessageDialog(null, "��й�ȣ : " + password);
						pframe.getSidField().setText("");
						pframe.getNameField().setText("");
					} else
						JOptionPane.showMessageDialog(null, "������ ��ġ���� �ʽ��ϴ�.");
				} else {
					JOptionPane.showMessageDialog(null, "���̵� �����ϴ�.");
				}
			} catch (SQLException sqex) {
				System.out.println(sqex.getMessage());
				System.out.println(sqex.getSQLState());
			}
		}
	}
}
