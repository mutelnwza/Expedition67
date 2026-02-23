package com.Expedition67.unit.player;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.unit.UnitBrain;

/**
 * The brain for the player, handling AP and special card effects like Resonance.
 */
public class PlayerBrain extends UnitBrain {

    private int ap;
    private final int MAX_AP = 4;
    private int resonanceBlockAmount = 0;

    /**
     * Constructs a new PlayerBrain.
     */
    public PlayerBrain() {
        this.ap = MAX_AP;
    }

    @Override
    public UnitBrain copy() {
        return new PlayerBrain();
    }

    public void onUseCard(Card c) {
        this.ap -= c.getAP();
        if (c.getAbility().getCardType() == CardAbility.CardType.ATK && resonanceBlockAmount > 0) {
            this.addDef(resonanceBlockAmount);
        }
    }

    @Override
    public void onTurnEnded() {
        super.onTurnEnded();
        this.ap = MAX_AP;
    }

    /**
     * Applies the Resonance effect, which grants block when attack cards are played.
     *
     * @param amount The amount of block to grant per attack card.
     */
    public void applyResonance(int amount) {
        resonanceBlockAmount += amount;
    }

    /**
     * Gets the current amount of Action Points (AP).
     *
     * @return The current AP.
     */
    public int getAP() {
        return this.ap;
    }
}
