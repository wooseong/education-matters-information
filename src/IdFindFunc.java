import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class IdFindFunc implements ActionListener { 
	Connection con; //DB����
	IdFindFrame Iframe; //�ؽ�Ʈ�ʵ忡 ��� ������ ���� IFrame����

	IdFindFunc(IdFindFrame pframe, Connection con) {//������
		this.Iframe = pframe;
		this.con = con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String Name = null;
		Name = Iframe.getNameField().getText();         //nameField�� ���� �̸� �ؽ�Ʈ ������ ����
		String Birth = Iframe.getBirthField().getText();//BirthField�� ���� ������� �ؽ�Ʈ ������ ����

		if (Name == "" || Birth.equals("")) {  //�ؽ�Ʈ�� ��ĭ�ϰ��
			JOptionPane.showMessageDialog(null, "��ĭ�� ��� �Է��ϼ���.");
		} else {

			DBController db = new DBController("./DB/utf-8.db"); //���ο� ����Ǿ��ִ� DB���Ͽ� ����
			 db.executeQuery("select ID from student where Birth = " + Birth + " and Name = " + Name); //DB���ִ� �й��� ã�´� �̸��� ��������� ���Ѵ�
			db.getResultSet();//�������� ����
			try {
				if (db.getResultSet().next()) { //�Է��� ������ DB�� ������츸! 
					String nameCheck = db.getResultSet().getString("Name"); //namecheck�� �̸������� String�� ����
					String id = db.getResultSet().getString("ID");          //id�� �й������� String�� ����

					if (Name.equals(nameCheck)) {                           //DB���ִ� ������ ���� �Է��Ѱ��� ��ġ�ϴ� ���� �ִ°��
						JOptionPane.showMessageDialog(null, "���̵� : " + id);//�й��� �˷��ش�
						Iframe.getNameField().setText("");
						Iframe.getBirthField().setText("");
					} else {                                               //��ġ�ϴ� ������ ���� ���
						JOptionPane.showMessageDialog(null, "������ ��ġ���� �ʽ��ϴ�.");
					}
				} else {//��ġ�ϴ� ������ ���� ���
					JOptionPane.showMessageDialog(null, "��ϵȰ��� �����ϴ�.");
				}
			} catch (HeadlessException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.disconnectDB();//�Ϸᰡ �Ǿ����� DB�� ��������ش�
		}
	}
}
