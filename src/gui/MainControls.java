package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
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
		setBounds(0, 0, 845, 316);
		setLayout(new MigLayout("", "[120px][150px][75px][]", "[14px][185px][20px][]"));

		JLabel lblUsername = new JLabel("");
		add(lblUsername, "cell 0 0,alignx right");
		lblUsername.setText(util.TwitterParser.getUsername()); // displays username in upper left hand corner
		JLabel lblTweetLengthStatus = new JLabel("0 Characters"); // initial character length of empty tweet

		JLabel lblSearchResults = new JLabel("Search Results:");
		lblSearchResults.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSearchResults, "cell 2 0,alignx left,aligny top");

		JLabel lblTweetMessage = new JLabel("Tweet Message:");
		lblTweetMessage.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTweetMessage, "cell 0 1,alignx right,aligny bottom");
		
		JButton btnSaveSearchResults = new JButton("Save Search Results");
		JButton locationMap = new JButton("Map Tweet Locations");
		if (util.TwitterParser.location == null || util.TwitterParser.location.isEmpty()) { // no locations or location data is empty (previous had location data)
		locationMap.setEnabled(false);
		}

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
				if (e.getKeyCode() == KeyEvent.VK_ENTER) { // user presses enter to submit tweet
					if (tweetField.getText().length() <= 140 && !tweetField.getText().isEmpty()) { // valid input, tweet it
						try {
							Status result = util.TwitterParser.twitter.updateStatus(tweetField.getText());
							JOptionPane.showMessageDialog(tweetField, "Tweet successful: " + result.getText());
							tweetField.setText(""); // clear text area after successful tweet
							lblTweetLengthStatus.setText(tweetField.getText().length() + " Characters");
						} catch (TwitterException e1) {
							JOptionPane.showMessageDialog(tweetField, "An error has occured, please check your API credentials and Tweet Message and try again");
						}					
					}
					else {
						JOptionPane.showMessageDialog(tweetField, "Your Tweet message is invalid, 140 characters is the max length and an input is required.");
					}
				}
			}
		});
		add(tweetField, "flowx,cell 1 1,alignx left,aligny bottom");
		tweetField.setColumns(15);

		lblTweetLengthStatus.setVerticalAlignment(SwingConstants.BOTTOM);
		add(lblTweetLengthStatus, "cell 2 1,alignx left,aligny bottom");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "cell 3 1,grow");
		scrollPane.setPreferredSize(new Dimension(450, 151));
		JTextArea infoField = new JTextArea();
		infoField.setLineWrap(true);
		scrollPane.setViewportView(infoField);
		infoField.setEditable(false);
		if (!util.TwitterParser.lastSearchResults.isEmpty()) { // restore previous search results if they are available
			infoField.setText(util.TwitterParser.lastSearchResults);
		}
		JLabel lblSearchLabel = new JLabel("Search:");
		lblSearchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSearchLabel, "cell 0 2,alignx right,aligny center");

		searchResultsNumberSlider = new JSlider();
		searchResultsNumberSlider.setMinorTickSpacing(5);
		searchResultsNumberSlider.setValue(100);
		searchResultsNumberSlider.setMajorTickSpacing(20);
		searchResultsNumberSlider.setToolTipText("Maximum number of search results");
		searchResultsNumberSlider.setSnapToTicks(true);
		searchResultsNumberSlider.setPaintLabels(true);
		searchResultsNumberSlider.setPaintTicks(true);


		searchField = new JTextField();
		searchField.addKeyListener(new KeyAdapter() { // adapter to submit search queries 
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !searchField.getText().isEmpty()){ // user pressed enter in search box and search box is not empty
					infoField.setText(""); // clear TextArea prior to showing search results
					util.TwitterParser.search(searchField.getText(), searchResultsNumberSlider.getValue(), infoField);	
					if (util.TwitterParser.location != null && !util.TwitterParser.location.isEmpty()) { // location data exists
						locationMap.setEnabled(true);
					}
					else { // no location data, disable mapping button
						locationMap.setEnabled(false);
					}
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && searchField.getText().isEmpty()) // empty search query
				{
					JOptionPane.showMessageDialog(searchField, "Your search message is invalid, please enter an input");
				}
				if (!infoField.getText().isEmpty()) { // search returned result(s)
					btnSaveSearchResults.setEnabled(true);
				}
				else { // search returned nothing
					btnSaveSearchResults.setEnabled(false);
				}
			}
		});

		add(searchField, "cell 1 2,alignx left,aligny top");
		searchField.setColumns(15);

		btnSaveSearchResults.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				JFileChooser savePrompt = new JFileChooser();
				int response = savePrompt.showOpenDialog(btnSaveSearchResults);
				if (response == JFileChooser.APPROVE_OPTION) {
					File file = savePrompt.getSelectedFile();
					PrintWriter out = null;
					try {
						out = new PrintWriter(file);
						infoField.write(out); // write infoField's search contents to the file specified by the user
						JOptionPane.showMessageDialog(btnSaveSearchResults, "Search results written to " + file.getAbsolutePath());
						} catch (IOException e1) {
						JOptionPane.showMessageDialog(btnSaveSearchResults, "An I/O error has occured, check file permissions" );
						e1.printStackTrace();
					}
					finally {
						if (out != null) {
							out.close();
						}
					}
				}
			}
		});
		
		locationMap.setToolTipText("Maps a user's location for tweet which matched search query");
		locationMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MapPanel map = new MapPanel();
				removeAll(); // removes existing panel
				add(map); // adds new map
				revalidate();
				repaint();
			}
		});
		add(locationMap, "flowx,cell 3 2,alignx right,aligny top");
		add(btnSaveSearchResults, "cell 3 2,alignx right,aligny top");

		JLabel lblMaximumSearchResults = new JLabel();
		lblMaximumSearchResults.setText("<html>Maximum Search <br/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Results:</html>"); // HTML in Java for line wrap in JLabels
		lblMaximumSearchResults.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblMaximumSearchResults, "cell 0 3,alignx right,aligny center");

		add(searchResultsNumberSlider, "cell 1 3,alignx left,aligny top");
		if (!util.TwitterParser.isInitialized()) { // disable features if not logged into Twitter API 
			searchField.setEnabled(false);
			tweetField.setEnabled(false);
			locationMap.setEnabled(false);
		}
		if (infoField.getText().isEmpty()) { // disable save search results button if nothing in search results area
			btnSaveSearchResults.setEnabled(false);
		}
	}
}
