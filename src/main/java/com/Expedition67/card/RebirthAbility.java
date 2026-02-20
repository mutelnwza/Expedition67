package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class RebirthAbility implements CardAbility {
    private int healAmount;
    private int normalShield;
    private int boostShield;

    public RebirthAbility(int healAmount, int normalShield, int boostShield) {
        this.healAmount = healAmount;
        this.normalShield = normalShield;
        this.boostShield = boostShield;
    }

    @Override
    public void apply(Unit target) {
        target.getBrain().heal(healAmount);
        target.getBrain().addDef(normalShield); 

        // TODO: รอดึงค่า MaxHP ได้ ค่อยทำเงื่อนไข เลือด < 20% เพื่อให้เกราะบูสต์
    }
    public void apply(Unit target, Unit src){
        target.getBrain().applyCard(this,src);
    }
}