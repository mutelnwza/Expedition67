package com.Expedition67.card;
import com.Expedition67.unit.Unit;ผ

public class HealAbility implements CardAbility {
    private int heal;

    public HealAbility(int heal){
        this.heal = heal;
    }
    @Override
    public void apply(Unit target){
        target.getBrain().heal(heal);
    }
}
