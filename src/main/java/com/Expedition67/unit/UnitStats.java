package com.Expedition67.unit;

public class UnitStats {

    //only for stats tracking
    protected float maxHp, hp, str, critRate, critDmg, def, crit;

    public UnitStats(float maxHp, float str, float def) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.str = str;
        this.def = def;
        this.crit = 1;
    }

    public float getHp() {
        return this.hp;
    }

    public float getMaxHp() {
        return this.maxHp;
    }

    public UnitStats copy() {
        return new UnitStats(maxHp, str, def);
    }
}
