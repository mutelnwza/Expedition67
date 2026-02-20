package com.Expedition67.unit;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemoveableAbility;
import com.Expedition67.core.CombatManager;

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

    public void takeDamage(float amount){
        owner.getUnitStats().hp-=amount;
        if(owner.getUnitStats().hp<=0){
            die();
        }
    }

    protected abstract void die();

    public abstract UnitBrain copy();

    public void applyCard(CardAbility ca, Unit src){
        CombatManager.Instance().setActionString(src.getName());
        if(ca instanceof RemoveableAbility rb){
            currentBuff.add(new buffTracker(rb.getTurn(), rb));
        }
        ca.apply(this.owner);
        
    }

    public void heal(float amount) {
        owner.getUnitStats().hp=Math.min(owner.getUnitStats().maxHp, owner.getUnitStats().hp+amount);
    }

    public void addDef(float amount) {
        owner.getUnitStats().def+=amount;
    }

    public void addCrit(float amount) {
        owner.getUnitStats().crit+=amount;
    }

    public void onTurnStarted() {
        owner.getUnitStats().def = 0;
    }

    public void onTurnEnded() {
        for (buffTracker bf : currentBuff) {
            bf.turnLeft--;
            if (bf.rb.isTickable()) {
                bf.rb.onTick(owner);
            }
            if (bf.turnLeft == 0) {
                bf.rb.remove(owner);
                currentBuff.remove(bf);
            }
        }
    }

    public Unit getOwner() {
        return this.owner;
    }
}
