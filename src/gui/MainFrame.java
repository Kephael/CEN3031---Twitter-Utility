package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -8026416994513756565L;
	public static JPanel currentPanel;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		currentPanel = new JPanel();
		currentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(currentPanel);
		setTitle("Twitter API Scraper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 900, 370);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnConfigurableOptions = new JMenu("Configurable Options");
		menuBar.add(mnConfigurableOptions);

		JMenuItem mntmApiSettings = new JMenuItem("API Settings");
		mntmApiSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getContentPane().removeAll(); // removes existing panel
				revalidate();
				getContentPane().add(new UserCredentials()); // adds new user credentials panel
				revalidate();
				repaint();
			}
		});
		mntmApiSettings.setHorizontalAlignment(SwingConstants.LEFT);
		mnConfigurableOptions.add(mntmApiSettings);

		JMenuItem mntmTweetEncryption = new JMenuItem("Tweet Encryption");
		mntmTweetEncryption.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getContentPane().removeAll(); // removes existing panel
				revalidate();
				getContentPane().add(new TweetEncryptionPanel()); // adds new tweet encryption panel
				revalidate();
				repaint();
			}
		});
		mnConfigurableOptions.add(mntmTweetEncryption);
		File settingsFile = new File (util.TwitterParser.KEY_FILE);
		if (settingsFile.exists() && !settingsFile.isDirectory()) {// loads API settings from file if file exists
			util.TwitterParser.loadAPISettingsFromFile(this); // 
		}
		getContentPane().add(new MainControls());
		revalidate();
		repaint();
	}
}
