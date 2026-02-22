package com.Expedition67.unit;

import com.Expedition67.card.Attack.DamageAbility;
import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemoveableAbility;
import com.Expedition67.core.ITickable;
import java.util.ArrayList;

public abstract class UnitBrain {

    //use to manage the logic of unit, both player and enemies
    protected class buffTracker {

        protected buffTracker(int t, RemoveableAbility rb) {
            turnLeft = t;
            this.rb = rb;
        }
        protected int turnLeft;
        protected RemoveableAbility rb;
    }

    protected Unit owner;
    protected ArrayList<buffTracker> currentBuff;

    public UnitBrain() {
        currentBuff = new ArrayList<>();
    }

    public final void setOwner(Unit owner) {
        this.owner = owner;
    }

    public void update() {

    }

    public float takeDamage(float amount) {
        float dmgDealt = amount;
        if (owner.getUnitStats().getDef() > 0) {
            float blocked = Math.min(owner.getUnitStats().getDef(), amount);
            owner.getUnitStats().def = owner.getUnitStats().getDef() - blocked;
            dmgDealt -= blocked;
        }
        if (dmgDealt > 0) {
            owner.getUnitStats().hp = Math.max(0, owner.getUnitStats().hp - dmgDealt);
        }
        return dmgDealt;
    }

    public void takeTrueDamage(float amount) {
        owner.getUnitStats().hp = Math.max(0, amount);
    }

    public abstract UnitBrain copy();

    public void applyCard(CardAbility ca, Unit src) {
        if (ca instanceof RemoveableAbility rb) {
            currentBuff.add(new buffTracker(rb.getTurn(), rb));
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
        for (buffTracker bf : currentBuff) {
            bf.turnLeft--;
            if (bf.rb instanceof ITickable i) {
                i.onTick(owner);
            }
            if (bf.turnLeft == 0) {
                bf.rb.remove(owner);
                currentBuff.remove(bf);
            }
        }
    }

    public ArrayList<RemoveableAbility> getCurrentDebuffs() {
        ArrayList<RemoveableAbility> debuffs = new ArrayList<>();

        for (buffTracker bf : currentBuff) {
            if (bf.rb.getCardType() == CardAbility.CardType.DEBUFF) {
                debuffs.add(bf.rb);
            }
        }
        return debuffs;
    }

    public void removeBuffs(RemoveableAbility ra){
        ra.remove(owner);
        for(buffTracker bt : currentBuff){
            if(bt.rb==ra){
                currentBuff.remove(bt);
            System.out.println("Successfully removed a debuff");
            }
        }
    }

    public Unit getOwner() {
        return this.owner;
    }
}
