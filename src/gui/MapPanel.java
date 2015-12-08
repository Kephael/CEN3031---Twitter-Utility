package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import map.MapSystem;

public class MapPanel extends JPanel {
	private static final long serialVersionUID = 7401026435835149852L;
	private final JButton btnMainMenu = new JButton("Main Menu");

	/**
	 * Create the panel.
	 */
	public MapPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{79, 688, 116, 0, 0};
		gridBagLayout.rowHeights = new int[]{241, 23, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		MapSystem map = new MapSystem();
		GridBagConstraints gbc_map = new GridBagConstraints();
		gbc_map.fill = GridBagConstraints.BOTH;
		gbc_map.insets = new Insets(0, 0, 5, 5);
		gbc_map.gridx = 1;
		gbc_map.gridy = 0;
		add(map, gbc_map);
		map.init();
		GridBagConstraints gbc_btnMainMenu = new GridBagConstraints();
		gbc_btnMainMenu.insets = new Insets(0, 0, 5, 0);
		gbc_btnMainMenu.anchor = GridBagConstraints.SOUTHEAST;
		gbc_btnMainMenu.gridx = 3;
		gbc_btnMainMenu.gridy = 0;
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // restore main controls menu
				map.destroy();
				init.Init.jframe.getContentPane().removeAll(); // removes existing panel
				revalidate();
				repaint();
				init.Init.jframe.add(new MainControls()); // adds new main menu
				init.Init.jframe.revalidate();
				init.Init.jframe.repaint();
			}
		});
		add(btnMainMenu, gbc_btnMainMenu);
	}

}
