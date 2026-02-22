package com.Expedition67.card;

import com.Expedition67.unit.Unit;

public abstract class RemovableAbility extends CardAbility {

    private final int turn;

    public RemovableAbility(int turn, int value, CardType cardType) {
        super(value, cardType);
        this.turn = turn;
    }

    public RemovableAbility(int turn, CardType cardType) {
        super(cardType);
        this.turn = turn;
    }

    public abstract void remove(Unit target);

    public int getTurn() {
        return turn;
    }
}