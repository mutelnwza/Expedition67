package com.Expedition67.card.buff;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents an Overdrive buff that increases critical hit chance at the cost of health.
 */
public class OverdriveAbility extends RemovableAbility {

    private final float selfDamage;
    private final float crit;

    /**
     * Constructs an Overdrive ability.
     *
     * @param turn       The number of turns the buff lasts.
     * @param selfDamage The amount of health the user loses upon activation.
     * @param crit       The amount of critical hit chance to grant.
     * @param cardType   The type of card, typically BUFF.
     */
    public OverdriveAbility(int turn, float selfDamage, float crit, CardType cardType) {
        super(turn, cardType);
        this.selfDamage = selfDamage;
        this.crit = crit;
    }

    /**
     * Applies the Overdrive buff to the target unit.
     *
     * @param target The unit to apply the buff to.
     */
    @Override
    public void apply(Unit target) {
        target.takeDamage(selfDamage);
        target.getBrain().addCrit(crit);
        CombatManager.Instance().addActionString(String.format(" enters an overdrive, losing %.2f HP for a guaranteed critical hit on the next attack!", selfDamage));
    }

    /**
     * Removes the Overdrive buff from the target unit.
     *
     * @param target The unit from which to remove the buff.
     */
    @Override
    public void remove(Unit target) {
        target.getBrain().addCrit(crit * -1);
        CombatManager.Instance().setActionString("Overdrive has ended.");
    }

    /**
     * Creates a copy of this OverdriveAbility.
     *
     * @return A new OverdriveAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new OverdriveAbility(getTurn(), selfDamage, crit, getCardType());
    }
}
