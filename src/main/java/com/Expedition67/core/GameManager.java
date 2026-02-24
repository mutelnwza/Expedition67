package com.Expedition67.core;

import com.Expedition67.card.CardName;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.storage.CardInventory;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.PlayerBrain;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The central hub of the game, managing all major components like game state, timer, and player data.
 */
public class GameManager {

    private static GameManager instance;

    private final GameStateManager gameStateManager;
    private final GameTimer gameTimer;
    private final GameData gameData;

    private GameManager() {
        this.gameStateManager = new GameStateManager();
        this.gameTimer = new GameTimer();
        this.gameData = new GameData();
        SoundManager.Instance().playBGM();
    }

    /**
     * Gets the single instance of the GameManager.
     *
     * @return The single instance of GameManager.
     */
    public static GameManager Instance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Initializes all necessary data for a new game.
     */
    public void newGame() {
        gameTimer.reset();
        gameTimer.start();
        gameData.reset();
        CombatManager.initNew();
        CardInventory.Instance().emptyInventory();

        gameData.setPlayer(Warehouse.Instance().spawnPlayer(0, 460));

        CardInventory.Instance().addCard(Warehouse.Instance().spawnCard(CardName.SOUL_FLICKER), 1);
        CardInventory.Instance().addCard(Warehouse.Instance().spawnCard(CardName.REMNANT_HIT), 3);
        CardInventory.Instance().addCard(Warehouse.Instance().spawnCard(CardName.ECHOING_STRIKE), 1);
        CardInventory.Instance().addCard(Warehouse.Instance().spawnCard(CardName.SPECTRAL_VEIL), 3);
        CardInventory.Instance().addCard(Warehouse.Instance().spawnCard(CardName.SOVEREIGNS_OVERDRIVE), 1);
        CardInventory.Instance().addCard(Warehouse.Instance().spawnCard(CardName.ETHEREAL_RESTORATION), 1);
        CardInventory.Instance().addCard(Warehouse.Instance().spawnCard(CardName.CELESTIAL_SINGULARITY), 1);
    }

    /**
     * The main update method, called every frame to update game logic.
     */
    public void update() {
        gameStateManager.update();
        gameTimer.update();
    }

    /**
     * The main render method, called every frame to draw the game.
     *
     * @param g The Graphics object to draw with.
     */
    public void render(Graphics g) {
        gameStateManager.render(g);
    }

    /**
     * Forwards mouse click events to the current game state.
     *
     * @param e The MouseEvent from the click.
     */
    public void mouseClicked(MouseEvent e) {
        gameStateManager.mouseClicked(e);
    }

    /**
     * Forwards mouse movement events to the current game state.
     *
     * @param e The MouseEvent from the movement.
     */
    public void mouseMoved(MouseEvent e) {
        gameStateManager.mouseMoved(e);
    }

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
