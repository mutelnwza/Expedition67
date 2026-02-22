package com.Expedition67.core;

import com.Expedition67.unit.player.PlayerBrain;
import com.Expedition67.unit.Unit;

public class GameData {

    private int room;
    private Unit player;

    public GameData() {
        reset();
    }

    public void reset() {
        room = 0;
        player = null;
    }

    public int getRoom() {
        return room;
    }

    public void incrementRoom() {
        this.room++;
    }

    public void decrementRoom() {
        this.room--;
    }

    public Unit getPlayer() {
        return player;
    }

    public void setPlayer(Unit player) {
        this.player = player;
    }

    public PlayerBrain getPlayerBrain() {
        if (player != null) {
            return (PlayerBrain) player.getBrain();
        }
        return null;
    }
}
