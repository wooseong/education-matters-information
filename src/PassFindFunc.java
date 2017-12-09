import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class PassFindFunc implements ActionListener {
	Connection con;//DB����
	PassFindFrame pframe;//�ؽ�Ʈ�ʵ忡 ��� ������ ���� pFrame����

	PassFindFunc(PassFindFrame pframe, Connection con) {
		this.pframe = pframe;
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ID = pframe.getIDField().getText();//IdField�� ���� �й� �ؽ�Ʈ ������ ����
		String Name = pframe.getNameField().getText();//nameField�� ���� �̸� �ؽ�Ʈ ������ ����

		if (ID.equals("") || Name.equals(""))//�ؽ�Ʈ�� ��ĭ�ϰ��
			JOptionPane.showMessageDialog(null, "��ĭ�� ��� �Է��ϼ���.");
		else {

			DBController db = new DBController(DBconf.DB); //���ο� ����Ǿ��ִ� DB���Ͽ� ����
			 db.executeQuery("select pw from student where id = " + ID + " and Name = " + Name);//DB���ִ� ��й�ȣ�� ã�´� �й��� �̸��� ���Ѵ�
			db.getResultSet();//�������� ����
			try {
				if (db.getResultSet().next()) {//�Է��� ������ DB�� ������츸! 
					String idCheck = db.getResultSet().getString("id");//idCheck�� �й������� String�� ����
					String pw = db.getResultSet().getString("pw");     //pw�� ��й�ȣ�� String�� ����

					if (ID.equals(idCheck)) {                          //DB���ִ� ������ ���� �Է��Ѱ��� ��ġ�ϴ� ���� �ִ°��
						JOptionPane.showMessageDialog(null, "��й�ȣ : " + pw);//��й�ȣ�� �˷��ش�
						pframe.getIDField().setText("");
						pframe.getNameField().setText("");
					} else {//��ġ�ϴ� ������ ���� ���
						JOptionPane.showMessageDialog(null, "������ ��ġ���� �ʽ��ϴ�.");
					}
				} else {//��ġ�ϴ� ������ ���� ���
					JOptionPane.showMessageDialog(null, "��ϵȰ��� �����ϴ�.");
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.disconnectDB(); //�Ϸᰡ �Ǿ����� DB�� ��������ش�
		}
	}
}