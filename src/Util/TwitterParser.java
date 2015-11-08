package Util;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterParser {

	static public Twitter twitter = TwitterFactory.getSingleton();

	static public ArrayList<GeoLocation> search(String searchMsg, int limit, TextArea textbox) throws TwitterException {
		ArrayList<GeoLocation> locations = new ArrayList<GeoLocation>();
		Query query = new Query(searchMsg);
	    QueryResult result = twitter.search(query);
	    List<Status> results = result.getTweets();
	    for (int i = 0; i < limit && i < results.size(); i++ ) { // do not exceed specified limit or actual number of results
	    	Status text = results.get(i);
	    	String output = "@" + text.getUser() + " " + text.getText() + "\nLocation: " + text.getPlace() + " Language: " + text.getLang() + "\n\n"; // Displays username, text, location, language and leaves blank space for additional tweets				
	    	locations.add(text.getGeoLocation()); // retrieves and stores actual GeoLocation (latitude and longitude data when available
	    	textbox.append(output); // adds tweet information that matches search query to TextArea
	    }
	    return locations;
	}
}
