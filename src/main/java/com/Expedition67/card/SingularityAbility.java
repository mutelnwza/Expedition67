package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class SingularityAbility extends CardAbility {
    public SingularityAbility(CardType cardType) {
        super(cardType);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void apply(Unit target) {
        target.getBrain().addDef(value);
        // TODO: รอทำระบบ setNextCardFree(true) ทำให้การ์ดใบถัดไป Cost 0
        CombatManager.Instance().addActionString(" deal cosmic damage to " + target.getName()+ " = " + value);
    }

}