package com.Expedition67.unit;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.ITickable;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manages all active buffs and debuffs on a unit.
 */
public class BuffManager {

    private final Unit owner;
    private final ArrayList<BuffTracker> currentBuffs;

    /**
     * Constructs a new BuffManager for a specific unit.
     *
     * @param owner The unit that this manager belongs to.
     */
    public BuffManager(Unit owner) {
        this.owner = owner;
        this.currentBuffs = new ArrayList<>();
    }

    /**
     * Adds a new buff or debuff to the unit.
     *
     * @param ability The removable ability to add.
     */
    public void addBuff(RemovableAbility ability) {
        currentBuffs.add(new BuffTracker(ability.getTurn(), ability));
    }

    /**
     * Removes a specific buff or debuff from the unit.
     *
     * @param ability The removable ability to remove.
     */
    public void removeBuff(RemovableAbility ability) {
        ability.remove(owner);
        currentBuffs.removeIf(bt -> bt.ability == ability);
    }

    /**
     * Removes all buffs and debuffs from the unit.
     */
    public void resetBuffs() {
        for (BuffTracker bt : new ArrayList<>(currentBuffs)) {
            if (bt.turnLeft > 0) {
                removeBuff(bt.ability);
            }
        }
    }

    /**
     * Called at the end of a turn to update buff durations and apply tick effects.
     */
    public void onTurnEnded() {
        Iterator<BuffTracker> iterator = currentBuffs.iterator();
        while (iterator.hasNext()) {
            BuffTracker bf = iterator.next();
            bf.turnLeft--;
            if (bf.ability instanceof ITickable i) {
                i.onTick(owner);
            }
            if (bf.turnLeft == 0) {
                bf.ability.remove(owner);
                iterator.remove();
            }
        }
    }

    /**
     * Gets a list of all active debuffs on the unit.
     *
     * @return An ArrayList of RemovableAbility debuffs.
     */
    public ArrayList<RemovableAbility> getCurrentDebuffs() {
        ArrayList<RemovableAbility> debuffs = new ArrayList<>();
        for (BuffTracker bf : currentBuffs) {
            if (bf.ability.getCardType() == CardAbility.CardType.DEBUFF) {
                debuffs.add(bf.ability);
            }
        }
        return debuffs;
    }

    /**
     * A private inner class to track the duration of a buff.
     */
    private static class BuffTracker {
        int turnLeft;
        RemovableAbility ability;

        BuffTracker(int turn, RemovableAbility ability) {
            this.turnLeft = turn;
            this.ability = ability;
        }
    }
}
