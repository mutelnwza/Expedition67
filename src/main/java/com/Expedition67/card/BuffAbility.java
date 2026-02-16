package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class BuffAbility implements CardAbility {
    private int amount;

    public BuffAbility(int amount){
        this.amount = amount;
    }
    @Override
    public void apply(Unit target){
        
    }

    public void setamount(int newDmg){
        this.amount=newDmg;
    }
    public int getamount(){
        return amount;
    }
}
