package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class HealAbility implements CardAbility {
    private int minHeal;
    private int maxHeal;
    private int healAmount;
    public HealAbility(int heal){
        this.maxHeal = heal;
    }

    public HealAbility(int minH, int maxH){
        this.minHeal = minH;
        this.maxHeal = maxH;
    }
    @Override
    public void apply(Unit target){
        healAmount = maxHeal;

        if(minHeal > 0){
            healAmount = minHeal + (int)(Math.random() * ((maxHeal - minHeal) + 1));
        }

        target.getBrain().heal(healAmount);
        this.healAmount = healAmount;
    }
    @Override
    public void apply(Unit src, Unit target) {
        CombatManager.Instance().addActionString(src.getName() + " restores " + target.getName() + " for = " + healAmount);
    }

}
