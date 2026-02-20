package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class DamageAbility implements CardAbility {
    private int Damage;

    public DamageAbility(int Damage){
        this.Damage = Damage;
    }
    @Override
    public void apply(Unit src, Unit target) {
        target.takeDamage(Damage);
        CombatManager.Instance().addActionString(src.getName() + " DEAL DAMAGE TO " + target.getName() + " = " + Damage);
    }

    public void setDamage(int newDmg){
        this.Damage=newDmg;
    }
    public int getDamage(){
        return Damage;
    }
    @Override
    public void apply(Unit target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }
}
