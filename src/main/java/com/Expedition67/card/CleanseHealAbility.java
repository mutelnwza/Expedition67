package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class CleanseHealAbility extends CardAbility {

    public CleanseHealAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target) {
        target.getBrain().heal(value);
        // TODO: รอทำระบบ ลบล้างสถานะผิดปกติ (Remove all debuffs)
        CombatManager.Instance().addActionString(" cleanses all negative effects " + target.getName()+ " = " + value);
    }

}