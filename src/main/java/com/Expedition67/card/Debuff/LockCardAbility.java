package com.Expedition67.card.Debuff;

import com.Expedition67.card.Card;
import com.Expedition67.card.RemoveableAbility;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Unit;
import java.util.ArrayList;

public class LockCardAbility extends RemoveableAbility {

    private ArrayList<Card> lockedCard;

    public LockCardAbility(int turn, int value, CardType cardType) {
        super(turn, value, cardType);
    }

    @Override
    public void apply(Unit target) {
        lockedCard = new ArrayList<>();

        Deck deck = CombatManager.Instance().getDeck();
        int count = 0;
        Card c;
        while (count < value && (c = deck.getRandomCardFromHand()) != null) {
            c.setLocked(true);
            lockedCard.add(c);
        }
    }

    @Override
    public void remove(Unit target){
        for(Card c : lockedCard){
            c.setLocked(false);
        }
    }

}
