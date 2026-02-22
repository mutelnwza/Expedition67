package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class AoEDamageAbility extends DamageAbility {

    public AoEDamageAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target, Unit src) {
        CombatManager.Instance().addActionString(String.format(" deals %d damage to ALL enemies", value));
        List<Enemy> enemies = new ArrayList<>(CombatManager.Instance().getEnemies());
        for (Enemy e : enemies)
            calculateAndDealDamage(e, src);
    }

    @Override
    public CardAbility copy() {
        return new AoEDamageAbility(value, getCardType());
    }
}
