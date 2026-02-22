package com.Expedition67.card.Attack;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility.CardType;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Unit;
import java.util.ArrayList;

public class VoidDragonAbility extends DamageAbility {

    private int normalDamage;
    private int boostedDamage;

    public VoidDragonAbility(int normal, int boosted, CardType cardType) {
        super(normal, cardType);
        normalDamage = normal;
        boostedDamage = boosted;
    }

    @Override
    public void apply(Unit target, Unit src) {
        float targetHp = target.getUnitStats().getHp();
        if (targetHp < target.getUnitStats().getMaxHp() * .5f) {
            super.apply(target,src,boostedDamage);
        } else {
            super.apply(target,src,normalDamage);
        }
        Deck deck = CombatManager.Instance().getDeck();
        ArrayList<Card> allCards = deck.getAllCards();
        for(Card c : allCards){
            if(c.getAbility().getCardType()==CardType.VOID){
                deck.removeFromDeck(c);
            }
        }
    }
}
