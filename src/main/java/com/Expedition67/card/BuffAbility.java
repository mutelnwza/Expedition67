package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class BuffAbility implements CardAbility {
    private int amount;

    public BuffAbility(int amount){
        this.amount = amount;
    }
    @Override
    public void apply(Unit src, Unit target){
        //target.getBrain().addCrit(crit);
        CombatManager.Instance().addActionString(src.getName() + "APPLY BUFF TO"+target.getName()+" ="+ amount);
    }

    public void setamount(int newDmg){
        this.amount=newDmg;
    }
    public int getamount(){
        return amount;
    }

    @Override
    public void apply(Unit target) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }
  
}
