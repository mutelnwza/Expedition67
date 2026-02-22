package com.Expedition67.unit.enemy;

import com.Expedition67.card.CardAbility;

import java.util.HashMap;

public class EnemyData {

    private final Enemy unit;
    private final HashMap<String, CardAbility> actions;

    public EnemyData(Enemy unit) {
        this.unit = unit;
        actions = new HashMap<>();
    }

    public void addActions(String name, CardAbility ca) {
        actions.put(name, ca);
    }

    public CardAbility getAbility(String name) {
        return actions.get(name);
    }

    public Enemy getUnit() {
        return unit;
    }
}
