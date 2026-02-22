package com.Expedition67.unit;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.ITickable;
import java.util.ArrayList;
import java.util.Iterator;

public class BuffManager {

    private static class BuffTracker {
        int turnLeft;
        RemovableAbility ability;

        BuffTracker(int turn, RemovableAbility ability) {
            this.turnLeft = turn;
            this.ability = ability;
        }
    }

    private final Unit owner;
    private final ArrayList<BuffTracker> currentBuffs;

    public BuffManager(Unit owner) {
        this.owner = owner;
        this.currentBuffs = new ArrayList<>();
    }

    public void addBuff(RemovableAbility ability) {
        currentBuffs.add(new BuffTracker(ability.getTurn(), ability));
    }

    public void removeBuff(RemovableAbility ability) {
        ability.remove(owner);
        currentBuffs.removeIf(bt -> bt.ability == ability);
    }

    public void resetBuffs(){
        for(BuffTracker bt : currentBuffs){
            if(bt.turnLeft>0)
            removeBuff(bt.ability);
        }
    }

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

    public ArrayList<RemovableAbility> getCurrentDebuffs() {
        ArrayList<RemovableAbility> debuffs = new ArrayList<>();
        for (BuffTracker bf : currentBuffs) {
            if (bf.ability.getCardType() == CardAbility.CardType.DEBUFF) {
                debuffs.add(bf.ability);
            }
        }
        return debuffs;
    }
}
