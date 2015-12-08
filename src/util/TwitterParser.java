package util;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterParser {

	public static final String KEY_FILE = "API_Details.txt"; // file with API settings in encrypted format
	static public Twitter twitter = TwitterFactory.getSingleton();
	static private String username = "<NOT LOGGED IN>";
	static private boolean initialized = false;
	static public String lastSearchResults = ""; // used for restoring previous search results
	static public ArrayList<GeoLocation> location;
	
	/**
	 * @return the username
	 */
	public static String getUsername() {
		return username;
	}

	/**
	 * @param username the username associated with the Twitter account
	 */
	public static void setUsername(String username) {
		TwitterParser.username = username;
	}

	/**
	 * @return the initialized state
	 */
	public static boolean isInitialized() {
		return initialized;
	}

	/**
	 * @param initialized the initialized state to set
	 */
	public static void setInitialized(boolean initialized) {
		TwitterParser.initialized = initialized;
	}

	public static void search(String searchMsg, int limit, JTextArea infoField)  {
		ArrayList<GeoLocation> locations = new ArrayList<GeoLocation>();
		Query query = new Query(searchMsg);
		query.setCount(limit); // 100 is max supported by library, an input of 0 seems to be ignored
		try {
			QueryResult result = twitter.search(query);
			List<Status> results = result.getTweets();
			StringBuilder buildLast = new StringBuilder();
			for (int i = 0; i < results.size() && i < limit; i++ ) { // do not exceed specified limit or actual number of results
				Status text = results.get(i);
				String output = "@" + text.getUser().getScreenName() + " " + text.getText() + " Language: " + text.getLang() + "\n\n"; // Displays username, text, location, language and leaves blank space for additional tweets	
				if (text.getGeoLocation() != null) { // add to ArrayList if geolocation data is present
				locations.add(text.getGeoLocation()); // retrieves and stores actual GeoLocation (latitude and longitude data when available
				}
				infoField.append(output); // adds tweet information that matches search query to TextArea
				buildLast.append(output); // builds a record of the search results returned for restoring the JPanel holding the TextArea later.
			}
			util.TwitterParser.lastSearchResults = buildLast.toString();
			util.TwitterParser.location = locations; 
		}
		catch (TwitterException e) {
			JOptionPane.showMessageDialog(infoField, "An error occured searching Twitter, init status: " + util.TwitterParser.isInitialized());
		}
	}

	public static void loadAPISettingsFromFile(JFrame label) {
		TwitterFactory factory = new TwitterFactory(util.PassEncrypt.getDecryptedSettings());
		util.TwitterParser.twitter = factory.getInstance();
		try {
			util.TwitterParser.setUsername("Logged in as: " + util.TwitterParser.twitter.getScreenName()); // exception will be thrown on this line if invalid user login credentials supplied
			util.TwitterParser.setInitialized(true);
		}
		catch (Exception e) {
			util.TwitterParser.setInitialized(false);	
			util.TwitterParser.setUsername("<NOT LOGGED IN>");
			JOptionPane.showMessageDialog(label, "Error decrypting saved API settings, delete " + util.TwitterParser.KEY_FILE);		
		}
	}
}
