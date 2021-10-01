package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Collection;
import controller.MenuBarListner;
import controller.ToolBarListner;


import java.awt.event.*;
import java.util.*;
import java.util.List;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class ToolBar extends JToolBar {
	public JComboBox comboBox;
	private GameEngine gameEngine;
	String betAmount;
	private ToolBar toolbar;
	private ToolBarListner barListener;
	private Appframe mainframe;
	private StatusBar playerStatusBar;
	private DefaultComboBoxModel defaultComboBoxModel;
	;
	private GameEngineCallbackGUI callback;
	//private MenuBarListner listner =new MenuBarListner(gameEngine, mainframe);

	public ToolBar(GameEngine gameEngine, Appframe mainframe,StatusBar playerStatusBar, GameEngineCallbackGUI callback) {
		this.mainframe=mainframe;
		this.gameEngine= gameEngine;
		this.callback=callback;
		this.playerStatusBar=playerStatusBar;
		
	
		JPanel contentPane = new JPanel();
		barListener= new ToolBarListner(this, gameEngine, mainframe, callback);
		

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		// player addded box
		JLabel lblNewLabel = new JLabel("Players");
		this.add(lblNewLabel);
		
		DefaultComboBoxModel defaultComboBoxModel= new DefaultComboBoxModel();

		comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(250, 24));
		comboBox.addItemListener(playerStatusBar);
		defaultComboBoxModel.addElement("House");
		this.add(comboBox);
//	comboBox.addItem("Player 1");
//		 if else use for no player
//		Collection<Player> playeres = listner.getPlayers();
//		if (playeres.isEmpty()) {
//			comboBox.addItem("No player present here");
//		} else {
//			for (Player player : playeres) {
//				comboBox.addItem(player.getPlayerName());
//			}
//
////		System.out.println("HEllo");
////		int points=1;
////		controller.addPlayer(getName(), points);
//
//		}

		// Bet button
		JButton bet = new JButton("Bet");
		bet.setPreferredSize(new Dimension(70, 30));
		bet.setActionCommand("Bet");
		barListener.bet(bet);
		bet.setSelected(true);

		this.add(bet);

		// Reset button
		JButton resetbet = new JButton("Reset bet");
		resetbet.setPreferredSize(new Dimension(70, 30));
		resetbet.setActionCommand("Reset bet");
		barListener.bet(resetbet);
		resetbet.setSelected(true);
		this.add(resetbet);

//		Deal
     	JButton deal = new JButton("Deal");
		deal.setPreferredSize(new Dimension(100, 30));
		deal.setActionCommand("Deal");
		barListener.bet(deal);
		deal.setSelected(true);
        
		this.add(deal);

		contentPane.add(this);

	}

	public void displayPlayers(Collection<Player> players) {
		defaultComboBoxModel = (DefaultComboBoxModel) comboBox.getModel();
		defaultComboBoxModel.removeAllElements();
		for (Player player : players) {
			defaultComboBoxModel.addElement(player.getPlayerName());
		}

	}
	
	public DefaultComboBoxModel getModel() {
		return defaultComboBoxModel;
	}
	
	public ToolBarListner getListener() {
		return barListener;
	}

//	public String getPlayerId(String playerName) {
//
//		String playerID = controller.getSelectedPlayerId(playerName);
//		return playerID;
//	}
}
