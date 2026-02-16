package com.Expedition67.unit.Enemy;

import com.Expedition67.card.*;
import com.Expedition67.unit.UnitBrain;

public abstract class EnemyBrain extends UnitBrain{
    protected CardAbility nextAction = null;

    @Override
    protected void die() {
        System.out.println("im ded");
        //tell combatmanager that this unit is dead
    }

// in case there are actions to perform if player use a card
    public void onPlayerUseCard(Card c){
        calculateNextMove(c);
    }

// pre calculate next action, call when starts player turn
    public abstract void calculateNextMove();

// in case that the action depends on players card
    public void calculateNextMove(Card c){

    }

// combat manager call this to get the next action then perform
    public CardAbility getNextAction(){
        return nextAction;
    }
}
