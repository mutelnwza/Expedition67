package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class SoulResonanceAbility extends CardAbility {
    public SoulResonanceAbility(CardType cardType) {
        super(cardType);
    }

    @Override
    public void apply(Unit target) {
        // TODO: รอทำระบบ บวกดาเมจให้การ์ด Attack ทุกใบในมือ
        CombatManager.Instance().addActionString(" resonance power to " + target.getName()+ " = " + value);
    }

}