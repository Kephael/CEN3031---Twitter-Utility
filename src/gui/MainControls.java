package gui;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import twitter4j.Status;
import twitter4j.TwitterException;

public class MainControls extends JPanel {
	private static final long serialVersionUID = 6767530104508892616L;
	private JTextField tweetField;
	private JTextField searchField;
	private JSlider searchResultsNumberSlider;


	// Creates the panel.
	public MainControls() {
		setBounds(0, 0, 853, 370);
		setLayout(new MigLayout("", "[120px][150px][75px][]", "[14px][185px][20px][]"));

		JLabel lblUsername = new JLabel("");
		add(lblUsername, "cell 0 0");
		lblUsername.setText(Util.TwitterParser.getUsername()); // displays username in upper left hand corner
		JLabel lblTweetLengthStatus = new JLabel("0 Characters"); // initial character length of empty tweet

		JLabel lblSearchResults = new JLabel("Search Results:");
		lblSearchResults.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSearchResults, "cell 2 0,alignx left,aligny top");

		JLabel lblTweetMessage = new JLabel("Tweet Message:");
		lblTweetMessage.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTweetMessage, "cell 0 1,alignx right,aligny bottom");

		tweetField = new JTextField();
		tweetField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lblTweetLengthStatus.setText(tweetField.getText().length() + " Characters"); // set length of tweet length in status area
				if (tweetField.getText().length() > 140 && tweetField.getText().length() <= 999) {
					lblTweetLengthStatus.setForeground(Color.red); // set color to red when over limit of 140 characters
				}
				else if (tweetField.getText().length() > 999) {
					lblTweetLengthStatus.setText("Out of Range"); // set length of tweet length in status area
					lblTweetLengthStatus.setForeground(Color.red); // set color to red when over limit of 140 characters
				}
				else {
					lblTweetLengthStatus.setForeground(Color.black); // set color to black when under limit of 140 characters
				}
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (tweetField.getText().length() <= 140) { // valid input, tweet it
						try {
							Status result = Util.TwitterParser.twitter.updateStatus(tweetField.getText());
							JOptionPane.showMessageDialog(tweetField, "Tweet successful: " + result.getText());
						} catch (TwitterException e1) {
							JOptionPane.showMessageDialog(tweetField, "An error has occured, please check your API credentials and try again");
							e1.printStackTrace();
						}					
					}
					else {
						JOptionPane.showMessageDialog(tweetField, "Your Tweet message is too long, 140 characters is the max length.");
					}
				}
			}
		});
		add(tweetField, "flowx,cell 1 1,alignx left,aligny bottom");
		tweetField.setColumns(15);

		lblTweetLengthStatus.setVerticalAlignment(SwingConstants.BOTTOM);
		add(lblTweetLengthStatus, "cell 2 1,alignx left,aligny bottom");

		TextArea infoField = new TextArea();
		infoField.setEditable(false);
		add(infoField, "cell 3 1,alignx left,growy");

		JLabel lblSearchLabel = new JLabel("Search:");
		lblSearchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSearchLabel, "cell 0 2,alignx right,aligny center");

		JSlider searchResultsNumberSlider = new JSlider();
		searchResultsNumberSlider.setMinorTickSpacing(5);
		searchResultsNumberSlider.setValue(100);
		searchResultsNumberSlider.setMajorTickSpacing(20);
		searchResultsNumberSlider.setToolTipText("Maximum number of search results");
		searchResultsNumberSlider.setSnapToTicks(true);
		searchResultsNumberSlider.setPaintLabels(true);
		searchResultsNumberSlider.setPaintTicks(true);


		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
					infoField.setText(""); // clear TextArea prior to showing search results
					Util.TwitterParser.search(searchField.getText(), searchResultsNumberSlider.getValue(), infoField);		
				}
			}
		});

		add(searchField, "cell 1 2,alignx left,aligny top");
		searchField.setColumns(15);

		JButton btnSaveSearchResults = new JButton("Save Search Results");
		btnSaveSearchResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JButton locationMap = new JButton("Map Tweet Locations");
		locationMap.setToolTipText("Maps a user's location for tweet which matched search query");
		locationMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		add(locationMap, "flowx,cell 3 2,alignx right,aligny top");
		add(btnSaveSearchResults, "cell 3 2,alignx right,aligny top");

		JLabel lblMaximumSearchResults = new JLabel();
		lblMaximumSearchResults.setText("<html>Maximum Search <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Results:</html>"); // HTML in Java for line wrap in JLabels
		lblMaximumSearchResults.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMaximumSearchResults, "cell 0 3,alignx right,aligny center");

		add(searchResultsNumberSlider, "cell 1 3,alignx left,aligny top");
		if (!Util.TwitterParser.isInitialized()) { // disable features if not logged into Twitter API 
			searchField.setEnabled(false);
			tweetField.setEnabled(false);
			locationMap.setEnabled(false);
		}
	}
}
