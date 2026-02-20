package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class VoidDragonAbility extends CardAbility {
    private float normalDamage;
    private float boostDamage;

    public VoidDragonAbility(CardType cardType) {
        super(cardType);
    }

    @Override
    public void apply(Unit target) {
        target.takeDamage(normalDamage);
        CombatManager.Instance().addActionString("  void damage to " + target.getName()+ " = " + value);
    }
}