package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

public class AttackAndStealHPAbility extends DamageAbility {

    private final int steal;

    public AttackAndStealHPAbility(int value, int steal, CardType cardType) {
        super(value, cardType);
        this.steal = steal;
    }

    @Override
    public void apply(Unit target, Unit src) {
        float dealtDamage = calculateAndDealDamage(target, src);
        String action = String.format(" deals %.2f damage to %s", dealtDamage, target.getName());

        if (target.getUnitStats().getDef() == 0) {
            //steal life if target got no block
            target.getUnitStats().setMaxHp(target.getUnitStats().getMaxHp() - steal);
            src.getUnitStats().setMaxHp(src.getUnitStats().getMaxHp() + steal);
            src.getBrain().heal(steal);
            action += String.format(" and steals %d Max HP!", steal);
        }
        CombatManager.Instance().addActionString(action);
    }

    @Override
    public CardAbility copy() {
        return new AttackAndStealHPAbility(getValue(), steal, getCardType());
    }
}
