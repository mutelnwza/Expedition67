package com.Expedition67.card.defense;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.CombatManager;
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
        int def = value;
        if (max > 0 && min > 0)
            def = (int) (Math.random() * (max - min + 1)) + min;
        target.getBrain().addDef(def);
        CombatManager.Instance().addActionString(String.format(" add %d shield to %s!", value, target.getName()));
    }

    @Override
    public CardAbility copy() {
        if (min > 0 && max > 0)
            return new ShieldAbility(min, max, getCardType());
        return new ShieldAbility(getValue(), getCardType());
    }
}
