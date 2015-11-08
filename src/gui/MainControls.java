package gui;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSlider;
import javax.swing.JButton;

public class MainControls extends JPanel {

	private JTextField tweetField;
	private JTextField searchField;

	 // Creates the panel.
	public MainControls() {
		setBounds(800, 600, 853, 370);
		setLayout(new MigLayout("", "[120px][150px][75px][]", "[14px][185px][20px][]"));
		
		JLabel lblSearchResults = new JLabel("Search Results:");
		lblSearchResults.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblSearchResults, "cell 2 0,alignx left,aligny top");
		
		JLabel lblTweetMessage = new JLabel("Tweet Message:");
		lblTweetMessage.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblTweetMessage, "cell 0 1,alignx right,aligny bottom");
		
		tweetField = new JTextField();
		add(tweetField, "flowx,cell 1 1,alignx left,aligny bottom");
		tweetField.setColumns(15);
		
		TextArea infoField = new TextArea();
		infoField.setEditable(false);
		add(infoField, "cell 3 1,alignx left,growy");
		
		JLabel lblSearchLabel = new JLabel("Search:");
		lblSearchLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSearchLabel, "cell 0 2,alignx right,aligny center");
		
		searchField = new JTextField();
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
		
		JSlider searchResultsNumberSlider = new JSlider();
		searchResultsNumberSlider.setValue(100);
		searchResultsNumberSlider.setMajorTickSpacing(50);
		searchResultsNumberSlider.setMaximum(250);
		searchResultsNumberSlider.setToolTipText("Maximum number of search results");
		searchResultsNumberSlider.setSnapToTicks(true);
		searchResultsNumberSlider.setPaintLabels(true);
		searchResultsNumberSlider.setPaintTicks(true);
		add(searchResultsNumberSlider, "cell 1 3,alignx left,aligny top");
	}
}
