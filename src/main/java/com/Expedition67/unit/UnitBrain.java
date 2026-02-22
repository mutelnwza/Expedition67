package com.Expedition67.unit;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.card.attack.DamageAbility;

import java.util.ArrayList;

public abstract class UnitBrain {

    protected Unit owner;
    protected BuffManager buffManager;

    public UnitBrain() {
    }

    public final void setOwner(Unit owner) {
        this.owner = owner;
        this.buffManager = new BuffManager(owner);
    }

    public void update() {
    }

    public float takeDamage(float amount) {
        float dmgDealt = amount;
        if (owner.getUnitStats().getDef() > 0) {
            float blocked = Math.min(owner.getUnitStats().getDef(), amount);
            owner.getUnitStats().def -= blocked;
            dmgDealt -= blocked;
        }
        if (dmgDealt > 0) {
            owner.getUnitStats().hp = Math.max(0, owner.getUnitStats().hp - dmgDealt);
        }
        return dmgDealt;
    }

//    public void takeTrueDamage(float amount) {
//        owner.getUnitStats().hp = Math.max(0, owner.getUnitStats().hp - amount);
//    }

    public abstract UnitBrain copy();

    public void applyCard(CardAbility ca, Unit src) {
        if (ca instanceof RemovableAbility rb) {
            buffManager.addBuff(rb);
        }
        if (ca instanceof DamageAbility d) {
            d.apply(this.owner, src);
        } else {
            ca.apply(this.owner);
        }
    }

    public void onUseCard(CardAbility ca) {
    }

    public void heal(float amount) {
        owner.getUnitStats().hp = Math.min(owner.getUnitStats().maxHp, owner.getUnitStats().hp + amount);
    }

    public void addDef(float amount) {
        owner.getUnitStats().def += amount;
    }

    public void addStr(float amount) {
        owner.getUnitStats().str += amount;
    }

    public void addCrit(float amount) {
        owner.getUnitStats().crit += amount;
    }

    public void onTurnStarted() {
        owner.getUnitStats().def = 0;
    }

    public void onTurnEnded() {
        buffManager.onTurnEnded();
    }

    public ArrayList<RemovableAbility> getCurrentDebuffs() {
        return buffManager.getCurrentDebuffs();
    }

    public void removeBuffs(RemovableAbility ra) {
        buffManager.removeBuff(ra);
    }

    public Unit getOwner() {
        return this.owner;
    }
}
