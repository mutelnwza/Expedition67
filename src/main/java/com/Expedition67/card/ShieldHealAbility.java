package com.Expedition67.card;

import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class ShieldHealAbility extends CardAbility {
    private int minShield, maxShield;
    private int minHeal, maxHeal;

    /**
     * Constructor for static values.
     * @param shield Static shield amount
     * @param heal Static heal amount
     */
    public ShieldHealAbility(int shield, int heal, CardType cardType) {
        super(cardType);
        this.minShield = this.maxShield = shield;
        this.minHeal = this.maxHeal = heal;
    }

    /**
     * Constructor for randomized ranges.
     */
    public ShieldHealAbility(int minS, int maxS, int minH, int maxH, CardType cardType) {
        super(cardType);
        this.minShield = minS;
        this.maxShield = maxS;
        this.minHeal = minH;
        this.maxHeal = maxH;
    }

    @Override
    public void apply(Unit target) {

        int finalShield = calculateValue(minShield, maxShield);
        
        int finalHeal = calculateValue(minHeal, maxHeal);

        if (finalShield > 0) {
            target.getBrain().addDef(finalShield);
        }
        if (finalHeal > 0) {
            target.getBrain().heal(finalHeal);
        }

        // Log the dual action
        String logMsg = String.format(" %s: +%d Shield, +%d HP", target.getName(), finalShield, finalHeal);
        CombatManager.Instance().addActionString(logMsg);
    }

    private int calculateValue(int min, int max) {
        if (max > 0 && max >= min) {
            return min + (int)(Math.random() * ((max - min) + 1));
        }
        return Math.max(0, min);
    }
}