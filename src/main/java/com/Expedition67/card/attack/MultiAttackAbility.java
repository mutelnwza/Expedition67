package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class MultiAttackAbility extends DamageAbility {

    private final int amount;

    public MultiAttackAbility(int value, CardType cardType, int amount) {
        super(value, cardType);
        this.amount = amount;
    }

    public MultiAttackAbility(int min, int max, CardType cardType, int amount) {
        super(min, max, cardType);
        this.amount = amount;
    }

    @Override
    public void apply(Unit target, Unit src) {
        float totalDamage = 0;
        for (int i = 0; i < amount; i++)
            totalDamage += calculateAndDealDamage(target, src);
        CombatManager.Instance().addActionString(String.format(" strikes %s %d times for a total of %.2f damage!", target.getName(), amount, totalDamage));
    }

    @Override
    public CardAbility copy() {
        if (getMinDmg() > 0 && getMaxDmg() > 0)
            return new MultiAttackAbility(getMinDmg(), getMaxDmg(), getCardType(), amount);
        return new MultiAttackAbility(getValue(), getCardType(), amount);
    }
}
