package vmdv.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {
	private Viewer viewer;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public SearchPanel(Viewer viewer) {
		this.viewer = viewer;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JButton searchButton = new JButton("Search");
		
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String searchText = textField.getText().trim();
				String[] searchTexts = searchText.split(":");
				viewer.search(searchTexts);
			}
		});
		
		textField = new JTextField();
		add(textField);
		textField.setColumns(10);
		add(searchButton);

	}

}
