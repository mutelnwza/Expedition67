package com.Expedition67.card;

import com.Expedition67.unit.Unit;

public abstract class CardAbility {
    public enum CardType{
        ATK,DEF,HEAL,BUFF,VOID;
    }

    private final CardType cardType;
    protected int value;

    public CardAbility(int value, CardType cardType){
        this.cardType = cardType;
        this.value = value;
    }

    public CardAbility(CardType cardType){
        this.cardType = cardType;
    }
    
    public abstract void apply(Unit target);
    public CardType getCardType(){return cardType;}
    public int getValue(){return value;}
}
