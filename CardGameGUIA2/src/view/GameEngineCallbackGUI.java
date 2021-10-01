package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import controller.DealHouselistner;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback{
	
	
    private GameEngine gameEngine;
	private CardPanel cardP;
	private Appframe frame;

	public GameEngineCallbackGUI(GameEngine gameEngine, CardPanel cardP, Appframe frame ) 
	{
		
		
		this.gameEngine = gameEngine;
		this.cardP=cardP;
		this.frame=frame;
	}
	
    public void startDeal() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                cardP.clearCards();
                cardP.repaint();
            }
        });
    }

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) 
	{
       
            SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        cardP.drawNextCard(card);
                    }
		});
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) 
	{
            
            SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                       cardP.drawBustCard(card);
                    }
            });
		
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {  
		if (frame.getTool().getListener().getflag()==engine.getAllPlayers().size()) {
		      showMessage("All player dealt. Deal House");
		        frame.getTool().getModel().setSelectedItem("House");
		        new DealHouselistner(gameEngine, this).actionPerformed(
		        new ActionEvent("", 0, "Deal House"));	
		        frame.revalidate();
		        frame.repaint();
		}       

    }
	
    @Override
    public void nextHouseCard(PlayingCard card, GameEngine engine) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	cardP.drawNextCard(card);
            }
        });

    }

    @Override
    public void houseBustCard(PlayingCard card, GameEngine engine) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	cardP.drawBustCard(card);
            }
        });
    }

    @Override
    public void houseResult(int result, GameEngine engine) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	frame.revalidate();
            	frame.repaint();
            	
            }
        });
    }


	public void showMessage(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

	
	

}
