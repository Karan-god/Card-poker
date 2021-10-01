package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {
	JList list = new JList();

	public SummaryPanel() {
		JPanel panelSummary = new JPanel();
		panelSummary.setPreferredSize(new Dimension(0, 0));
		this.add(panelSummary);

		setLayout(new BorderLayout(0, 0));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JScrollPane scrollPane = new JScrollPane();
		this.add(scrollPane);

		scrollPane.setViewportView(list);

	}

	public void displayPlayers(Collection<Player> players) {
		DefaultListModel defaultListModel = new DefaultListModel<>();

		defaultListModel.clear();
		for (Player player : players) {

			defaultListModel.addElement(player);
		}
		list.setModel(defaultListModel);
	}

}