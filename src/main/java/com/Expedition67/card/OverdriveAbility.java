package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class OverdriveAbility implements CardAbility {
    private float selfDamage; 
    private float critRate;

    public OverdriveAbility(float selfDamage, float critRate) {
        this.selfDamage = selfDamage;
        this.critRate = critRate;
    }

    @Override
    public void apply(Unit target, Unit src) {
        src.takeDamage(selfDamage);
        src.getBrain().addCritRate(critRate);
    }

    @Override
    public void apply(Unit target) {
        
    }
}