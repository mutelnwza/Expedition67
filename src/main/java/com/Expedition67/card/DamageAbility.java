package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class DamageAbility implements CardAbility {
    private int Damage;

    public DamageAbility(int Damage){
        this.Damage = Damage;
    }
    @Override
    public void apply(Unit target){
        target.takeDamage(Damage);
        System.out.println("DEAL DAMAGE TO "+target.getName()+" ="+Damage);
    }

    public void setDamage(int newDmg){
        this.Damage=newDmg;
    }
    public int getDamage(){
        return Damage;
    }
}
