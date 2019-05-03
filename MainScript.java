package mabiTimer;
import javax.swing.JFrame;
import mabiTimer.MainPage;

public class MainScript {
	public static void main(String args[])
	{
		MainPage p = new MainPage();
		
		p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setVisible(true);
	}
}