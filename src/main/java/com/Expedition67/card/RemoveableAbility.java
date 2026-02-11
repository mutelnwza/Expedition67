package com.Expedition67.card;

import com.Expedition67.unit.Unit;

public abstract class RemoveableAbility implements CardAbility{
    public RemoveableAbility(int turn){
        this.turn=turn;
    }
    protected int turn;
    public void remove(Unit target){}
    public int getTurn(){return turn;}
}