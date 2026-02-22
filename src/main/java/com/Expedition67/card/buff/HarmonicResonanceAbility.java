package com.Expedition67.card.buff;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.core.GameManager;
import com.Expedition67.unit.Unit;

public class HarmonicResonanceAbility extends RemovableAbility {

    public HarmonicResonanceAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target) {
        GameManager.Instance().getPlayerBrain().applyResonance(value);
        CombatManager.Instance().addActionString(String.format(" creates a harmonic shield that grants %d Block whenever an attack is played this turn.", value));
    }

    @Override
    public void remove(Unit target) {
        GameManager.Instance().getPlayerBrain().applyResonance(-value);
    }

    @Override
    public CardAbility copy() {
        return new HarmonicResonanceAbility(getTurn(), getCardType());
    }
}
