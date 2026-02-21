package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class OverdriveAbility extends RemoveableAbility {
    private final float selfDamage; 
    private final float crit;

    public OverdriveAbility(int turn, float selfDamage, int crit, CardType cardType) {
        super(turn,cardType);
        this.selfDamage = selfDamage;
        this.crit = crit;
    }

    @Override
    public void apply(Unit target) {
        target.takeDamage(selfDamage);
        target.getBrain().addCrit(crit); 
        CombatManager.Instance().addActionString(" overdrive +" + crit + " crit (-" + selfDamage + " hp)");
    }

    @Override
    public void remove(Unit target){
        target.getBrain().addCrit(crit*-1);
        CombatManager.Instance().addActionString("overdrive expired on " + target.getName());
    }
}
