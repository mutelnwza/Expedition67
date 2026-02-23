package com.Expedition67.unit;

/**
 * Stores the core statistics for a unit, such as health, strength, and defense.
 */
public class UnitStats {

    protected float maxHp, hp, str, def, crit;

    /**
     * Constructs a new UnitStats object.
     *
     * @param maxHp The maximum health of the unit.
     * @param str   The strength of the unit.
     * @param def   The defense of the unit.
     */
    public UnitStats(float maxHp, float str, float def) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.str = str;
        this.def = def;
        this.crit = 0.1f;
    }

    /**
     * Creates a copy of these UnitStats.
     *
     * @return A new UnitStats instance with the same values.
     */
    public UnitStats copy() {
        return new UnitStats(maxHp, str, def);
    }

    public float getHp() {
        return this.hp;
    }

    public void setMaxHp(float hp) {
        maxHp = hp;
    }

    public float getMaxHp() {
        return this.maxHp;
    }

    public float getCrit() {
        return this.crit;
    }

    public float getDef() {
        return def;
    }

    public float getStr() {
        return str;
    }
}
