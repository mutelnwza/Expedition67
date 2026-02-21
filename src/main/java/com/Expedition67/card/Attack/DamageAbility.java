package com.Expedition67.card.Attack;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class DamageAbility extends CardAbility {
    private int minDmg=0;
    private int maxDmg=0;

    public DamageAbility(int value, CardType cardType) {
        super(value, cardType);
        //TODO Auto-generated constructor stub
    }

    public DamageAbility(int min, int max, CardType cardType){
        super(cardType);
        this.minDmg=min;
        this.maxDmg=max;
    }
    @Override
    public void apply(Unit target){
        target.takeDamage(calculateDamage());
        CombatManager.Instance().addActionString(" deal damage to "+target.getName()+" ="+value);
    }

    @Override
    public void apply(Unit target, Unit src){
        super.apply(target,src);
        int dmg = (int)(calculateDamage() * (1+(src.getUnitStats().getStr()/10)));
        if(Math.random() <= src.getUnitStats().getCrit()){
            dmg*=1.5;
        }
        target.takeDamage(dmg);
        CombatManager.Instance().addActionString(" deal damage to "+target.getName()+" ="+value);
    }

    protected int calculateDamage(){
        int dmg = value;
        if(maxDmg>0 && minDmg>0) dmg= (int)(Math.random() * (maxDmg - minDmg + 1)) + minDmg;
        return dmg;
    }

    public void setDamage(int newDmg){
        this.value=newDmg;
    }
    public int getDamage(){
        return value;
    }
}
