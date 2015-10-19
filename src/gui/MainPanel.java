package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;
import javax.swing.SwingConstants;

public class MainPanel extends JFrame {
	private JTextField tweetField;
	private JTextField searchField;


	/**
	 * Create the frame.
	 */
	public MainPanel() {
		setTitle("Twitter API Scraper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 600, 851, 360);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConfigurableOptions = new JMenu("Configurable Options");
		menuBar.add(mnConfigurableOptions);
		
		JButton apiDetails = new JButton("API Settings");
		apiDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnConfigurableOptions.add(apiDetails);
		
		JButton tweetEncryption = new JButton("Tweet Encryption");
		mnConfigurableOptions.add(tweetEncryption);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		
		JLabel lblSearchResults = new JLabel("Search Results:");
		lblSearchResults.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblSearchResults, "cell 1 1,alignx center");
		
		JLabel lblTweetMessage = new JLabel("Tweet Message:");
		lblTweetMessage.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblTweetMessage, "cell 0 3,alignx left");
		
		tweetField = new JTextField();
		getContentPane().add(tweetField, "cell 1 3,alignx left");
		tweetField.setColumns(20);
		
		TextArea infoField = new TextArea();
		infoField.setEditable(false);
		getContentPane().add(infoField, "cell 1 2,alignx right");
		
		JLabel lblSearchLabel = new JLabel("Search:");
		lblSearchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblSearchLabel, "cell 0 4,alignx left");
		
		searchField = new JTextField();
		getContentPane().add(searchField, "flowx,cell 1 4,alignx left");
		searchField.setColumns(20);
		
		JButton locationMap = new JButton("Map Tweet Location");
		locationMap.setToolTipText("Maps a user's location for tweet which matched search query");
		locationMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getContentPane().add(locationMap, "cell 1 5,alignx right,growy");
	}

}
