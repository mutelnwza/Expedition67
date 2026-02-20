package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class AoEDamageAbility extends CardAbility {
    public AoEDamageAbility(int value, CardType cardType) {
        super(value, cardType);
        //TODO Auto-generated constructor stub
    }
    private float damage;
    @Override
    public void apply(Unit target) {target.takeDamage(damage);
        // TODO: รอทำระบบ(AoE)}
}}