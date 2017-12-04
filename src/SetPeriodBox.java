import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;

public class SetPeriodBox extends JFrame {
	private JLabel				lblSignUpPeriod;					// 수강 신청 기간을 화면에 출력하는 Label
	private JLabel				lblAccessablePeriod;				// 접속 가능 기간을 화면에 출력하는 Label
	private JLabel				lblArr[];							// 년/월/일/시/분/초 등을 저장하기 위함
	private JTextField			txtSignUpStart[], txtSignUpEnd[];	// 수강 신청 기간을 표시하는 텍스트 필드
	private JTextField			txtAccessStart[], txtAccessEnd[];	// 접속 가능 기간을 표시하는 텍스트 필드
	private JButton				btnSet, btnExit;					// 설정 버튼 / 종료 버튼
	private ButtonListener		buttonListener;						// 버튼이 클릭되었을 때, 화면을 전환하는 Listener
	private SignUpAccessPage	primary;							// UpCall을 위한 상위 패널
	
	public SetPeriodBox(SignUpAccessPage page, Date dtSignUpStart, Date dtSignUpEnd, Date dtAccessStart, Date dtAccessEnd) {
		super();
		
		this.primary = page;	// UpCall을 위해 상위 클래스 객체 저장

		this.buttonListener = new ButtonListener();	// 버튼 작동을 위한 Listener 객체 생성
		
		this.setBounds(400, 200, 480, 320);	
		this.setPreferredSize(new Dimension(480, 320));
		this.setBackground(Color.white);
		this.setLayout(null);
		
		this.lblSignUpPeriod = new JLabel("수강신청기간 설정");	
		this.lblSignUpPeriod.setBounds(10, 20, 200, 20);
		//this.lblSignUpPeriod.setFont(new Font("VERDANA",Font.BOLD,15));
		this.add(this.lblSignUpPeriod);
		// 수강 신청 기간 설정을 화면에 표시

		this.lblAccessablePeriod = new JLabel("접속가능기간 설정");	
		this.lblAccessablePeriod.setBounds(10, 120, 200, 20);
		//this.lblAccessablePeriod.setFont(new Font("VERDANA",Font.BOLD,15));
		this.add(this.lblAccessablePeriod);
		// 접속 가능 기간 설정을 화면에 표시
		
		this.txtSignUpStart = new JTextField[6];
		this.txtSignUpEnd = new JTextField[6];
		this.txtAccessStart = new JTextField[6];
		this.txtAccessEnd = new JTextField[6];
		// 텍스트 필드 배열을 생성
		
		this.txtSignUpStart[0] = new JTextField(String.valueOf(1900+dtSignUpStart.getYear()));
		this.txtSignUpStart[1] = new JTextField(String.valueOf(1+dtSignUpStart.getMonth()));
		this.txtSignUpStart[2] = new JTextField(String.valueOf(dtSignUpStart.getDate()));
		this.txtSignUpStart[3] = new JTextField(String.valueOf(dtSignUpStart.getHours()));
		this.txtSignUpStart[4] = new JTextField(String.valueOf(dtSignUpStart.getMinutes()));
		this.txtSignUpStart[5] = new JTextField(String.valueOf(dtSignUpStart.getSeconds()));
		
		this.txtSignUpEnd[0] = new JTextField(String.valueOf(1900+dtSignUpEnd.getYear()));
		this.txtSignUpEnd[1] = new JTextField(String.valueOf(1+dtSignUpEnd.getMonth()));
		this.txtSignUpEnd[2] = new JTextField(String.valueOf(dtSignUpEnd.getDate()));
		this.txtSignUpEnd[3] = new JTextField(String.valueOf(dtSignUpEnd.getHours()));
		this.txtSignUpEnd[4] = new JTextField(String.valueOf(dtSignUpEnd.getMinutes()));
		this.txtSignUpEnd[5] = new JTextField(String.valueOf(dtSignUpEnd.getSeconds()));

		this.txtAccessStart[0] = new JTextField(String.valueOf(1900+dtAccessStart.getYear()));
		this.txtAccessStart[1] = new JTextField(String.valueOf(1+dtAccessStart.getMonth()));
		this.txtAccessStart[2] = new JTextField(String.valueOf(dtAccessStart.getDate()));
		this.txtAccessStart[3] = new JTextField(String.valueOf(dtAccessStart.getHours()));
		this.txtAccessStart[4] = new JTextField(String.valueOf(dtAccessStart.getMinutes()));
		this.txtAccessStart[5] = new JTextField(String.valueOf(dtAccessStart.getSeconds()));

		this.txtAccessEnd[0] = new JTextField(String.valueOf(1900+dtAccessEnd.getYear()));
		this.txtAccessEnd[1] = new JTextField(String.valueOf(1+dtAccessEnd.getMonth()));
		this.txtAccessEnd[2] = new JTextField(String.valueOf(dtAccessEnd.getDate()));
		this.txtAccessEnd[3] = new JTextField(String.valueOf(dtAccessEnd.getHours()));
		this.txtAccessEnd[4] = new JTextField(String.valueOf(dtAccessEnd.getMinutes()));
		this.txtAccessEnd[5] = new JTextField(String.valueOf(dtAccessEnd.getSeconds()));
		// 현재 설정된 기간으로 각 텍스트 필드들의 기본값 설정
		
		for(int j=0;j<4;j++) {
			this.lblArr = new JLabel[8];
			this.lblArr[0] = new JLabel("년");
			this.lblArr[1] = new JLabel("월");
			this.lblArr[2] = new JLabel("일");
			this.lblArr[3] = new JLabel("시");
			this.lblArr[4] = new JLabel("분");
			this.lblArr[5] = new JLabel("초");
			this.lblArr[6] = new JLabel("부터");
			this.lblArr[7] = new JLabel("까지");
			
			for(int i=0; i<7;i++) {
				int y=0;
				
				if(j==0)		y = 50;
				else if(j==1)	y = 80;
				else if(j==2)	y = 150;
				else if(j==3)	y = 180;
				
				if(i==6) {
					if(j%2==0) {
						this.lblArr[6].setBounds(370, y, 50, 20);
						this.add(this.lblArr[6]);
					}else {
						this.lblArr[7].setBounds(370, y, 50, 20);
						this.add(this.lblArr[7]);
					}
				}else {
					this.lblArr[i].setBounds(50+(i*60), y, 20, 20);
					this.add(this.lblArr[i]);
				}
			}
		}
		// 각 텍스트 필드 옆에 년/월/일/시/분/초/부터/까지 등의 내용을 담은 Label 부착
		
		for(int i=0; i<6;i++) {
			this.txtSignUpStart[i].setBounds(10+(i*60), 50, 40, 20);
			this.txtSignUpEnd[i].setBounds(10+(i*60), 80, 40, 20);
			this.txtAccessStart[i].setBounds(10+(i*60), 150, 40, 20);
			this.txtAccessEnd[i].setBounds(10+(i*60), 180, 40, 20);
			
			this.add(this.txtSignUpStart[i]);
			this.add(this.txtSignUpEnd[i]);
			this.add(this.txtAccessStart[i]);
			this.add(this.txtAccessEnd[i]);
		}
		// 텍스트 필드들을 적절한 위치에 부착
		
		this.btnSet = new JButton("설정");
		this.btnSet.setBounds(10, 220, 60, 30);
		this.btnSet.addActionListener(buttonListener);
		this.add(this.btnSet);
		// 설정 버튼 화면에 부착
		
		this.btnExit = new JButton("종료");
		this.btnExit.setBounds(80, 220, 60, 30);
		this.btnExit.addActionListener(buttonListener);
		this.add(this.btnExit);
		// 종료 버튼 화면에 부착
		
		this.pack();
		this.setVisible(true);
	}
	
	public void setPeriod() {
		// 변경된 수강 신청 시작/종료 기간
		Date dtSignUpStart = new Date(
				Integer.parseInt(txtSignUpStart[0].getText())-1900,
				Integer.parseInt(txtSignUpStart[1].getText())-1,
				Integer.parseInt(txtSignUpStart[2].getText()),
				Integer.parseInt(txtSignUpStart[3].getText()),
				Integer.parseInt(txtSignUpStart[4].getText()),
				Integer.parseInt(txtSignUpStart[5].getText()));
		
		Date dtSignUpEnd= new Date(
				Integer.parseInt(txtSignUpEnd[0].getText())-1900,
				Integer.parseInt(txtSignUpEnd[1].getText())-1,
				Integer.parseInt(txtSignUpEnd[2].getText()),
				Integer.parseInt(txtSignUpEnd[3].getText()),
				Integer.parseInt(txtSignUpEnd[4].getText()),
				Integer.parseInt(txtSignUpEnd[5].getText()));
		
		Date dtAccessStart = new Date(
				Integer.parseInt(txtAccessStart[0].getText())-1900,
				Integer.parseInt(txtAccessStart[1].getText())-1,
				Integer.parseInt(txtAccessStart[2].getText()),
				Integer.parseInt(txtAccessStart[3].getText()),
				Integer.parseInt(txtAccessStart[4].getText()),
				Integer.parseInt(txtAccessStart[5].getText()));
		
		Date dtAccessEnd= new Date(
				Integer.parseInt(txtAccessEnd[0].getText())-1900,
				Integer.parseInt(txtAccessEnd[1].getText())-1,
				Integer.parseInt(txtAccessEnd[2].getText()),
				Integer.parseInt(txtAccessEnd[3].getText()),
				Integer.parseInt(txtAccessEnd[4].getText()),
				Integer.parseInt(txtAccessEnd[5].getText()));
		// 현재 텍스트 필드에 있는 값으로 변경된 기간을 설정
		
		primary.setPeriod(dtSignUpStart, dtSignUpEnd, dtAccessStart, dtAccessEnd);
		// 업콜을 통해 수강신청 접속 페이지의 기간을 변경된 기간으로 설정
	}
	
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();

			if(obj == btnSet) {		// 설정 버튼
				setPeriod();	// 현재 텍스트 필드에 있는 값으로 기간을 설정
			}else {					// 종료 버튼
				String[] buttons = {"저장한 후 종료", "저장하지 않고 종료", "취소"};
				int result = JOptionPane.showOptionDialog(null, "종료하시겠습니까?", "", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "저장한 후 종료");

				if(result==JOptionPane.YES_OPTION) {		// 저장한 후 종료, 변경된 값으로 설정한 후 frame 종료
					setPeriod();
					dispose();
				}else if(result==JOptionPane.NO_OPTION)  {	// 저장하지 않고 종료, frame 종료
					dispose();
				}											// else 취소, 아무것도 하지 않음
			}			
		}
	}
}
