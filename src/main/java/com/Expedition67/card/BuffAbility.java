package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class BuffAbility implements CardAbility {
    private float critRate;

    public BuffAbility(float critRate){
        this.critRate = critRate;
    }
    
    @Override
    public void apply(Unit src, Unit target){
        target.getBrain().addCritRate(critRate);
    }

    @Override
    public void apply(Unit target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }
}