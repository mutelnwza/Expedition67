package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class AoEDamageAbility implements CardAbility {
    private float damage;

    public AoEDamageAbility(float damage) {
        this.damage = damage;
    }

    @Override
    public void apply(Unit target, Unit src) {
        target.takeDamage(damage);
        // TODO: รอทำระบบ(AoE)
    }

    @Override
    public void apply(Unit target) {}
}