package com.Expedition67.unit;

public class UnitStats {
    //only for stats tracking
    protected float maxHp,hp,str,critRate,critDmg,def,agi;
    public UnitStats(float maxHp, float str, float critDmg, float critRate, float def, float agi){
        this.maxHp  = maxHp;
        this.hp = maxHp;
        this.str=str;
        this.critDmg = critDmg;
        this.critRate = critRate;
        this.def = def;
        this.agi = agi;
    }

    public float getHp(){return this.hp;}
}
