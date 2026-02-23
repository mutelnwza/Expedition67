package com.Expedition67.unit.enemy;

import com.Expedition67.card.CardAbility;

import java.util.HashMap;

/**
 * Stores data for an enemy, including its base unit and available actions.
 */
public class EnemyData {

    private final Enemy unit;
    private final HashMap<String, CardAbility> actions;

    /**
     * Constructs a new EnemyData object.
     *
     * @param unit The enemy unit this data belongs to.
     */
    public EnemyData(Enemy unit) {
        this.unit = unit;
        this.actions = new HashMap<>();
    }

    /**
     * Adds a new action to the enemy.
     *
     * @param name The name of the action.
     * @param ca   The card ability representing the action.
     */
    public void addActions(String name, CardAbility ca) {
        actions.put(name, ca);
    }

    /**
     * Gets a specific ability by its name.
     *
     * @param name The name of the ability to retrieve.
     * @return The CardAbility object, or null if not found.
     */
    public CardAbility getAbility(String name) {
        return actions.get(name);
    }

    /**
     * Gets the enemy unit associated with this data.
     *
     * @return The Enemy object.
     */
    public Enemy getUnit() {
        return unit;
    }
}
