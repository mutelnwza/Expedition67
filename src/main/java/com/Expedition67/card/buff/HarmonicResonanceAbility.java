package com.Expedition67.card.buff;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.RemovableAbility;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.unit.Unit;

/**
 * Represents a buff that grants Block each time an attack card is played for one turn.
 */
public class HarmonicResonanceAbility extends RemovableAbility {

    /**
     * Constructs a Harmonic Resonance ability.
     *
     * @param value    The amount of Block to grant when an attack card is played.
     * @param cardType The type of card, typically BUFF.
     */
    public HarmonicResonanceAbility(int value, CardType cardType) {
        super(1, value, cardType);
    }

    /**
     * Applies the harmonic resonance effect to the target unit.
     *
     * @param target The unit to apply the buff to.
     */
    @Override
    public void apply(Unit target) {
        GameManager.Instance().getPlayerBrain().applyResonance(value);
        CombatManager.Instance().addActionString(String.format(" creates a harmonic shield that grants %d Block whenever an attack is played this turn.", value));
    }

    /**
     * Removes the harmonic resonance effect from the target unit.
     *
     * @param target The unit from which to remove the buff.
     */
    @Override
    public void remove(Unit target) {
        GameManager.Instance().getPlayerBrain().applyResonance(-value);
    }

    /**
     * Creates a copy of this HarmonicResonanceAbility.
     *
     * @return A new HarmonicResonanceAbility instance with the same properties.
     */
    @Override
    public CardAbility copy() {
        return new HarmonicResonanceAbility(this.value, getCardType());
    }
}
