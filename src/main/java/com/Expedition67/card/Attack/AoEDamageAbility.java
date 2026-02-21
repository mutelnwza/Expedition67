package com.Expedition67.card.Attack;
import com.Expedition67.card.CardAbility;
import com.Expedition67.card.CardAbility.CardType;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class AoEDamageAbility extends CardAbility {

    public AoEDamageAbility(CardType cardType) {
        super(cardType);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void apply(Unit target) {
        CombatManager.Instance().addActionString (" deals " + value + " damage to ALL enemies");
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apply'");
    }
    // public AoEDamageAbility(int value, CardType cardType) {
    //     super(value, cardType);
    //     //TODO Auto-generated constructor stub
    // }
    // private float damage;
    // @Override
    // public void apply(Unit target) {target.takeDamage(damage);
    //     // TODO: รอทำระบบ(AoE)}
}