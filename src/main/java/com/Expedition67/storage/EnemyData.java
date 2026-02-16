package com.Expedition67.storage;

import java.util.HashMap;

import com.Expedition67.card.CardAbility;
import com.Expedition67.unit.Unit;

public class EnemyData {
    private Unit unit;
    private HashMap<String, CardAbility> actions;

    public EnemyData(Unit unit){
        this.unit=unit;
        actions = new HashMap<>();
    }

    public void addActions(String name, CardAbility ca){
        actions.put(name, ca);
    }
    
    public CardAbility getAbility(String name){
        return actions.get(name);
    }
    public Unit getUnit(){
        return unit;
    }
}
