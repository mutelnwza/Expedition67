package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class ShieldHealAbility implements CardAbility {
    private int shield;
    private int heal;

    public ShieldHealAbility(int shield, int heal) {
        this.shield = shield;
        this.heal = heal;
    }

    @Override
    public void apply(Unit target) {
        target.getBrain().addDef(shield); 
        target.getBrain().heal(heal);     
    }
}