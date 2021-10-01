package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackGUI;
//for house dealing
public class DealHouselistner implements ActionListener {

    private GameEngine gameEngine;
    private GameEngineCallbackGUI gameEngineCallbackGUI;

    public DealHouselistner(GameEngine gameEngine,
                                GameEngineCallbackGUI gameEngineCallbackGUI) {
        this.gameEngine = gameEngine;
        this.gameEngineCallbackGUI = gameEngineCallbackGUI;
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        new Thread() {
            @Override
            public void run() {
                gameEngine.dealHouse(100);
            }
        }.start();

        gameEngineCallbackGUI.startDeal();
    }

}
