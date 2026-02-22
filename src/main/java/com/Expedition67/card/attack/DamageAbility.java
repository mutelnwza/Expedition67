package com.Expedition67.card.attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

public class DamageAbility extends CardAbility {

    private int minDmg = 0;
    private int maxDmg = 0;

    public DamageAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    public DamageAbility(int min, int max, CardType cardType) {
        super(cardType);
        this.minDmg = min;
        this.maxDmg = max;
    }

    @Override
    public void apply(Unit target) {
        System.out.println("USE ATTACK TARGET");
        float dmg = calculateDamage(value);
        target.takeDamage(dmg);
        CombatManager.Instance().addActionString(String.format(" deals %.2f damage to %s", dmg, target.getName()));
    }

    @Override
    public void apply(Unit target, Unit src) {
        System.out.println("USE ATTACK TARGET SRC");
        super.apply(target, src);
        float dmg = calculateAndDealDamage(target, src, value);
        CombatManager.Instance().addActionString(String.format(" deals %.2f damage to %s", dmg, target.getName()));
    }

    protected float calculateAndDealDamage(Unit target, Unit src) {
        return calculateAndDealDamage(target, src, value);
    }

    protected float calculateAndDealDamage(Unit target, Unit src, float baseValue) {
        float dmg = calculateDamage(baseValue) * (1 + (src.getUnitStats().getStr() / 10));
        if (Math.random() <= src.getUnitStats().getCrit())
            dmg *= 1.5F;
        target.takeDamage(dmg);
        return dmg;
    }

    protected float calculateDamage(float val) {
        float dmg = val;
        if (maxDmg > 0 && minDmg > 0) dmg = (float) ((Math.random() * (maxDmg - minDmg + 1)) + minDmg);
        return dmg;
    }

    public int getMinDmg() {
        return minDmg;
    }

    public int getMaxDmg() {
        return maxDmg;
    }

    @Override
    public CardAbility copy() {
        if (minDmg > 0 && maxDmg > 0)
            return new DamageAbility(minDmg, maxDmg, getCardType());
        return new DamageAbility(value, getCardType());
    }
}
