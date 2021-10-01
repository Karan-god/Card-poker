package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.Appframe;
import view.SummaryPanel;
import view.ToolBar;

public class MenuBarListner {
	private GameEngine gameEngine;
	private Appframe mainframe;
	private String name;
	public JComboBox comboBox;
	private String points;
	private ToolBar toolBar;
	private SummaryPanel summaryPanel = new SummaryPanel();
	List<Player> playerList = new ArrayList<>();

	public MenuBarListner(GameEngine gameEngine, Appframe mainframe, ToolBar toolBar) {
		this.gameEngine = gameEngine;
		this.mainframe = mainframe;
		this.toolBar = toolBar;
	}

	public void addplayerlistener(JMenuItem add) {
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// my name
				do {
					name = JOptionPane.showInputDialog(new JFrame(), "Enter Player Name", "Player Name ",
							JOptionPane.PLAIN_MESSAGE);
					if (name.isEmpty()) {
						JOptionPane.showMessageDialog(new JFrame(), "Please enter value cant read empty value",
								"Error Empty Name", JOptionPane.ERROR_MESSAGE);
					}
				} while (name.isEmpty());

				// my point
				do {
					points = JOptionPane.showInputDialog(new JFrame(), "Enter Points to Played", "Input Player Point",
							JOptionPane.PLAIN_MESSAGE);
					int point = Integer.parseInt(points);
					if (points.isEmpty()) {
						JOptionPane.showMessageDialog(new JFrame(), "Please enter points cant read",
								"Error Empty points", JOptionPane.ERROR_MESSAGE);

					} else {
						addPlayer(name, point);
						mainframe.display(getPlayers());
						JOptionPane.showMessageDialog(new JFrame(), "Player added", "Done Player Addition",
								JOptionPane.PLAIN_MESSAGE);

					}

				} while (points.isEmpty());

			}

			@SuppressWarnings("unchecked")
			public void addPlayer(String name, int point) {
				String playerId = "PLayer" + gameEngine.getAllPlayers().size() + "";
				Player player = new SimplePlayer(playerId, name, point);
				gameEngine.addPlayer(player);
				toolBar.comboBox.addItem(player.getPlayerName());

			}
		});
	}

	public void removeplayerlistener(JMenuItem remove) {
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = mainframe.toolbar.comboBox.getSelectedItem().toString();
				String id = getSelectedPlayerId(name);
				removePlayer(id);
				mainframe.display(gameEngine.getAllPlayers());

			}
		});
	}

	public void removePlayer(String id) {
		gameEngine.removePlayer(gameEngine.getPlayer(id));

	}

	public Player getPlayer(String id) {
		return gameEngine.getPlayer(id);
	}

	public void displayPlayers() {

		summaryPanel.displayPlayers(gameEngine.getAllPlayers());

	}

	public List<Player> getRecentPlayerList() {
		List<Player> players = new ArrayList<>();
		return playerList;
	}

	public Collection<Player> getPlayers() {
		return gameEngine.getAllPlayers();
	}

	public String getSelectedPlayerId(String playerName) {
		String id = null;
		for (Player player : gameEngine.getAllPlayers()) {
			if (player.getPlayerName().equals(playerName)) {
				id = player.getPlayerId();
			}
		}
		return id;

	}

}
