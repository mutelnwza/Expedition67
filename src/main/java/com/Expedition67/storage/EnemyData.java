package com.Expedition67.storage;

import java.util.HashMap;

import com.Expedition67.card.CardAbility;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Unit;

public class EnemyData {
    private Enemy unit;
    private HashMap<String, CardAbility> actions;

    public EnemyData(Enemy unit){
        this.unit=unit;
        actions = new HashMap<>();
    }

    public void addActions(String name, CardAbility ca){
        actions.put(name, ca);
    }
    
    public CardAbility getAbility(String name){
        return actions.get(name);
    }
    public Enemy getUnit(){
        return unit;
    }
}
