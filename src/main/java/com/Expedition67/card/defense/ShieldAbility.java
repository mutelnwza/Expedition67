package com.Expedition67.card.defense;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

public class ShieldAbility extends CardAbility {

    private int min = 0;
    private int max = 0;

    public ShieldAbility(int min, int max, CardType cardType) {
        super(cardType);
        this.min = min;
        this.max = max;
    }

    public ShieldAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target) {
        int blockAmount = getBlockAmount();
        target.getBrain().addDef(blockAmount);
        CombatManager.Instance().addActionString(String.format(" gains %d block!", blockAmount));
    }

    @Override
    public void apply(Unit target, Unit src) {
        super.apply(target, src);
        int blockAmount = getBlockAmount();
        target.getBrain().addDef(blockAmount);
        CombatManager.Instance().addActionString(String.format(" gains %d block!", blockAmount));
    }

    private int getBlockAmount() {
        if (max > 0 && min > 0)
            return (int) (min + (Math.random() * (max - min + 1)));
        return value;
    }

    @Override
    public CardAbility copy() {
        if (min > 0 && max > 0)
            return new ShieldAbility(min, max, getCardType());
        return new ShieldAbility(getValue(), getCardType());
    }
}
