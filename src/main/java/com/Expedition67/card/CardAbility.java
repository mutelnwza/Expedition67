package com.Expedition67.card;

import com.Expedition67.unit.Unit;

public interface CardAbility {

    public void apply(Unit target);
    
    default void apply(Unit target, Unit src){
        target.getBrain().applyCard(this,src);
    }
    
    default boolean isTickable(){return false;}
    default void onTick(Unit target){}
}
