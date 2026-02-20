package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class VoidDragonAbility implements CardAbility {
    private float normalDamage;
    private float boostDamage;

    public VoidDragonAbility(float normalDamage, float boostDamage) {
        this.normalDamage = normalDamage;
        this.boostDamage = boostDamage;
    }

    @Override
    public void apply(Unit target, Unit src) {
        target.takeDamage(normalDamage);
        // TODO: รอทำเงื่อนไขเช็คเลือด < 50% เพื่อทำดาเมจ boostDamage
        // TODO: รอทำระบบลบการ์ด Void 
    }

    @Override
    public void apply(Unit target) {}
}