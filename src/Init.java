import javax.swing.JFrame;

import gui.MainFrame;
import twitter4j.TwitterException;

public class Init {
	
	public static void main(String[] args) throws TwitterException {
		MainFrame panel = new MainFrame();
		panel.setVisible(true);
		panel.setResizable(false);
		panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
