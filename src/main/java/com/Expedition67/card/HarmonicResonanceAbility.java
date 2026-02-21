package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameManager;
import com.Expedition67.unit.Unit;

public class HarmonicResonanceAbility extends RemoveableAbility {
    public HarmonicResonanceAbility(int value, CardType cardType) {
        super(value, cardType);
    }

    @Override
    public void apply(Unit target) {
        GameManager.Instance().getPlayerBrain().applyResonance(true);
        CombatManager.Instance().addActionString("empowering to " + target.getName()+ " = " + value);
    }

    @Override
    public void remove(Unit target){
        GameManager.Instance().getPlayerBrain().applyResonance(false);
    }

}