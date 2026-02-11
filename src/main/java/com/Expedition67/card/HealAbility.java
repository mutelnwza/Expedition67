package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class HealAbility implements CardAbility {
    private int heal;

    public HealAbility(int heal){
        this.heal = heal;
    }
    @Override
    public void apply(Unit src, Unit target){
        target.heal(heal);
    }
    
}
