package com.Expedition67.card.Attack;

import com.Expedition67.card.CardName;
import com.Expedition67.core.CombatManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Unit;

public class VoidAttackAbility extends DamageAbility{

    public VoidAttackAbility(int value, CardType cardType) {
        super(value, cardType);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public void apply(Unit target, Unit src){
        super.apply(target,src);
        Deck d = CombatManager.Instance().getDeck();
        d.addCard(Warehouse.Instance().spawnCard(CardName.VOID));
        CombatManager.Instance().addActionString(" adds a VOID card to the deck");
    }
}
