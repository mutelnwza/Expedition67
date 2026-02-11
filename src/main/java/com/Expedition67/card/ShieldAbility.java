package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class ShieldAbility extends RemoveableAbility {
    private int Shield;
    
    public ShieldAbility(int turn, int Shield){
        super(turn);
        this.Shield = Shield;
    }

    @Override
    public void apply(Unit target){
         target.getBrain().addDef(Shield);
     }

    @Override
    public void remove(Unit target) {
        target.getBrain().addDef(Shield*-1);
    }
}
