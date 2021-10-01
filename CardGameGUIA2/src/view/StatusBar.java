package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.Player;

@SuppressWarnings("serial")
public class StatusBar extends JPanel implements ItemListener {
	private final JLabel statusLabel = new JLabel("status", JLabel.LEFT);

	public StatusBar() {
		setLayout(new GridLayout(1, 1));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		statusLabel.setText("No Player Selected");
		add(statusLabel);
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		String status = (String) event.getItem();
		statusLabel.setText(String.format("%s Selected", status));
	}

}