package mabiTimer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Calendar;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MainPage extends JFrame implements Runnable, ItemListener, ActionListener // 시간을 받아오기 위한 스레드와 체크박스와 버튼을 사용하기 위해 액션 리스너와 아이템 리스너를 추가하는 JFrame 상속 MainPage 클래스
{ 	
	//탭 패널
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); // 스톱워치와 게임 시계 기능을 변경하여 사용할 수 있도록 한 프레임 안에 여러개의 패널을 사용하도록 하는 JTabbedPane
	JPanel 마비노기 = new JPanel(); // 게임 시계 패널
	JPanel 스톱워치 = new JPanel(); // 스톱워치 패널
	
	//시계 관련 기능
	JPanel clockPanel = new JPanel(); // 시계 부분을 가리키는 시계 패널
	JPanel timePanel = new JPanel(); // 게임 시계와 실제 시계를 표시할 Panel
	JLabel realTime = new JLabel("\uD604\uC7AC \uC2DC\uAC04 :"); // 시계 기능에서 실제 시간을 나타내는 label
	JLabel gameTime = new JLabel("\uC5D0\uB9B0 \uC2DC\uAC04 :"); // 시계 기능에서 게임 시간을 나타내는 label
	JLabel fleta_IsAppear = new JLabel("\uCD9C\uD604 \uC5EC\uBD80"); // NPC 출현 여부를 표시해주는 label
	
	
	JPanel logoPanel = new JPanel(); // 좌상단에 로고를 넣을 수 있도록 label을 넣을 Panel
	JLabel mabiLogo = new JLabel(""); // 로고를 넣을 label
	
	//기능 패널
	JPanel eventPanel = new JPanel(); // 기능이 담겨있는 panel
	JPanel leftEventPanel = new JPanel(); // 왼쪽 기능 panel(fleta, price)
	JPanel rightEventPanel = new JPanel(); // 오른쪽 기능 panel(aditem,setting)
	
	//NPC 플레타 관련 패널
	JPanel fletaTimerPanel = new JPanel(); // 전체 패널
	JPanel fleta_TitlePanel = new JPanel(); // 타이틀 패널
	JLabel fleta_Title = new JLabel("\uD50C\uB808\uD0C0 \uD0C0\uC774\uBA38"); // 타이틀 
	JLabel fleta_Image = new JLabel(""); // 캐릭터 이미지
	
	JPanel fleta_MainPanel = new JPanel(); // 내용 패널
	JPanel fleta_Main_BelowPanel = new JPanel(); //하단 부분 디자인용 패널
	JLabel fleta_AppearTime = new JLabel("(\uCD9C\uD604 \uC2DC\uAC04 : 09~11\uC2DC, 15~17\uC2DC, 19~21\uC2DC)"); // 출현 시간을 안내
	
	//NPC 프라이스 관련 패널
	JPanel priceTimerPanel = new JPanel(); //전체 패널
	JPanel price_TitlePanel = new JPanel(); //타이틀 패널
	JLabel price_Image = new JLabel(""); // 캐릭터 이미지
	JLabel price_Title = new JLabel("\uD504\uB77C\uC774\uC2A4 \uD0C0\uC774\uBA38"); // 타이틀
	
	JPanel price_MainPanel = new JPanel(); // 내용 패널
	
	JPanel price_Main_BelowPanel = new JPanel(); // 내용 하단 부분 디자인용 패널
	JLabel price_RelocateTimeGap = new JLabel("(\uCD9C\uD604 \uC2DC\uAC04 : \uD558\uB8E8 \uAC04\uACA9\uC73C\uB85C \uC774\uB3D9)"); // 출현 간격을 안내 
	
	JPanel price_StuffPanel = new JPanel();  // 내용 부분 패널
	JLabel price_Location = new JLabel("\uCD9C\uD604 \uC704\uCE58"); // NPC 출현 위치를 표시해주는 label
	JLabel price_NextLocation = new JLabel("\uB2E4\uC74C \uC704\uCE58"); // NPC의 다음 출현 위치를 표시해주는 label
	
	//어드밴스드 아이템 관련 패널
	JPanel advancedItemPanel = new JPanel();// 전체 패널
	
	JPanel adItem_TitlePanel = new JPanel();// 타이틀 패널
	JLabel adItem_Title = new JLabel("\uC624\uB298\uC758 \uC5B4\uB4DC\uBC34\uC2A4\uB4DC \uC544\uC774\uD15C"); //타이틀
	JLabel aditem_Image = new JLabel(""); //이미지
	
	JPanel adItem_MainPanel = new JPanel(); //내용 패널
	JLabel adItem_Main = new JLabel("\uC5B4\uB4DC\uBC34\uC2A4\uB4DC \uC544\uC774\uD15C"); //요일별 지급되는 아이템을 표시하는 label
	
	//세팅 관련 패널
	JPanel settingPanel = new JPanel(); // 전체 패널
	
	JPanel setting_TitlePanel = new JPanel(); // 타이틀 패널
	JLabel setting_Image = new JLabel(""); //타이틀 이미지
	JLabel setting_Title = new JLabel("\uC124\uC815"); //타이틀
	
	JPanel setting_MainPanel = new JPanel(); //내용 패널 
	JCheckBox fleta_IsAlarm = new JCheckBox("\uD50C\uB808\uD0C0 \uCD9C\uD604 \uC54C\uB9BC on"); //NPC 출현 알람을 사용할지 결정하는 체크박스 
	int alarmOnOff = 2; // 알람을 실행시킬 지 결정하는 변수
	
	//스톱워치 관련 패널
	JLabel title = new JLabel("\uC2A4\uD1B1\uC6CC\uCE58"); // 스톱워치 타이틀
	JButton Start = new JButton("start"); // 스톱워치 시작 버튼
	JButton Stop = new JButton("stop"); // 스톱워치 종료 버튼
	JLabel timer = new JLabel("press start"); // 스톱워치 시간을 표시하는 label
	
	//스톱워치 관련 변수
	int On; // 스톱워치가 실행중인지 확인하는 변수
	int T = 0; // 프로그램이 실행된 이후의 시간을 저장하는 변수
	int savedT = 0; // 스톱워치가 실행된 순간의 T를 저장하는 변수
	
	Thread thread; //시계 구현에 사용된 Thread
	
	public MainPage() {
		setSize(new Dimension(550,480)); // UI 설정
		setResizable(false);
		
		// 디자인 설정
		
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\source\\33334.png"));
		setFont(new Font("나눔고딕", Font.PLAIN, 12));
		setTitle("마비노기 타이머");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		tabbedPane.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		tabbedPane.setBackground(Color.WHITE);
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("\uB9C8\uBE44\uB178\uAE30 \uC2DC\uACC4", null, 마비노기, null);
		마비노기.setLayout(new BorderLayout(0, 0));
		
		마비노기.add(timePanel, BorderLayout.NORTH);
		timePanel.setBackground(Color.WHITE);
		timePanel.setLayout(new BorderLayout(0, 0));
		
		logoPanel.setBackground(Color.WHITE);
		timePanel.add(logoPanel, BorderLayout.WEST);
		
		
		mabiLogo.setIcon(new ImageIcon(".\\source\\mabinogi_A.png"));
		logoPanel.add(mabiLogo);
		
		
		clockPanel.setBackground(Color.WHITE);
		timePanel.add(clockPanel, BorderLayout.CENTER);
		clockPanel.setLayout(new GridLayout(0, 1, 0, 0));
		clockPanel.add(realTime);
		realTime.setHorizontalAlignment(SwingConstants.LEFT);
		realTime.setForeground(Color.DARK_GRAY);
		realTime.setFont(new Font("나눔고딕", Font.BOLD, 30));
		clockPanel.add(gameTime);
		
		gameTime.setFont(new Font("나눔고딕", Font.BOLD, 30));
		gameTime.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		마비노기.add(eventPanel, BorderLayout.CENTER);
		eventPanel.setBackground(Color.WHITE);
		eventPanel.setLayout(null);
		
		
		leftEventPanel.setBackground(Color.WHITE);
		leftEventPanel.setBounds(0, 0, 269, 229);
		eventPanel.add(leftEventPanel);
		leftEventPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		leftEventPanel.add(fletaTimerPanel);
		fletaTimerPanel.setLayout(new BorderLayout(0, 0));
		
		
		fletaTimerPanel.add(fleta_TitlePanel, BorderLayout.NORTH);
		fleta_TitlePanel.setBackground(Color.LIGHT_GRAY);
		fleta_TitlePanel.setLayout(new BorderLayout(0, 0));
		
		
		fleta_TitlePanel.add(fleta_Title, BorderLayout.CENTER);
		fleta_Title.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		fleta_Title.setBackground(Color.WHITE);
		fleta_Title.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		fleta_Image.setIcon(new ImageIcon(".\\source\\fleta.png"));
		fleta_TitlePanel.add(fleta_Image, BorderLayout.WEST);
		
		
		fletaTimerPanel.add(fleta_MainPanel, BorderLayout.CENTER);
		fleta_MainPanel.setLayout(new BorderLayout(0, 0));
		
		
		fleta_IsAppear.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		fleta_IsAppear.setHorizontalAlignment(SwingConstants.CENTER);
		fleta_MainPanel.add(fleta_IsAppear, BorderLayout.CENTER);
		
		
		fleta_MainPanel.add(fleta_Main_BelowPanel, BorderLayout.SOUTH);
		
		
		fleta_Main_BelowPanel.add(fleta_AppearTime);
		fleta_AppearTime.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		fleta_AppearTime.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		leftEventPanel.add(priceTimerPanel);
		priceTimerPanel.setLayout(new BorderLayout(0, 0));
		
		priceTimerPanel.add(price_TitlePanel, BorderLayout.NORTH);
		price_TitlePanel.setBackground(Color.LIGHT_GRAY);
		price_TitlePanel.setLayout(new BorderLayout(0, 0));
		
		
		price_Image.setBackground(Color.WHITE);
		price_Image.setIcon(new ImageIcon(".\\source\\price.png"));
		price_TitlePanel.add(price_Image, BorderLayout.WEST);
		
		
		price_TitlePanel.add(price_Title, BorderLayout.CENTER);
		price_Title.setBackground(Color.WHITE);
		price_Title.setHorizontalAlignment(SwingConstants.CENTER);
		price_Title.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		
		
		priceTimerPanel.add(price_MainPanel, BorderLayout.CENTER);
		price_MainPanel.setLayout(new BorderLayout(0, 0));
		
		
		price_MainPanel.add(price_Main_BelowPanel, BorderLayout.SOUTH);
		
		
		price_RelocateTimeGap.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		price_Main_BelowPanel.add(price_RelocateTimeGap);
		
		
		price_MainPanel.add(price_StuffPanel, BorderLayout.CENTER);
		price_StuffPanel.setLayout(new GridLayout(0, 1, 0, 0));
		price_StuffPanel.add(price_Location);
		
		price_Location.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		price_Location.setHorizontalAlignment(SwingConstants.CENTER);
		
		price_NextLocation.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		price_NextLocation.setHorizontalAlignment(SwingConstants.CENTER);
		price_StuffPanel.add(price_NextLocation);
		
		
		rightEventPanel.setBackground(Color.GRAY);
		rightEventPanel.setBounds(271, 0, 263, 229);
		eventPanel.add(rightEventPanel);
		rightEventPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		
		rightEventPanel.add(advancedItemPanel);
		advancedItemPanel.setLayout(new BorderLayout(0, 0));
		
		adItem_TitlePanel.setBackground(Color.LIGHT_GRAY);
		advancedItemPanel.add(adItem_TitlePanel, BorderLayout.NORTH);
		adItem_TitlePanel.setLayout(new BorderLayout(0, 0));
		
		
		adItem_Title.setHorizontalAlignment(SwingConstants.CENTER);
		adItem_Title.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		adItem_TitlePanel.add(adItem_Title, BorderLayout.CENTER);
		
		
		aditem_Image.setIcon(new ImageIcon(".\\source\\제목 없음-3.png"));
		adItem_TitlePanel.add(aditem_Image, BorderLayout.WEST);
		
		
		advancedItemPanel.add(adItem_MainPanel, BorderLayout.CENTER);
		adItem_MainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		adItem_Main.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		
		
		adItem_Main.setHorizontalAlignment(SwingConstants.CENTER);
		adItem_MainPanel.add(adItem_Main);
		
		
		rightEventPanel.add(settingPanel);
		settingPanel.setLayout(new BorderLayout(0, 0));
		
		
		setting_TitlePanel.setBackground(Color.LIGHT_GRAY);
		settingPanel.add(setting_TitlePanel, BorderLayout.NORTH);
		setting_TitlePanel.setLayout(new BorderLayout(0, 0));
		
		setting_Image.setIcon(new ImageIcon(".\\source\\setting.png"));
		setting_TitlePanel.add(setting_Image, BorderLayout.WEST);
		
		
		setting_Title.setHorizontalAlignment(SwingConstants.CENTER);
		setting_Title.setFont(new Font("나눔고딕", Font.PLAIN, 18));
		setting_TitlePanel.add(setting_Title, BorderLayout.CENTER);
		
		
		settingPanel.add(setting_MainPanel, BorderLayout.CENTER);
		setting_MainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		fleta_IsAlarm.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		fleta_IsAlarm.addItemListener(this); //체크박스에 아이템 리스너 추가
		setting_MainPanel.add(fleta_IsAlarm);
		
		
		tabbedPane.addTab("\uC2A4\uD1B1\uC6CC\uCE58", null, 스톱워치, null);
		스톱워치.setLayout(null);
		
		
		title.setFont(new Font("나눔고딕", Font.PLAIN, 32));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(155, 0, 217, 135);
		스톱워치.add(title);
		timer.setFont(new Font("나눔고딕", Font.PLAIN, 24));
		
		timer.setHorizontalAlignment(SwingConstants.CENTER);
		timer.setBounds(155, 147, 210, 56);
		스톱워치.add(timer);
		Start.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		
		Start.setBounds(420, 330, 97, 23);
		스톱워치.add(Start);
		Stop.setFont(new Font("나눔고딕", Font.PLAIN, 12));

		Stop.setBounds(12, 330, 97, 23);
		스톱워치.add(Stop);
		
		//디자인 설정 끝
		
		 // 버튼의 액션 리스너 추가
        Start.addActionListener(this);
        Stop.addActionListener(this); 
		
        //thread 시작
		thread = new Thread(this);
		thread.start();
	}	
	//thread 의 run() 오버라이드
	public void run() {
		while(true) {
			calShow(); //alarm(erinH,erinM), fletaAppearCheck(erinH), priceLocation(erinTime,d), advancedItem(d) 이 있는 calshow 메소드 실행
			
			if(On == 1)
			{
				T++; //  스레드가 실행 된 이후 시간 저장
				timer.setText((T-savedT) + " 초 "); // 스레드가 실행 된 이후 시간 - 버튼이 눌린 시간을 하여 유저에게 보여주는 부분
			}
			try {
				Thread.sleep(1000); //1초간 기다린 후 실행
			} catch (Exception e) {
		   }
		}
	}
	
	public void calShow() {
		  Calendar calendar = Calendar.getInstance(); // 현재 시간을 가져오는 calendar 사용
		  
		  int d = calendar.get(Calendar.DAY_OF_WEEK); // price와 advanceditem에서 사용할 요일 변수 d
		 
		 //시계 구현 
		  int h = calendar.get(Calendar.HOUR); 
		  int AMPM = calendar.get(Calendar.AM_PM); 
		  if(AMPM == 1)
			  h = h + 12;
		  
		  String hour = String.format("%02d", h);
		  int mi = calendar.get(Calendar.MINUTE);
		  String minute = String.format("%02d", mi);
		  int s = calendar.get(Calendar.SECOND);
		  
		  //게임 시계 구현
		  int erinTime = (3600*h + 60*mi + s);
		  int erinH = (erinTime * 2 / 3 / 60) % 24;
		  String erinHour = String.format("%02d", erinH);
		  int erinM = (erinTime * 2 / 3 % 60);
		  String erinMinute = String.format("%02d", erinM);
		  
		  
		  realTime.setText("현실 시간 : " + hour + ":" + minute);
		  gameTime.setText("에린 시간 : " + erinHour + ":" + erinMinute);
		  
		  alarm(erinH,erinM); // 알람 기능
		  fletaAppearCheck(erinH); // 출현 여부 체크 기능
		  priceLocation(erinTime,d); //출현 위치 체크 기능
		  advancedItem(d); //요일별 지급되는 아이템 확인 기능
	}
	
	private void advancedItem(int day) { // day를 받아서 맞는 테이블로 adItem_Main을 세팅해줌
		switch(day)
		{
			case 1:{
				adItem_Main.setText("<html>염색 앰플(7일) 1개 <br/>금속 염색 앰플(7일) 1개</html>");
				break;}
			case 2:{
				adItem_Main.setText("<html>말풍선 스티커 상자(7일, 거래불가) 1개 <br/>이름 색상 변경 포션(7일, 거래불가) 1개</html>");
				break;}
			case 3:{
				adItem_Main.setText("<html>대용량 축복의 포션(거래불가) 1개 <br/>완전 회복의 포션 1개</html>");
				break;}
			case 4:{
				adItem_Main.setText("<html>원격 이용권 상자(7일) 1개</html>");
				break;}
			case 5:{
				adItem_Main.setText("<html>긴급탈출 아이스B 3개 <br/>나오의 영혼석 10개 <br/>생명력 500 포션 SE 5개 (7일, 거래불가)</html>");
				break;}
			case 6:{
				adItem_Main.setText("<html>거대한 외침의 뿔피리(7일) 3개</html></html>");
				break;}
			case 7:{
				adItem_Main.setText("<html>생명력의 엘릭서(7일) 1개 <br/>스태미나의 엘릭서(7일) 1개 <br/>마법 공격력 증가 포션(7일) 1개</html>");
				break;}
			default:{
				adItem_Main.setText("<html>Error has occured!</html>");
				break;}
		}
	}
	
	private void alarm(int eH, int eM) { //게임 시간으로 해당 시간 1분 전에 미리 알람을 줌.
		if((eH == 8 || eH == 14 || eH == 18) && eM == 59 && alarmOnOff == 1) {
			 
			//프로젝트가 위치한 폴더의 wav파일 재생
			File file = new File(".\\source\\party_msg.wav");
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch(Exception e) {
        }}
	}
	
	private void fletaAppearCheck(int eH) { // 출현 중인 시간에는 label을 출현 중으로 표시하고 아닐 땐 출현 중이 아님을 보여주는 메소드
		if((eH >= 9 && eH < 11) || (eH >= 15 && eH < 17) || (eH >= 19 && eH < 21))
			  fleta_IsAppear.setText("출현 중!");
		  else
			  fleta_IsAppear.setText("출현 중이 아님.");
	}
	
	private void priceLocation(int erinT, int day) { // 정해진 시간 테이블에 따라 label을 변경해주는 메소드 priceLocation 선언
		int checkT = erinT + (day -1) * 24 * 60 * 60; // 확인할 시간은 월요일 00시 00분을 기준으로 하였음. 매주 동일한 테이블로 나타남
		int checkTable = checkT % 30240; // 한번 테이블이 순환하는데 8시간 24분, 그러니까 30240 초가 필요함
		switch (checkTable / 2160) // 한번 데이터가 바뀌는 데에는 2160초가 필요함
		{
			case 0:{
				price_Location.setText("현재 위치 : 던바튼 동쪽 감자밭 천막");
				price_NextLocation.setText("다음 위치 : 드래곤 유적지 5시 집");
				break;
			}
			case 1:{
				price_Location.setText("현재 위치 : 드래곤 유적지 5시 집");
				price_NextLocation.setText("다음 위치 : 반호르 주점");
				break;
			}
			case 2:{
				price_Location.setText("현재 위치 : 반호르 주점");
				price_NextLocation.setText("다음 위치 : 센마이 평원 이멘마하 방향 집터");
				break;
			}
			case 3:{
				price_Location.setText("현재 위치 : 센마이 평원 이멘마하 방향 집터");
				price_NextLocation.setText("다음 위치 : 이멘마하 무기점 뒷 골목");
				break;
			}
			case 4:{
				price_Location.setText("현재 위치 : 이멘마하 무기점 뒷 골목");
				price_NextLocation.setText("다음 위치 : 케오섬");
				break;
			}
			case 5:{
				price_Location.setText("현재 위치 : 케오섬");
				price_NextLocation.setText("다음 위치 : 이멘마하 남쪽다리 중간 섬");
				break;
			}
			case 6:{
				price_Location.setText("현재 위치 : 이멘마하 남쪽다리 중간 섬");
				price_NextLocation.setText("다음 위치 : 센마이 평원 이멘마하 방향 집터");
				break;
			}
			case 7:{
				price_Location.setText("현재 위치 : 센마이 평원 이멘마하 방향 집터");
				price_NextLocation.setText("다음 위치 : 드래곤 유적지 5시 집");
				break;
			}
			case 8:{
				price_Location.setText("현재 위치 : 드래곤 유적지 5시 집");
				price_NextLocation.setText("다음 위치 : 바리던전 옆");
				break;
			}
			case 9:{
				price_Location.setText("현재 위치 : 바리던전 옆");
				price_NextLocation.setText("다음 위치 : 던바튼 학교 앞");
				break;
			}
			case 10:{
				price_Location.setText("현재 위치 : 던바튼 학교 앞");
				price_NextLocation.setText("다음 위치 : 두갈드아일 벌목캠프 움집");
				break;
			}
			case 11:{
				price_Location.setText("현재 위치 : 두갈드아일 벌목캠프 움집");
				price_NextLocation.setText("다음 위치 : 티르코네일 여관");
				break;
			}
			case 12:{
				price_Location.setText("현재 위치 : 티르코네일 여관");
				price_NextLocation.setText("다음 위치 : 두갈드아일 벌목캠프 움집");
				break;
			}
			case 13:{
				price_Location.setText("현재 위치 : 두갈드아일 벌목캠프 움집");
				price_NextLocation.setText("다음 위치 : 던바튼 동쪽 감자밭 천막");
				break;
			}
			default :
				break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) { // 체크박스가 체크되면 변수 alarmOnOff를 1로 설정하고 아니면 2로 설정함.
		if(e.getSource() == fleta_IsAlarm) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				alarmOnOff = 1;
			}
			else
				alarmOnOff = 2;
		}
	}
	public void actionPerformed(ActionEvent e) { // 버튼이 눌리면 작동하는 액션 이벤트
        if (e.getSource() == Start) { //start가 눌리면 버튼이 사라지고 stop이 표시되게 함
            Start.setVisible(false);
            Stop.setVisible(true);
            savedT = T; // T의 값을 savedT에 저장함
            On = 1;
        } else if (e.getSource() == Stop) { // stop이 눌리면 버튼이 사라지고 start가 표시되게 함
            Start.setVisible(true);
            Stop.setVisible(false);
            On = 2;
        }
    }
}
