package Util;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterParser {

	static public Twitter twitter = TwitterFactory.getSingleton();
	static private String username = "<NOT LOGGED IN>";
	static private boolean initialized = false;
	
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

	static public ArrayList<GeoLocation> search(String searchMsg, int limit, TextArea textbox)  {
		ArrayList<GeoLocation> locations = new ArrayList<GeoLocation>();
		Query query = new Query(searchMsg);
		query.setCount(limit); // 100 is max supported by library
		try {
			QueryResult result = twitter.search(query);
			List<Status> results = result.getTweets();
			for (int i = 0; i < results.size(); i++ ) { // do not exceed specified limit or actual number of results
				Status text = results.get(i);
				String output = "@" + text.getUser().getScreenName() + " " + text.getText() + " Language: " + text.getLang() + "\n\n"; // Displays username, text, location, language and leaves blank space for additional tweets				
				locations.add(text.getGeoLocation()); // retrieves and stores actual GeoLocation (latitude and longitude data when available
				textbox.append(output); // adds tweet information that matches search query to TextArea
			}

		}
		catch (TwitterException e) {
			JOptionPane.showMessageDialog(textbox, "An error occured searching Twitter, init status: " + Util.TwitterParser.isInitialized());
		}
		return locations;
	}
}
