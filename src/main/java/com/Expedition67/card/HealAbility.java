package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class HealAbility extends CardAbility {
    private int minHeal;
    private int maxHeal;
    public HealAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    public HealAbility(int minH, int maxH, CardType cardType){
        super(maxH, cardType);
        this.minHeal = minH;
        this.maxHeal = maxH;
    }
    @Override
    public void apply(Unit target){
        int healAmount = value;

        if(minHeal > 0 && maxHeal > 0){
            healAmount = minHeal + (int)(Math.random() * ((maxHeal - minHeal) + 1));
        }
        target.getBrain().heal(healAmount);
        CombatManager.Instance().addActionString(" restores " + target.getName()+ "for = " + healAmount);
    }

}
