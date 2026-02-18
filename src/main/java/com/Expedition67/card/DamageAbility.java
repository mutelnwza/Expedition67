package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class DamageAbility extends CardAbility {

    public DamageAbility(int value, CardType cardType) {
        super(value, cardType);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void apply(Unit target){
        target.takeDamage(value);
        System.out.println("DEAL DAMAGE TO "+target.getName()+" ="+value);
    }

    public void setDamage(int newDmg){
        this.value=newDmg;
    }
    public int getDamage(){
        return value;
    }
}
