package com.Expedition67.card.heal;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

public class HealAbility extends CardAbility {

    private int minHeal;
    private int maxHeal;

    public HealAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    public HealAbility(int minH, int maxH, CardType cardType) {
        super(maxH, cardType);
        this.minHeal = minH;
        this.maxHeal = maxH;
    }

    @Override
    public void apply(Unit target) {
        int healAmount = getHealAmount();
        target.getBrain().heal(healAmount);
        CombatManager.Instance().addActionString(String.format(" restores %d HP.", healAmount));
    }

    @Override
    public void apply(Unit target, Unit src) {
        super.apply(target, src);
        int healAmount = getHealAmount();
        target.getBrain().heal(healAmount);
        CombatManager.Instance().addActionString(String.format(" restores %d HP.", healAmount));
    }

    private int getHealAmount() {
        if (minHeal > 0 && maxHeal > 0) return (int) (minHeal + (Math.random() * ((maxHeal - minHeal) + 1)));
        return value;
    }

    @Override
    public CardAbility copy() {
        if (minHeal > 0 && maxHeal > 0) return new HealAbility(minHeal, maxHeal, getCardType());
        return new HealAbility(getValue(), getCardType());
    }
}
