package com.Expedition67.unit;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.card.attack.DamageAbility;
import com.Expedition67.ui.UnitUIHandler;
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
            owner.showStatus(-1 * blocked, UnitUIHandler.STATUS_SHIELD);
            dmgDealt -= blocked;
        }
        if (dmgDealt > 0) {
            owner.getUnitStats().hp = Math.max(0, owner.getUnitStats().hp - dmgDealt);
            owner.showStatus(dmgDealt, UnitUIHandler.STATUS_DAMAGE);
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
        owner.showStatus(amount, UnitUIHandler.STATUS_HEAL);
    }

    public void addDef(float amount) {
        owner.getUnitStats().def += amount;
        owner.showStatus(amount, UnitUIHandler.STATUS_SHIELD);
    }

    public void addStr(float amount) {
        owner.getUnitStats().str += amount;
        owner.showStatus(amount, UnitUIHandler.STATUS_STR);
    }

    public void addCrit(float amount) {
        owner.getUnitStats().crit += amount;
        owner.showStatus(amount, UnitUIHandler.STATUS_CRIT);
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

    public BuffManager getBuffManager(){
        return this.buffManager;
    }
}
