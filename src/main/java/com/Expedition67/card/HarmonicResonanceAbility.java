package com.Expedition67.card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.unit.Unit;

public class HarmonicResonanceAbility implements CardAbility {
    private int defPerAttack;

    public HarmonicResonanceAbility(int defPerAttack) {
        this.defPerAttack = defPerAttack;
    }

    @Override
    public void apply(Unit target) {
        // TODO: รอทำระบบ บัฟติดตัว (ถ้าเล่นการ์ดตี จะได้เกราะเพิ่ม)
    }

}