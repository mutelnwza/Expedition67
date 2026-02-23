package com.Expedition67.unit.enemy;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitBrain;

/**
 * Defines the abstract base for an enemy's AI and decision-making logic.
 */
public abstract class EnemyBrain extends UnitBrain {

    protected CardAbility nextAction = null;
    protected Unit target;

    /**
     * Determines the enemy's next action, called at the start of the player's turn.
     */
    public abstract void calculateNextMove();

    /**
     * Called when the player uses a card, allowing the enemy to react.
     *
     * @param c The card used by the player.
     */
    public void onPlayerUseCard(Card c) {
        calculateNextMove(c);
    }

    /**
     * An optional method for enemies whose actions depend on the player's card.
     *
     * @param c The card used by the player.
     */
    public void calculateNextMove(Card c) {
    }

    /**
     * Gets the action the enemy has decided to perform.
     *
     * @return The CardAbility representing the next action.
     */
    public CardAbility getNextAction() {
        return nextAction;
    }

    /**
     * Gets the target of the enemy's action.
     *
     * @return The target Unit.
     */
    public Unit getTarget() {
        return target;
    }
}
