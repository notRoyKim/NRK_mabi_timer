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
public class MainPage extends JFrame implements Runnable, ItemListener, ActionListener // �ð��� �޾ƿ��� ���� ������� üũ�ڽ��� ��ư�� ����ϱ� ���� �׼� �����ʿ� ������ �����ʸ� �߰��ϴ� JFrame ��� MainPage Ŭ����
{ 	
	//�� �г�
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP); // �����ġ�� ���� �ð� ����� �����Ͽ� ����� �� �ֵ��� �� ������ �ȿ� �������� �г��� ����ϵ��� �ϴ� JTabbedPane
	JPanel ������ = new JPanel(); // ���� �ð� �г�
	JPanel �����ġ = new JPanel(); // �����ġ �г�
	
	//�ð� ���� ���
	JPanel clockPanel = new JPanel(); // �ð� �κ��� ����Ű�� �ð� �г�
	JPanel timePanel = new JPanel(); // ���� �ð�� ���� �ð踦 ǥ���� Panel
	JLabel realTime = new JLabel("\uD604\uC7AC \uC2DC\uAC04 :"); // �ð� ��ɿ��� ���� �ð��� ��Ÿ���� label
	JLabel gameTime = new JLabel("\uC5D0\uB9B0 \uC2DC\uAC04 :"); // �ð� ��ɿ��� ���� �ð��� ��Ÿ���� label
	JLabel fleta_IsAppear = new JLabel("\uCD9C\uD604 \uC5EC\uBD80"); // NPC ���� ���θ� ǥ�����ִ� label
	
	
	JPanel logoPanel = new JPanel(); // �»�ܿ� �ΰ� ���� �� �ֵ��� label�� ���� Panel
	JLabel mabiLogo = new JLabel(""); // �ΰ� ���� label
	
	//��� �г�
	JPanel eventPanel = new JPanel(); // ����� ����ִ� panel
	JPanel leftEventPanel = new JPanel(); // ���� ��� panel(fleta, price)
	JPanel rightEventPanel = new JPanel(); // ������ ��� panel(aditem,setting)
	
	//NPC �÷�Ÿ ���� �г�
	JPanel fletaTimerPanel = new JPanel(); // ��ü �г�
	JPanel fleta_TitlePanel = new JPanel(); // Ÿ��Ʋ �г�
	JLabel fleta_Title = new JLabel("\uD50C\uB808\uD0C0 \uD0C0\uC774\uBA38"); // Ÿ��Ʋ 
	JLabel fleta_Image = new JLabel(""); // ĳ���� �̹���
	
	JPanel fleta_MainPanel = new JPanel(); // ���� �г�
	JPanel fleta_Main_BelowPanel = new JPanel(); //�ϴ� �κ� �����ο� �г�
	JLabel fleta_AppearTime = new JLabel("(\uCD9C\uD604 \uC2DC\uAC04 : 09~11\uC2DC, 15~17\uC2DC, 19~21\uC2DC)"); // ���� �ð��� �ȳ�
	
	//NPC �����̽� ���� �г�
	JPanel priceTimerPanel = new JPanel(); //��ü �г�
	JPanel price_TitlePanel = new JPanel(); //Ÿ��Ʋ �г�
	JLabel price_Image = new JLabel(""); // ĳ���� �̹���
	JLabel price_Title = new JLabel("\uD504\uB77C\uC774\uC2A4 \uD0C0\uC774\uBA38"); // Ÿ��Ʋ
	
	JPanel price_MainPanel = new JPanel(); // ���� �г�
	
	JPanel price_Main_BelowPanel = new JPanel(); // ���� �ϴ� �κ� �����ο� �г�
	JLabel price_RelocateTimeGap = new JLabel("(\uCD9C\uD604 \uC2DC\uAC04 : \uD558\uB8E8 \uAC04\uACA9\uC73C\uB85C \uC774\uB3D9)"); // ���� ������ �ȳ� 
	
	JPanel price_StuffPanel = new JPanel();  // ���� �κ� �г�
	JLabel price_Location = new JLabel("\uCD9C\uD604 \uC704\uCE58"); // NPC ���� ��ġ�� ǥ�����ִ� label
	JLabel price_NextLocation = new JLabel("\uB2E4\uC74C \uC704\uCE58"); // NPC�� ���� ���� ��ġ�� ǥ�����ִ� label
	
	//���꽺�� ������ ���� �г�
	JPanel advancedItemPanel = new JPanel();// ��ü �г�
	
	JPanel adItem_TitlePanel = new JPanel();// Ÿ��Ʋ �г�
	JLabel adItem_Title = new JLabel("\uC624\uB298\uC758 \uC5B4\uB4DC\uBC34\uC2A4\uB4DC \uC544\uC774\uD15C"); //Ÿ��Ʋ
	JLabel aditem_Image = new JLabel(""); //�̹���
	
	JPanel adItem_MainPanel = new JPanel(); //���� �г�
	JLabel adItem_Main = new JLabel("\uC5B4\uB4DC\uBC34\uC2A4\uB4DC \uC544\uC774\uD15C"); //���Ϻ� ���޵Ǵ� �������� ǥ���ϴ� label
	
	//���� ���� �г�
	JPanel settingPanel = new JPanel(); // ��ü �г�
	
	JPanel setting_TitlePanel = new JPanel(); // Ÿ��Ʋ �г�
	JLabel setting_Image = new JLabel(""); //Ÿ��Ʋ �̹���
	JLabel setting_Title = new JLabel("\uC124\uC815"); //Ÿ��Ʋ
	
	JPanel setting_MainPanel = new JPanel(); //���� �г� 
	JCheckBox fleta_IsAlarm = new JCheckBox("\uD50C\uB808\uD0C0 \uCD9C\uD604 \uC54C\uB9BC on"); //NPC ���� �˶��� ������� �����ϴ� üũ�ڽ� 
	int alarmOnOff = 2; // �˶��� �����ų �� �����ϴ� ����
	
	//�����ġ ���� �г�
	JLabel title = new JLabel("\uC2A4\uD1B1\uC6CC\uCE58"); // �����ġ Ÿ��Ʋ
	JButton Start = new JButton("start"); // �����ġ ���� ��ư
	JButton Stop = new JButton("stop"); // �����ġ ���� ��ư
	JLabel timer = new JLabel("press start"); // �����ġ �ð��� ǥ���ϴ� label
	
	//�����ġ ���� ����
	int On; // �����ġ�� ���������� Ȯ���ϴ� ����
	int T = 0; // ���α׷��� ����� ������ �ð��� �����ϴ� ����
	int savedT = 0; // �����ġ�� ����� ������ T�� �����ϴ� ����
	
	Thread thread; //�ð� ������ ���� Thread
	
	public MainPage() {
		setSize(new Dimension(550,480)); // UI ����
		setResizable(false);
		
		// ������ ����
		
		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\source\\33334.png"));
		setFont(new Font("�������", Font.PLAIN, 12));
		setTitle("������ Ÿ�̸�");
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		tabbedPane.setFont(new Font("�������", Font.PLAIN, 12));
		tabbedPane.setBackground(Color.WHITE);
		getContentPane().add(tabbedPane);
		
		tabbedPane.addTab("\uB9C8\uBE44\uB178\uAE30 \uC2DC\uACC4", null, ������, null);
		������.setLayout(new BorderLayout(0, 0));
		
		������.add(timePanel, BorderLayout.NORTH);
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
		realTime.setFont(new Font("�������", Font.BOLD, 30));
		clockPanel.add(gameTime);
		
		gameTime.setFont(new Font("�������", Font.BOLD, 30));
		gameTime.setHorizontalAlignment(SwingConstants.LEFT);
		
		
		������.add(eventPanel, BorderLayout.CENTER);
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
		fleta_Title.setFont(new Font("�������", Font.PLAIN, 18));
		fleta_Title.setBackground(Color.WHITE);
		fleta_Title.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		fleta_Image.setIcon(new ImageIcon(".\\source\\fleta.png"));
		fleta_TitlePanel.add(fleta_Image, BorderLayout.WEST);
		
		
		fletaTimerPanel.add(fleta_MainPanel, BorderLayout.CENTER);
		fleta_MainPanel.setLayout(new BorderLayout(0, 0));
		
		
		fleta_IsAppear.setFont(new Font("�������", Font.PLAIN, 12));
		fleta_IsAppear.setHorizontalAlignment(SwingConstants.CENTER);
		fleta_MainPanel.add(fleta_IsAppear, BorderLayout.CENTER);
		
		
		fleta_MainPanel.add(fleta_Main_BelowPanel, BorderLayout.SOUTH);
		
		
		fleta_Main_BelowPanel.add(fleta_AppearTime);
		fleta_AppearTime.setFont(new Font("�������", Font.PLAIN, 12));
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
		price_Title.setFont(new Font("�������", Font.PLAIN, 18));
		
		
		priceTimerPanel.add(price_MainPanel, BorderLayout.CENTER);
		price_MainPanel.setLayout(new BorderLayout(0, 0));
		
		
		price_MainPanel.add(price_Main_BelowPanel, BorderLayout.SOUTH);
		
		
		price_RelocateTimeGap.setFont(new Font("�������", Font.PLAIN, 12));
		price_Main_BelowPanel.add(price_RelocateTimeGap);
		
		
		price_MainPanel.add(price_StuffPanel, BorderLayout.CENTER);
		price_StuffPanel.setLayout(new GridLayout(0, 1, 0, 0));
		price_StuffPanel.add(price_Location);
		
		price_Location.setFont(new Font("�������", Font.PLAIN, 12));
		price_Location.setHorizontalAlignment(SwingConstants.CENTER);
		
		price_NextLocation.setFont(new Font("�������", Font.PLAIN, 12));
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
		adItem_Title.setFont(new Font("�������", Font.PLAIN, 18));
		adItem_TitlePanel.add(adItem_Title, BorderLayout.CENTER);
		
		
		aditem_Image.setIcon(new ImageIcon(".\\source\\���� ����-3.png"));
		adItem_TitlePanel.add(aditem_Image, BorderLayout.WEST);
		
		
		advancedItemPanel.add(adItem_MainPanel, BorderLayout.CENTER);
		adItem_MainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		adItem_Main.setFont(new Font("�������", Font.PLAIN, 12));
		
		
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
		setting_Title.setFont(new Font("�������", Font.PLAIN, 18));
		setting_TitlePanel.add(setting_Title, BorderLayout.CENTER);
		
		
		settingPanel.add(setting_MainPanel, BorderLayout.CENTER);
		setting_MainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		fleta_IsAlarm.setFont(new Font("�������", Font.PLAIN, 12));
		fleta_IsAlarm.addItemListener(this); //üũ�ڽ��� ������ ������ �߰�
		setting_MainPanel.add(fleta_IsAlarm);
		
		
		tabbedPane.addTab("\uC2A4\uD1B1\uC6CC\uCE58", null, �����ġ, null);
		�����ġ.setLayout(null);
		
		
		title.setFont(new Font("�������", Font.PLAIN, 32));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(155, 0, 217, 135);
		�����ġ.add(title);
		timer.setFont(new Font("�������", Font.PLAIN, 24));
		
		timer.setHorizontalAlignment(SwingConstants.CENTER);
		timer.setBounds(155, 147, 210, 56);
		�����ġ.add(timer);
		Start.setFont(new Font("�������", Font.PLAIN, 12));
		
		Start.setBounds(420, 330, 97, 23);
		�����ġ.add(Start);
		Stop.setFont(new Font("�������", Font.PLAIN, 12));

		Stop.setBounds(12, 330, 97, 23);
		�����ġ.add(Stop);
		
		//������ ���� ��
		
		 // ��ư�� �׼� ������ �߰�
        Start.addActionListener(this);
        Stop.addActionListener(this); 
		
        //thread ����
		thread = new Thread(this);
		thread.start();
	}	
	//thread �� run() �������̵�
	public void run() {
		while(true) {
			calShow(); //alarm(erinH,erinM), fletaAppearCheck(erinH), priceLocation(erinTime,d), advancedItem(d) �� �ִ� calshow �޼ҵ� ����
			
			if(On == 1)
			{
				T++; //  �����尡 ���� �� ���� �ð� ����
				timer.setText((T-savedT) + " �� "); // �����尡 ���� �� ���� �ð� - ��ư�� ���� �ð��� �Ͽ� �������� �����ִ� �κ�
			}
			try {
				Thread.sleep(1000); //1�ʰ� ��ٸ� �� ����
			} catch (Exception e) {
		   }
		}
	}
	
	public void calShow() {
		  Calendar calendar = Calendar.getInstance(); // ���� �ð��� �������� calendar ���
		  
		  int d = calendar.get(Calendar.DAY_OF_WEEK); // price�� advanceditem���� ����� ���� ���� d
		 
		 //�ð� ���� 
		  int h = calendar.get(Calendar.HOUR); 
		  int AMPM = calendar.get(Calendar.AM_PM); 
		  if(AMPM == 1)
			  h = h + 12;
		  
		  String hour = String.format("%02d", h);
		  int mi = calendar.get(Calendar.MINUTE);
		  String minute = String.format("%02d", mi);
		  int s = calendar.get(Calendar.SECOND);
		  
		  //���� �ð� ����
		  int erinTime = (3600*h + 60*mi + s);
		  int erinH = (erinTime * 2 / 3 / 60) % 24;
		  String erinHour = String.format("%02d", erinH);
		  int erinM = (erinTime * 2 / 3 % 60);
		  String erinMinute = String.format("%02d", erinM);
		  
		  
		  realTime.setText("���� �ð� : " + hour + ":" + minute);
		  gameTime.setText("���� �ð� : " + erinHour + ":" + erinMinute);
		  
		  alarm(erinH,erinM); // �˶� ���
		  fletaAppearCheck(erinH); // ���� ���� üũ ���
		  priceLocation(erinTime,d); //���� ��ġ üũ ���
		  advancedItem(d); //���Ϻ� ���޵Ǵ� ������ Ȯ�� ���
	}
	
	private void advancedItem(int day) { // day�� �޾Ƽ� �´� ���̺�� adItem_Main�� ��������
		switch(day)
		{
			case 1:{
				adItem_Main.setText("<html>���� ����(7��) 1�� <br/>�ݼ� ���� ����(7��) 1��</html>");
				break;}
			case 2:{
				adItem_Main.setText("<html>��ǳ�� ��ƼĿ ����(7��, �ŷ��Ұ�) 1�� <br/>�̸� ���� ���� ����(7��, �ŷ��Ұ�) 1��</html>");
				break;}
			case 3:{
				adItem_Main.setText("<html>��뷮 �ູ�� ����(�ŷ��Ұ�) 1�� <br/>���� ȸ���� ���� 1��</html>");
				break;}
			case 4:{
				adItem_Main.setText("<html>���� �̿�� ����(7��) 1��</html>");
				break;}
			case 5:{
				adItem_Main.setText("<html>���Ż�� ���̽�B 3�� <br/>������ ��ȥ�� 10�� <br/>����� 500 ���� SE 5�� (7��, �ŷ��Ұ�)</html>");
				break;}
			case 6:{
				adItem_Main.setText("<html>�Ŵ��� ��ħ�� ���Ǹ�(7��) 3��</html></html>");
				break;}
			case 7:{
				adItem_Main.setText("<html>������� ������(7��) 1�� <br/>���¹̳��� ������(7��) 1�� <br/>���� ���ݷ� ���� ����(7��) 1��</html>");
				break;}
			default:{
				adItem_Main.setText("<html>Error has occured!</html>");
				break;}
		}
	}
	
	private void alarm(int eH, int eM) { //���� �ð����� �ش� �ð� 1�� ���� �̸� �˶��� ��.
		if((eH == 8 || eH == 14 || eH == 18) && eM == 59 && alarmOnOff == 1) {
			 
			//������Ʈ�� ��ġ�� ������ wav���� ���
			File file = new File(".\\source\\party_msg.wav");
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch(Exception e) {
        }}
	}
	
	private void fletaAppearCheck(int eH) { // ���� ���� �ð����� label�� ���� ������ ǥ���ϰ� �ƴ� �� ���� ���� �ƴ��� �����ִ� �޼ҵ�
		if((eH >= 9 && eH < 11) || (eH >= 15 && eH < 17) || (eH >= 19 && eH < 21))
			  fleta_IsAppear.setText("���� ��!");
		  else
			  fleta_IsAppear.setText("���� ���� �ƴ�.");
	}
	
	private void priceLocation(int erinT, int day) { // ������ �ð� ���̺� ���� label�� �������ִ� �޼ҵ� priceLocation ����
		int checkT = erinT + (day -1) * 24 * 60 * 60; // Ȯ���� �ð��� ������ 00�� 00���� �������� �Ͽ���. ���� ������ ���̺�� ��Ÿ��
		int checkTable = checkT % 30240; // �ѹ� ���̺��� ��ȯ�ϴµ� 8�ð� 24��, �׷��ϱ� 30240 �ʰ� �ʿ���
		switch (checkTable / 2160) // �ѹ� �����Ͱ� �ٲ�� ������ 2160�ʰ� �ʿ���
		{
			case 0:{
				price_Location.setText("���� ��ġ : ����ư ���� ���ڹ� õ��");
				price_NextLocation.setText("���� ��ġ : �巡�� ������ 5�� ��");
				break;
			}
			case 1:{
				price_Location.setText("���� ��ġ : �巡�� ������ 5�� ��");
				price_NextLocation.setText("���� ��ġ : ��ȣ�� ����");
				break;
			}
			case 2:{
				price_Location.setText("���� ��ġ : ��ȣ�� ����");
				price_NextLocation.setText("���� ��ġ : ������ ��� �̸ึ�� ���� ����");
				break;
			}
			case 3:{
				price_Location.setText("���� ��ġ : ������ ��� �̸ึ�� ���� ����");
				price_NextLocation.setText("���� ��ġ : �̸ึ�� ������ �� ���");
				break;
			}
			case 4:{
				price_Location.setText("���� ��ġ : �̸ึ�� ������ �� ���");
				price_NextLocation.setText("���� ��ġ : �ɿ���");
				break;
			}
			case 5:{
				price_Location.setText("���� ��ġ : �ɿ���");
				price_NextLocation.setText("���� ��ġ : �̸ึ�� ���ʴٸ� �߰� ��");
				break;
			}
			case 6:{
				price_Location.setText("���� ��ġ : �̸ึ�� ���ʴٸ� �߰� ��");
				price_NextLocation.setText("���� ��ġ : ������ ��� �̸ึ�� ���� ����");
				break;
			}
			case 7:{
				price_Location.setText("���� ��ġ : ������ ��� �̸ึ�� ���� ����");
				price_NextLocation.setText("���� ��ġ : �巡�� ������ 5�� ��");
				break;
			}
			case 8:{
				price_Location.setText("���� ��ġ : �巡�� ������ 5�� ��");
				price_NextLocation.setText("���� ��ġ : �ٸ����� ��");
				break;
			}
			case 9:{
				price_Location.setText("���� ��ġ : �ٸ����� ��");
				price_NextLocation.setText("���� ��ġ : ����ư �б� ��");
				break;
			}
			case 10:{
				price_Location.setText("���� ��ġ : ����ư �б� ��");
				price_NextLocation.setText("���� ��ġ : �ΰ������ ����ķ�� ����");
				break;
			}
			case 11:{
				price_Location.setText("���� ��ġ : �ΰ������ ����ķ�� ����");
				price_NextLocation.setText("���� ��ġ : Ƽ���ڳ��� ����");
				break;
			}
			case 12:{
				price_Location.setText("���� ��ġ : Ƽ���ڳ��� ����");
				price_NextLocation.setText("���� ��ġ : �ΰ������ ����ķ�� ����");
				break;
			}
			case 13:{
				price_Location.setText("���� ��ġ : �ΰ������ ����ķ�� ����");
				price_NextLocation.setText("���� ��ġ : ����ư ���� ���ڹ� õ��");
				break;
			}
			default :
				break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) { // üũ�ڽ��� üũ�Ǹ� ���� alarmOnOff�� 1�� �����ϰ� �ƴϸ� 2�� ������.
		if(e.getSource() == fleta_IsAlarm) {
			if(e.getStateChange() == ItemEvent.SELECTED) {
				alarmOnOff = 1;
			}
			else
				alarmOnOff = 2;
		}
	}
	public void actionPerformed(ActionEvent e) { // ��ư�� ������ �۵��ϴ� �׼� �̺�Ʈ
        if (e.getSource() == Start) { //start�� ������ ��ư�� ������� stop�� ǥ�õǰ� ��
            Start.setVisible(false);
            Stop.setVisible(true);
            savedT = T; // T�� ���� savedT�� ������
            On = 1;
        } else if (e.getSource() == Stop) { // stop�� ������ ��ư�� ������� start�� ǥ�õǰ� ��
            Start.setVisible(true);
            Stop.setVisible(false);
            On = 2;
        }
    }
}
