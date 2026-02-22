package com.Expedition67.card;

import com.Expedition67.unit.Unit;

public abstract class CardAbility {

    private final CardType cardType;
    protected int value;
    protected int baseValue;

    public CardAbility(int value, CardType cardType) {
        this.cardType = cardType;
        this.value = value;
        this.baseValue = value;
    }

    public CardAbility(CardType cardType) {
        this.cardType = cardType;
    }

    public abstract void apply(Unit target);

    public void apply(Unit target, Unit src) {
        src.getBrain().onUseCard(this);
    }

    public CardType getCardType() {
        return cardType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        value = newValue;
        baseValue = newValue;
    }

    public void setTemporaryValue(int newValue) {
        value = newValue;
    }

    public void resetValue() {
        value = baseValue;
    }

    public abstract CardAbility copy();

    public enum CardType {
        ATK, DEF, HEAL, BUFF, VOID, DEBUFF
    }
}
