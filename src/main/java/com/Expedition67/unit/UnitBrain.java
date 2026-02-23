package com.Expedition67.unit;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.card.attack.DamageAbility;
import com.Expedition67.ui.UnitUIHandler;

import java.util.ArrayList;

/**
 * Defines the core logic and behavior for a unit.
 */
public abstract class UnitBrain {

    protected Unit owner;
    protected BuffManager buffManager;

    public UnitBrain() {
    }

    /**
     * Creates a copy of this UnitBrain.
     *
     * @return A new instance of the UnitBrain subclass.
     */
    public abstract UnitBrain copy();

    /**
     * Sets the owner of this brain.
     *
     * @param owner The unit that this brain controls.
     */
    public final void setOwner(Unit owner) {
        this.owner = owner;
        this.buffManager = new BuffManager(owner);
    }

    /**
     * Updates the brain's logic, called once per frame.
     */
    public void update() {
    }

    /**
     * Handles the unit taking damage, factoring in defense.
     *
     * @param amount The initial amount of damage.
     * @return The actual damage dealt after defense.
     */
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

    /**
     * Applies a card's ability to the unit.
     *
     * @param ca  The card ability to apply.
     * @param src The source unit using the ability.
     */
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

    /**
     * Called when the unit uses a card.
     *
     * @param ca The card ability being used.
     */
    public void onUseCard(CardAbility ca) {
    }

    /**
     * Heals the unit for a specified amount.
     *
     * @param amount The amount of health to restore.
     */
    public void heal(float amount) {
        owner.getUnitStats().hp = Math.min(owner.getUnitStats().maxHp, owner.getUnitStats().hp + amount);
        owner.showStatus(amount, UnitUIHandler.STATUS_HEAL);
    }

    /**
     * Adds defense points to the unit.
     *
     * @param amount The amount of defense to add.
     */
    public void addDef(float amount) {
        owner.getUnitStats().def += amount;
        owner.showStatus(amount, UnitUIHandler.STATUS_SHIELD);
    }

    /**
     * Adds strength to the unit.
     *
     * @param amount The amount of strength to add.
     */
    public void addStr(float amount) {
        owner.getUnitStats().str += amount;
        owner.showStatus(amount, UnitUIHandler.STATUS_STR);
    }

    /**
     * Adds critical hit chance to the unit.
     *
     * @param amount The amount of critical chance to add.
     */
    public void addCrit(float amount) {
        owner.getUnitStats().crit += amount;
        owner.showStatus(amount, UnitUIHandler.STATUS_CRIT);
    }

    /**
     * Called at the start of the unit's turn.
     */
    public void onTurnStarted() {
        owner.getUnitStats().def = 0;
    }

    /**
     * Called at the end of the unit's turn.
     */
    public void onTurnEnded() {
        buffManager.onTurnEnded();
    }

    /**
     * Gets the list of current debuffs on the unit.
     *
     * @return An ArrayList of RemovableAbility debuffs.
     */
    public ArrayList<RemovableAbility> getCurrentDebuffs() {
        return buffManager.getCurrentDebuffs();
    }

    /**
     * Removes a specific buff or debuff from the unit.
     *
     * @param ra The RemovableAbility to remove.
     */
    public void removeBuffs(RemovableAbility ra) {
        buffManager.removeBuff(ra);
    }

    /**
     * Gets the unit controlled by this brain.
     *
     * @return The owner Unit.
     */
    public Unit getOwner() {
        return this.owner;
    }

    /**
     * Gets the buff manager for this unit.
     *
     * @return The BuffManager instance.
     */
    public BuffManager getBuffManager() {
        return this.buffManager;
    }
}
