package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents an attack that deals damage and steals Max HP from the target.
 */
public class AttackAndStealHPAbility extends DamageAbility {

    private final int steal;

    /**
     * Constructs an Attack and Steal HP ability.
     *
     * @param value    The base damage of the attack.
     * @param steal    The amount of Max HP to steal from the target.
     * @param cardType The type of card, typically ATK or DEBUFF.
     */
    public AttackAndStealHPAbility(int value, int steal, CardType cardType) {
        super(value, cardType);
        this.steal = steal;
    }

    /**
     * Applies the ability's effect: deals damage and steals Max HP if the target has no defense.
     *
     * @param target The unit to be damaged and have its HP stolen.
     * @param src    The unit performing the attack and receiving the stolen HP.
     */
    @Override
    public void apply(Unit target, Unit src) {
        float dealtDamage = calculateAndDealDamage(target, src);
        String action = String.format(" deals %.2f damage to %s", dealtDamage, target.getName());

        if (target.getUnitStats().getDef() == 0) {
            target.getUnitStats().setMaxHp(target.getUnitStats().getMaxHp() - steal);
            src.getUnitStats().setMaxHp(src.getUnitStats().getMaxHp() + steal);
            src.getBrain().heal(steal);

            action += String.format(" and steals %d Max HP!", steal);
        }
        CombatManager.Instance().addActionString(action);
    }

    /**
     * Creates a copy of this AttackAndStealHPAbility.
     *
     * @return A new AttackAndStealHPAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new AttackAndStealHPAbility(getValue(), steal, getCardType());
    }
}
