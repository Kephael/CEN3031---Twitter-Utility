package gui;

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
import javax.swing.JSlider;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenuItem;

public class MainPanel extends JFrame {
	private static final long serialVersionUID = -6962793798987727988L;
	private JTextField tweetField;
	private JTextField searchField;

	 // Creates the frame.
	public MainPanel() {
		setTitle("Twitter API Scraper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 600, 853, 370);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConfigurableOptions = new JMenu("Configurable Options");
		menuBar.add(mnConfigurableOptions);
		
		JMenuItem mntmApiSettings = new JMenuItem("API Settings");
		mntmApiSettings.setHorizontalAlignment(SwingConstants.LEFT);
		mnConfigurableOptions.add(mntmApiSettings);
		
		JMenuItem mntmTweetEncryption = new JMenuItem("Tweet Encryption");
		mntmTweetEncryption.setHorizontalAlignment(SwingConstants.LEFT);
		mnConfigurableOptions.add(mntmTweetEncryption);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		
		Component horizontalStrut = Box.createHorizontalStrut(50);
		getContentPane().add(horizontalStrut, "flowx,cell 1 1,alignx center");
		
		JLabel lblSearchResults = new JLabel("Search Results:");
		lblSearchResults.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblSearchResults, "cell 1 1,alignx center");
		
		JLabel lblTweetMessage = new JLabel("Tweet Message:");
		lblTweetMessage.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblTweetMessage, "cell 0 3,alignx left");
		
		tweetField = new JTextField();
		getContentPane().add(tweetField, "flowx,cell 1 3,alignx left");
		tweetField.setColumns(15);
		
		TextArea infoField = new TextArea();
		infoField.setEditable(false);
		getContentPane().add(infoField, "cell 1 2,alignx right");
		
		JLabel lblSearchLabel = new JLabel("Search:");
		lblSearchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblSearchLabel, "cell 0 4,alignx left");
		
		searchField = new JTextField();
		getContentPane().add(searchField, "flowx,cell 1 4,alignx left");
		searchField.setColumns(15);
		
		JLabel lblMaximumSearchResults = new JLabel("Maximum Search Results:");
		getContentPane().add(lblMaximumSearchResults, "cell 0 5");
		
		JSlider searchResultsNumberSlider = new JSlider();
		searchResultsNumberSlider.setValue(100);
		searchResultsNumberSlider.setMajorTickSpacing(50);
		searchResultsNumberSlider.setMaximum(250);
		searchResultsNumberSlider.setToolTipText("Maximum number of search results");
		searchResultsNumberSlider.setSnapToTicks(true);
		searchResultsNumberSlider.setPaintLabels(true);
		searchResultsNumberSlider.setPaintTicks(true);
		getContentPane().add(searchResultsNumberSlider, "flowx,cell 1 5");
		
		JButton btnSaveSearchResults = new JButton("Save Search Results");
		btnSaveSearchResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		Component horizontalStrutTweetMessage = Box.createHorizontalStrut(185);
		getContentPane().add(horizontalStrutTweetMessage, "cell 1 3,alignx left");
		getContentPane().add(btnSaveSearchResults, "cell 1 3,alignx right");
		
		JButton locationMap = new JButton("Map Tweet Locations");
		locationMap.setToolTipText("Maps a user's location for tweet which matched search query");
		locationMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getContentPane().add(locationMap, "cell 1 3,alignx right");
	}

}
