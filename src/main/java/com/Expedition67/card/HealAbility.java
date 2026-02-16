package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class HealAbility implements CardAbility {
    private int minHeal;
    private int maxHeal;

    public HealAbility(int heal){
        this.maxHeal = heal;
    }

    public HealAbility(int minH, int maxH){
        this.minHeal = minH;
        this.maxHeal = maxH;
    }
    @Override
    public void apply(Unit target){
        int healAmount = maxHeal;

        if(minHeal > 0){
            healAmount = minHeal + (int)(Math.random() * ((maxHeal - minHeal) + 1));
        }

        target.getBrain().heal(healAmount);
    }
}
