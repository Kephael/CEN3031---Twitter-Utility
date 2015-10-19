package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.TextArea;
import javax.swing.SwingConstants;

public class MainPanel extends JFrame {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPanel frame = new MainPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainPanel() {
		setTitle("Twitter API Scraper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 600, 851, 360);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnConfigurableOptions = new JMenu("Configurable Options");
		menuBar.add(mnConfigurableOptions);
		
		JButton apiDetails = new JButton("API Settings");
		apiDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		mnConfigurableOptions.add(apiDetails);
		
		JButton btnNewButton_1 = new JButton("Tweet Encryption");
		mnConfigurableOptions.add(btnNewButton_1);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		
		JLabel lblTweetMessage = new JLabel("Tweet Message:");
		lblTweetMessage.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblTweetMessage, "cell 0 3,alignx left");
		
		textField = new JTextField();
		getContentPane().add(textField, "cell 1 3,alignx left");
		textField.setColumns(20);
		
		TextArea textArea_1 = new TextArea();
		getContentPane().add(textArea_1, "cell 1 2,alignx right");
		
		JLabel lblSearchLabel = new JLabel("Search:");
		lblSearchLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblSearchLabel, "cell 0 4,alignx left");
		
		textField_1 = new JTextField();
		getContentPane().add(textField_1, "cell 1 4,alignx left");
		textField_1.setColumns(20);
	}

}
