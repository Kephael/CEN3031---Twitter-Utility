package gui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class UserCredentials extends JPanel {
	private JTextField consumerKey;
	private JPasswordField apiPassword;

	/**
	 * Create the panel.
	 */
	public UserCredentials() {
		setLayout(new GridLayout(5, 2, 0, 10));
		
		JLabel label_3 = new JLabel("");
		add(label_3);
		
		JLabel label_2 = new JLabel("");
		add(label_2);
		
		JLabel label = new JLabel("");
		add(label);
		
		JLabel label_1 = new JLabel("");
		add(label_1);
		
		JLabel lblConsumerKeyapi = new JLabel("<html>Consumer Key <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(API Key):</html>");
		lblConsumerKeyapi.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsumerKeyapi.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblConsumerKeyapi);
		
		consumerKey = new JTextField();
		consumerKey.setColumns(10);
		add(consumerKey);
		
		JLabel lblConsumerSecretapi = new JLabel("<html>Consumer Secret <br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(API Secret):</html>");
		lblConsumerSecretapi.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsumerSecretapi.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblConsumerSecretapi);
		
		apiPassword = new JPasswordField();
		apiPassword.setColumns(10);
		add(apiPassword);
		
		JLabel label_6 = new JLabel("");
		add(label_6);
		
		JButton applyBtn = new JButton("Apply");
		applyBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO set textfield settings to be used by Twitter object
				removeAll();
				add(new MainControls()); // adds new panel
				revalidate();
				repaint();
			}
		});
		add(applyBtn);

	}

}
