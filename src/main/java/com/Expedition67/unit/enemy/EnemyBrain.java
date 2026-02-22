package com.Expedition67.unit.enemy;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitBrain;

public abstract class EnemyBrain extends UnitBrain {

    protected CardAbility nextAction = null;
    protected Unit target;

    // in case there are actions to perform if player use a card
    public void onPlayerUseCard(Card c) {
        calculateNextMove(c);
    }

    // pre-calculate next action, call when starts player turn
    public abstract void calculateNextMove();

    // in case that the action depends on players card
    public void calculateNextMove(Card c) {

    }

    // combat manager call this to get the next action then perform
    public CardAbility getNextAction() {
        return nextAction;
    }

    public Unit getTarget() {
        return target;
    }
}
