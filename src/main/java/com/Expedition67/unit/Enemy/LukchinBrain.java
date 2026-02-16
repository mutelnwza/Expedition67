package com.Expedition67.unit.Enemy;

import com.Expedition67.card.Card;
import com.Expedition67.unit.UnitBrain;

public class LukchinBrain extends EnemyBrain{

    @Override
    public void calculateNextMove() {
        
    }

    @Override
    public void calculateNextMove(Card c){
        nextAction = c.getAbility();
    }

    @Override
    public UnitBrain copy() {
        return new LukchinBrain();
    }
    
}
