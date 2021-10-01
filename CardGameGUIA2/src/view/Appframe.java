package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/***
 * 
 * @author karan
 * my main frame
 */

public class Appframe extends JFrame {
	private MenuBar menuBar;
	private SummaryPanel panelSummary;
	public ToolBar toolbar;
	private CardPanel cardPanel;
	private GameEngineCallbackGUI callback;
	public Appframe() {

		new JPanel();

		cardPanel = new CardPanel();

		final GameEngine gameEngine = new GameEngineImpl();
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		callback = new GameEngineCallbackGUI(gameEngine, cardPanel, this);
		gameEngine.addGameEngineCallback(callback);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(110, 70, 600, 550);
		this.setMinimumSize(new Dimension(1000, 500));
		this.setTitle("Card Game");
		this.setLayout(new BorderLayout());
		// status bar
		StatusBar st = new StatusBar();
		this.add(st, BorderLayout.SOUTH);
		// toolbar
		JToolBar t = new JToolBar();
		toolbar = new ToolBar(gameEngine, this, st, callback);
		toolbar.setPreferredSize(new Dimension(50, 30));
		this.add(toolbar, BorderLayout.NORTH);
		this.add(t);
		// menu
		menuBar = new MenuBar(gameEngine, this, toolbar);
		this.setJMenuBar(menuBar);

		JPanel center = new JPanel();
		this.add(center, BorderLayout.CENTER);

		// card panel
		JPanel cp = new JPanel();
		String title = "Card Panel";
		Border border = BorderFactory.createTitledBorder(title);
		cp.setBorder(border);

		cardPanel.setPreferredSize(new Dimension(850, 270));
		cp.add(cardPanel);
		center.add(cp, BorderLayout.NORTH);

		// summary panel
		JPanel sm = new JPanel();
		String title2 = "Summary Panel";
		Border border2 = BorderFactory.createTitledBorder(title2);
		sm.setBorder(border2);
		panelSummary = new SummaryPanel();
		panelSummary.setPreferredSize(new Dimension(650, 110));
		sm.add(panelSummary);
		center.add(sm, BorderLayout.SOUTH);

		this.setVisible(true);

	}

	public void display(Collection<Player> players) {
		panelSummary.displayPlayers(players);
		toolbar.displayPlayers(players);
	}

	public ToolBar getTool() {
		return toolbar;
	}

}
