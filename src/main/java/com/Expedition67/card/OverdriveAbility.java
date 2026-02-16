package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class OverdriveAbility extends RemoveableAbility {
    private float selfDamage; 
    private float crit;

    public OverdriveAbility(int turn, float selfDamage, float crit) {
        super(turn);
        this.selfDamage = selfDamage;
        this.crit = crit;
    }

    @Override
    public void apply(Unit target, Unit src) {
        target.takeDamage(selfDamage);
        src.getBrain().addCrit(crit); 
    }

    @Override
    public void apply(Unit target) {
        
    }

    @Override
    public void remove(Unit target){
        target.getBrain().addCrit(crit*-1);
    }
}