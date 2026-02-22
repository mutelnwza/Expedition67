package com.Expedition67.unit;

public class UnitStats {

    //only for stats tracking
    protected float maxHp, hp, str, def, crit;

    public UnitStats(float maxHp, float str, float def) {
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.str = str;
        this.def = def;
        this.crit = 0.1f;
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

    public UnitStats copy() {
        return new UnitStats(maxHp, str, def);
    }
}
