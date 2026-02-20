package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class CleanseHealAbility implements CardAbility {
    private int healAmount;

    public CleanseHealAbility(int healAmount) {
        this.healAmount = healAmount;
    }

    @Override
    public void apply(Unit target) {
        target.getBrain().heal(healAmount);
        
        // TODO: รอทำระบบ ลบล้างสถานะผิดปกติ (Remove all debuffs)
    }

}