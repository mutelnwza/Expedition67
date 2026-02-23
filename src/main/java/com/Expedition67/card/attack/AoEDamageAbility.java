package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Area of Effect (AoE) damage ability that hits all enemies.
 */
public class AoEDamageAbility extends DamageAbility {

    /**
     * Constructs an AoE damage ability.
     *
     * @param value    The base amount of damage to deal to each enemy.
     * @param cardType The type of card, typically ATK.
     */
    public AoEDamageAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    /**
     * Applies AoE damage to all enemies.
     *
     * @param target The initial target selected by the player (not used in AoE).
     * @param src    The unit using the ability.
     */
    @Override
    public void apply(Unit target, Unit src) {
        CombatManager.Instance().addActionString(String.format(" deals %d damage to ALL enemies", value));

        List<Enemy> enemies = new ArrayList<>(CombatManager.Instance().getEnemies());

        for (Enemy e : enemies) {
            calculateAndDealDamage(e, src);
        }
    }

    /**
     * Creates a copy of this AoEDamageAbility.
     *
     * @return A new AoEDamageAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new AoEDamageAbility(value, getCardType());
    }
}
