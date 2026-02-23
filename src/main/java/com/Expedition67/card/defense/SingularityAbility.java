package com.Expedition67.card.defense;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents a defensive ability that grants Block and makes the next card played free.
 */
public class SingularityAbility extends CardAbility {

    /**
     * Constructs a Singularity ability.
     *
     * @param value    The amount of Block to grant.
     * @param cardType The type of card, typically DEF or BUFF.
     */
    public SingularityAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    /**
     * Applies the singularity effect to the target unit.
     *
     * @param target The unit to receive the Block.
     */
    @Override
    public void apply(Unit target) {
        target.getBrain().addDef(this.value);
        CombatManager.Instance().getDeck().setFreeCard(2, this);
        CombatManager.Instance().addActionString(String.format(" creates a singularity, granting %d Block and making the next card have no AP cost!", this.value));
    }

    /**
     * Creates a copy of this SingularityAbility.
     *
     * @return A new SingularityAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new SingularityAbility(getValue(), getCardType());
    }
}
