import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;

public class CurrentTimeLabel extends JLabel implements Runnable{
	private Thread myThread;
	private	Date currentTime; // 현재 시간을 저장할 Date Class
	
	public CurrentTimeLabel() {	// Constructor
		this.myThread = null;
		this.currentTime = new Date(); 	// Date() Constructor를 이용해 현재 시간으로 초기화
	}
	
	public Date getCurrentTime() {	return this.currentTime;	}	// 현재 시간을 반환
	
	public void initThread() {		this.myThread = null;	}	// 쓰레드 초기화
	
	public void start() {	// 쓰레드를 작동시키는 메서드
		if(this.myThread == null)	this.myThread = new Thread(this);
		this.myThread.start();
	}
	
	public void stop() {
		if(this.myThread != null)	this.myThread.stop();		
	}
	
	public void run() {	// 쓰레드를 이용해 현재 시간 1초마다 갱신
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	// 화면에 날짜를 표시하기 위한 포맷 설정
		try {
			while(true) {	
				this.currentTime = new Date();	//  Date() Constructor를 이용해 현재 시간으로 초기화 
				String str = dayTime.format(this.currentTime);	
				this.setText("현재시간 : "+str);	// 화면에 현재 시간 표시
				this.myThread.sleep(1000);	// 1초 대기
			}
		} catch(Exception e) {}
	}
}