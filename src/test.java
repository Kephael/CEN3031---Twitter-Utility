import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class test {

	public static void main(String[] args) {
		ConfigurationBuilder cb = new ConfigurationBuilder();
		
		// Setting Tokens
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("b8clPMAjQs4ROlWtctk3jarYl")
			.setOAuthConsumerSecret("Ngf0aWbLNz2j0VOxKQWVI0t6XoqN2Xd23lGDKXGKF6dk7c6vka")
			.setOAuthAccessToken("3111937349-G8iXjHIvSFi73oLFyEL3SPeAZ0V4nzatBa0sWWe")
			.setOAuthAccessTokenSecret("gv8JtAyfovxftZ423mBkXcG1IgrbeqiXV7MhGartzKYE2");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter4j.TwitterAPIConfiguration tw = tf.getInstance();
		
		// Test
		// Post on twitter time line
		Status stat = tw.updateStatus("Welcome to Twiiter");
		System.out.println("Twitter updated");
	}

}
