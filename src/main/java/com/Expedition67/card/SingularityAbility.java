package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class SingularityAbility implements CardAbility {
    private int shield;

    public SingularityAbility(int shield) {
        this.shield = shield;
    }

    @Override
    public void apply(Unit target) {
        target.getBrain().addDef(shield);
        // TODO: รอทำระบบ setNextCardFree(true) ทำให้การ์ดใบถัดไป Cost 0
    }

}