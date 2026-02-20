package com.Expedition67.card;

import com.Expedition67.unit.Unit;

public abstract class RemoveableAbility extends CardAbility{
    public RemoveableAbility(int turn, int value, CardType cardType){
        super(value, cardType);
        this.turn=turn;
    }

    public RemoveableAbility(int turn,CardType cardType){
        super(cardType);
        this.turn=turn;
    }
    
    private int turn;
    public void remove(Unit target){}
    public int getTurn(){return turn;}
}