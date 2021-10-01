package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import Client.Client;
import controller.MenuBarListner;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class MenuBar extends JMenuBar {
	private JMenu menu;
	private JButton bet;
	private JButton deal;
	private JButton resetbet;
	private JPanel contentPane;
	private JComboBox comboBox;
	private ToolBar toolbar;
	private Client client;

	private CardPanel cardPanel;

	private JMenuItem miAddPlayer;
	private JMenuItem miRemovePlayer;
	private JMenuItem miback;

	private String name;
	private String points;
	private int point;
	private Collection<Player> player;
	private GameEngine gameEngine;
	private Player panel;
	public Appframe mainframe;
	public MenuBarListner listen;

	public MenuBar(GameEngine gameEngine, Appframe mainframe,ToolBar toolBar ) {
		this.gameEngine= gameEngine;
		this.mainframe= mainframe;
		this.toolbar=toolBar;
		listen= new MenuBarListner(gameEngine, mainframe,toolbar);

		menu = new JMenu("Menu");
		contentPane = new JPanel();

		miAddPlayer = new JMenuItem("Add Player");
		miRemovePlayer = new JMenuItem("Remove Player");
		// Add player action listener
		listen.addplayerlistener(miAddPlayer);

		// remove player action listener
		listen.removeplayerlistener(miRemovePlayer);
		miback = new JMenuItem("EXIT");
		miback.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		menu.add(miAddPlayer);
		menu.add(miRemovePlayer);
		menu.add(miback);
		add(menu);

	}

}
