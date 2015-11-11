package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import java.awt.Color;

public class UserCredentials extends JPanel {
	private JTextField OAuthKey;
	private JPasswordField consumerSecretPassword;
	private JTextField accessToken;
	private JPasswordField accessTokenSecret;

	/**
	 * Create the panel.
	 */
	public UserCredentials() {
		setLayout(new GridLayout(7, 2, 0, 10));

		JLabel label_3 = new JLabel("");
		add(label_3);

		JLabel label_2 = new JLabel("");
		add(label_2);

		JLabel label = new JLabel("");
		add(label);

		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		add(lblError);

		JLabel lblConsumerKeyApi = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Consumer Key <br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(API Key):</html>");
		lblConsumerKeyApi.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsumerKeyApi.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblConsumerKeyApi);

		OAuthKey = new JTextField();
		OAuthKey.setColumns(10);
		add(OAuthKey);

		JLabel lblConsumerSecretApi = new JLabel("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Consumer Secret <br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(API Secret):</html>");
		lblConsumerSecretApi.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsumerSecretApi.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblConsumerSecretApi);

		consumerSecretPassword = new JPasswordField();
		consumerSecretPassword.setColumns(10);
		add(consumerSecretPassword);

		JLabel lblAccessToken = new JLabel("Access Token:");
		lblAccessToken.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccessToken.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblAccessToken);

		accessToken = new JTextField();
		accessToken.setColumns(10);
		add(accessToken);

		JLabel lblAccessTokenSecret = new JLabel("Access Token Secret:");
		lblAccessTokenSecret.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAccessTokenSecret.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblAccessTokenSecret);

		accessTokenSecret = new JPasswordField();
		accessTokenSecret.setColumns(10);
		add(accessTokenSecret);
		
				JButton applyBtn = new JButton("Apply");
				applyBtn.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						if (consumerSecretPassword.getPassword().length > 0 && OAuthKey.getText().length() > 0 && accessTokenSecret.getPassword().length > 0 && accessToken.getText().length() > 0) { // checks there are inputs
							String password = "";
							for (char x : consumerSecretPassword.getPassword()){
								password += x; // goes through array concatenating characters to String
							}
							String applicationPassword = "";
							for (char x : accessTokenSecret.getPassword()){
								applicationPassword += x;
							}
							ConfigurationBuilder builder = new ConfigurationBuilder();
							builder.setOAuthConsumerKey(OAuthKey.getText());
							builder.setOAuthConsumerSecret(password);
							builder.setOAuthAccessToken(accessToken.getText());
							builder.setOAuthAccessTokenSecret(applicationPassword);
							Configuration configuration = builder.build();
							TwitterFactory factory = new TwitterFactory(configuration);
						    Util.TwitterParser.twitter = factory.getInstance();
						    try {
								Util.TwitterParser.setUsername("Logged in as: " + Util.TwitterParser.twitter.getScreenName()); // exception will be thrown on this line if invalid user login credentials supplied
								Util.TwitterParser.setInitialized(true);
								removeAll();
								add(new MainControls()); // adds new panel
								revalidate();
								repaint();
								} catch (Exception e1) {
									lblError.setText("Invalid Login Credentials");
									Util.TwitterParser.setInitialized(false);	
									Util.TwitterParser.setUsername("<NOT LOGGED IN>");
							}
						}
						else { // no entry for one of the text boxes
							lblError.setText("Invalid Input");
						}
					}
				});
				add(applyBtn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) { // go back to main screen, don't change anything
				removeAll();
				add(new MainControls()); // adds new panel
				revalidate();
				repaint();
			}
		});
		add(btnCancel);
	}

}
