package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class SoulResonanceAbility implements CardAbility {
    private int damageBuff;

    public SoulResonanceAbility(int damageBuff) {
        this.damageBuff = damageBuff;
    }

    @Override
    public void apply(Unit target) {
        // TODO: รอทำระบบ บวกดาเมจให้การ์ด Attack ทุกใบในมือ
    }

}