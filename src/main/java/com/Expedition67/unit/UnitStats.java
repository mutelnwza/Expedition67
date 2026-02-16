package com.Expedition67.unit;

public class UnitStats {

    //only for stats tracking
    protected float maxHp, hp, str, critRate, critDmg, def;

    public UnitStats(float maxHp, float str, float critDmg, float critRate, float def) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.str = str;
        this.critDmg = critDmg;
        this.critRate = critRate;
        this.def = def;
    }

    public float getHp() {
        return this.hp;
    }

    public float getMaxHp() {
        return this.maxHp;
    }

    public UnitStats copy() {
        return new UnitStats(maxHp, str, critDmg, critRate, def);
    }
}
