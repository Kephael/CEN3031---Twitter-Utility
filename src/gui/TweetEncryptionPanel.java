package gui;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;

public class TweetEncryptionPanel extends JPanel {
	private JTextField textField;
	private final ButtonGroup buttonGroupCipher = new ButtonGroup();
	/**
	 * Create the panel.
	 */
	public TweetEncryptionPanel() {
		setLayout(new GridLayout(6, 3, 0, 0));
		JLabel lblEncryptionKey = new JLabel("Vigen\u00E8re cipher key:");
		lblEncryptionKey.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEncryptionKey.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblEncryptionKey);
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("");
		add(label);
		
		JLabel label_1 = new JLabel("");
		add(label_1);
		
		JRadioButton rdbtnVig = new JRadioButton("Vigen\u00E8re cipher");
		buttonGroupCipher.add(rdbtnVig);
		add(rdbtnVig);
		
		JLabel label_2 = new JLabel("");
		add(label_2);
		
		JLabel label_3 = new JLabel("");
		add(label_3);
		
		JRadioButton rdbtnC = new JRadioButton("Caesar cipher");
		buttonGroupCipher.add(rdbtnC);
		add(rdbtnC);
		
		JLabel label_4 = new JLabel("");
		add(label_4);
		
		JLabel label_5 = new JLabel("");
		add(label_5);
		
		JRadioButton rdbtnRot = new JRadioButton("ROT13");
		buttonGroupCipher.add(rdbtnRot);
		add(rdbtnRot);
		
		JLabel label_6 = new JLabel("");
		add(label_6);
		
		JLabel label_7 = new JLabel("");
		add(label_7);
		
		JRadioButton rdbtnNoTweetEncryption = new JRadioButton("No Tweet encryption");
		buttonGroupCipher.add(rdbtnNoTweetEncryption);
		add(rdbtnNoTweetEncryption);
		
		JLabel label_8 = new JLabel("");
		add(label_8);
		
		JLabel label_9 = new JLabel("");
		add(label_9);
		
		JLabel label_10 = new JLabel("");
		add(label_10);
		
		JButton btnApply = new JButton("Apply");
		btnApply.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				removeAll();
				add(new MainControls()); // adds new panel
				revalidate();
				repaint();
			}
		});
		add(btnApply);
	}
}
