package com.Expedition67.core;

import com.Expedition67.card.Card;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.PlayerBrain;
import com.Expedition67.unit.Unit;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GameManager {

    private static GameManager instance;

    private final GameStateManager gameStateManager;
    private final GameTimer gameTimer;
    private final GameData gameData;

    private GameManager() {
        gameStateManager = new GameStateManager();
        gameTimer = new GameTimer();
        gameData = new GameData();
    }

    public static GameManager Instance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void newGame() {
        gameTimer.reset();
        gameTimer.start();
        gameData.reset();

        gameData.setPlayer(Warehouse.Instance().spawnPlayer(0, 460));
        CombatManager.initNew();
        CardInventory.Instance().emptyInventory();
        for (Card c : Warehouse.Instance().getCards())
            CardInventory.Instance().addCard(c, 1);
    }

    public void update() {
        gameStateManager.update();
        gameTimer.update();
    }

    public void render(Graphics g) {
        gameStateManager.render(g);
    }

    public void mouseClicked(MouseEvent e) {
        gameStateManager.mouseClicked(e);
    }

    public void mouseMoved(MouseEvent e) {
        gameStateManager.mouseMoved(e);
    }

    // --- Getters ---

    public GameStateManager getGameStateManager() {
        return gameStateManager;
    }

    public GameTimer getGameTimer() {
        return gameTimer;
    }

    public GameData getGameData() {
        return gameData;
    }

    public Unit getPlayer() {
        return gameData.getPlayer();
    }

    public PlayerBrain getPlayerBrain() {
        return gameData.getPlayerBrain();
    }

    public int getRoom() {
        return gameData.getRoom();
    }

    public String getTimeString() {
        return gameTimer.getTimeString();
    }
}
