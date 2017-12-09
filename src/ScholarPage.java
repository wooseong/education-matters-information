import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScholarPage extends JPanel {
	private JLabel name;
	private JLabel total;
	private SelfCreateTable table;
	private DBController db;
	
	public ScholarPage() {
		this.setLayout(null);
		
		this.name = new JLabel();
		this.add(name);
		
		this.total = new JLabel();
		this.add(total);
	}
	
	public void initPage() {
		this.name.setText(User.USERNAME);
		
	}
}
