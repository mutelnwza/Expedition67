package com.Expedition67.card;

import com.Expedition67.unit.Unit;

public abstract class CardAbility {
    public enum CardType{
        ATK,DEF,HEAL,BUFF,VOID,DEBUFF;
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
    public void apply(Unit target, Unit src){
        src.getBrain().onUseCard(this);
    }
    
    public CardType getCardType(){return cardType;}
    public int getValue(){return value;}
}
