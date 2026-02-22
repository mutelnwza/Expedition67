package com.Expedition67.card.buff;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

public class OverdriveAbility extends RemovableAbility {

    private final float selfDamage;
    private final float crit;

    public OverdriveAbility(int turn, float selfDamage, float crit, CardType cardType) {
        super(turn, cardType);
        this.selfDamage = selfDamage;
        this.crit = crit;
    }

    @Override
    public void apply(Unit target) {
        target.takeDamage(selfDamage);
        target.getBrain().addCrit(crit);
        CombatManager.Instance().addActionString(String.format(" enters an overdrive, losing %.2f HP for a guaranteed critical hit on the next attack!", selfDamage));
    }

    @Override
    public void remove(Unit target) {
        target.getBrain().addCrit(crit * -1);
        CombatManager.Instance().addActionString("overdrive has ended.");
    }

    @Override
    public CardAbility copy() {
        return new OverdriveAbility(getTurn(), selfDamage, crit, getCardType());
    }
}
