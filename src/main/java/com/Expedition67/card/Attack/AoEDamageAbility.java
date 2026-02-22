package com.Expedition67.card.Attack;

import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Unit;
import java.util.List;

public class AoEDamageAbility extends DamageAbility {

    public AoEDamageAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target, Unit src) {
        CombatManager.Instance().addActionString(" deals " + value + " damage to ALL enemies");
        List<Enemy> enemies = CombatManager.Instance().getEnemies();
        for(Enemy e : enemies){
            super.apply(e, src);
        }
    }

}
