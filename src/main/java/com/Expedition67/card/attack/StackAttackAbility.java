package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

public class StackAttackAbility extends DamageAbility {

    private final int stackAdd;

    public StackAttackAbility(int value, int stackAdd, CardType cardType) {
        super(value, cardType);
        this.stackAdd = stackAdd;
    }

    @Override
    public void apply(Unit target, Unit src) {
        float dealtDamage = calculateAndDealDamage(target, src);
        String action = String.format(" deals %.2f damage to %s", dealtDamage, target.getName());

        if (dealtDamage > 0) {
            src.getBrain().addStr(stackAdd);
            action += " and grows stronger!";
        }
        CombatManager.Instance().addActionString(action);
    }

    @Override
    public CardAbility copy() {
        return new StackAttackAbility(value, stackAdd, getCardType());
    }
}
