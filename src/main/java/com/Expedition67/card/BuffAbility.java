package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class BuffAbility implements CardAbility {
    private int Damage;

    public BuffAbility(int Damage){
        this.Damage = Damage;
    }
    @Override
    public void apply(Unit target){
        target.takeDamage(Damage);
    }

    public void setDamage(int newDmg){
        this.Damage=newDmg;
    }
    public int getDamage(){
        return Damage;
    }
}
