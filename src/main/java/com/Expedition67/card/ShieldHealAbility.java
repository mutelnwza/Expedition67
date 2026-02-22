package com.Expedition67.card;

import com.Expedition67.unit.Unit;

public class ShieldHealAbility extends CardAbility {
    private final ShieldAbility shield;
    private final HealAbility heal;
    protected int shieldVal;
    protected int healVal;
    public ShieldHealAbility(int shield, int heal, CardType cardType) {
        super(cardType);

        this.shield = new ShieldAbility(shield, cardType);
        this.heal = new HealAbility(heal, cardType);
        shieldVal = shield;
        healVal = heal;
    }

    @Override
    public void apply(Unit target) {
        shield.apply(target);
        heal.apply(target);
    }

    protected void setHealValue(int newVal){
        heal.setValue(newVal);
    }

    protected void setShieldValue(int newVal){
        shield.setValue(newVal);
    }
}