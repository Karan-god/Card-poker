package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.Appframe;
import view.GameEngineCallbackGUI;
import view.ToolBar;

public class ToolBarListner {
	
	private GameEngine gameEngine;
	private ToolBar toolbar;
	private Appframe appframe;
	private String betAmount;
	private int flag=0;
	private GameEngineCallbackGUI callback;

	public ToolBarListner(ToolBar toolbar,GameEngine gameEngine,Appframe appframe, GameEngineCallbackGUI callback ) {
		this.toolbar=toolbar;
		this.gameEngine=gameEngine;
		this.appframe=appframe;
		this.callback=callback;
		
	}

    public void bet(JButton cool) {
    	cool.addActionListener(new ActionListener() { 	
	public void actionPerformed(ActionEvent e) {
		String event =e.getActionCommand();
		if(event.equals("Bet"))
		{
			String playerName = toolbar.comboBox.getSelectedItem().toString();
			betAmount = JOptionPane.showInputDialog(new JFrame(), "Enter bet amount", "Place A Bet",
					JOptionPane.PLAIN_MESSAGE);
			int bet1 = Integer.parseInt(betAmount);
			if (betAmount == null) {

			} else {
				String id = getPlayerId();
				setBet(id, bet1);
				appframe.display(getPlayers());
			}
  	   
    	}
		else if(event.equals("Reset bet"))
		{
			String playerName = toolbar.comboBox.getSelectedItem().toString();
			try {
				String id = getPlayerId();
				 resetBet(id);
				appframe.display(getPlayers());
			} catch (Exception e2) {
				// TODO: handle exception

			}
			
		}
		else if(event.equals("Deal"))
		{
			try {
				String id=getPlayerId();
				int bet=Integer.parseInt(betAmount);
				dealCard(id,bet);
                flag++;
                if(flag==getPlayers().size())
                {
//                	houseCard();
                	appframe.display(getPlayers());
                	
                }
           
				
				
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
    	}
    	});
    }
   
    public void setBet(String playerId, int bet) {
		gameEngine.getPlayer(playerId).setBet(bet);

		}
    
    public void resetBet(String id) {
		gameEngine.getPlayer(id).resetBet();
	}
    
    	public String getPlayerId() {

 		String p = toolbar.comboBox.getSelectedItem().toString();
 		String playerID=getSelectedPlayerId(p);
 		return playerID;
    }
    	
    	public Collection<Player> getPlayers(){
    		return gameEngine.getAllPlayers();
    	}
    	
    	  public String getSelectedPlayerId(String playerName)
    	  {
    		  String id = null;
    		  for (Player player : getPlayers()) {
    			if(player.getPlayerName().equals(playerName))
    			  {
    				  id=player.getPlayerId();
    			  }
    			}
    		  return id;
    		  
    		 
    	  }
    	  
    	  public void dealCard(String id,int bet) {
    			Player player=gameEngine.getPlayer(id);
    			gameEngine.placeBet(player, bet);
    			new Thread(new Runnable() {
    				@Override
    				public void run() {
    					gameEngine.dealPlayer(player,100);
//    					appframe.revalidate();
//    					appframe.repaint();
    				}
    			}).start();
    			callback.startDeal();			
    		}
    	  
    	  public void houseCard() {
    			new Thread(new Runnable() {
    				@Override
    				public void run() {
    					// TODO Auto-generated method stub
    					
    					try {
    						gameEngine.dealHouse(100);
    						System.out.println("i am deal house");
    						appframe.revalidate();
        					appframe.repaint();
    					} catch (Exception e) {
    						// TODO: handle exception
    					}
    					
    				}
    			}).start();
    			
    			
    		}
    	  
    	  public int getflag()
    	  {
    		  return flag;
    		  
    	  }

   
}


	      
		
