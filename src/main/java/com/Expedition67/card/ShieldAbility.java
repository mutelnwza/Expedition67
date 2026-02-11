package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class ShieldAbility implements CardAbility {
    private int Shield;

    public ShieldAbility(int Shield){
        this.Shield = Shield;
    }
    @Override
    public void apply(Unit src, Unit target){
         src.addShield(Shield);
     }
}
