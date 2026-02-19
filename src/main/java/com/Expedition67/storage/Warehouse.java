package com.Expedition67.storage;
//import com.Expedition67.card.Card;
import com.Expedition67.card.*;
import com.Expedition67.unit.Enemy.EnemyBrain;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitStats;
import java.util.HashMap;

public class Warehouse {
    private static Warehouse instance;
    private HashMap<String, Unit> unitFactory = new HashMap<>();
    private HashMap<String, Card> cardFactory = new HashMap<>();
    private Warehouse(){
        loadEnemy();
        loadCard();
    }

    public static Warehouse Instance(){
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
    }

    private void loadEnemy(){
        Unit test = new Unit("test", new UnitStats(50, 1, 1, 1, 0), new EnemyBrain(),30,30,500,500);
        test.getAnimator().addAnimation("idle", 0, 4, 4);
        unitFactory.put("test", test);
        // Unit Momo = new Unit("Momo", new UnitStats(maxHp, str, critDmg, critRate, def, agi), new MomoBrain(), 0, 0, 0, 0); <- x,y,w,h
        // Momo.getAnimator().addAnimation(name, row, speed, frameCount); <- hard code เอา
        // unitFactory.put("Momo", Momo);
    }

private void loadCard(){
        // Attack
        cardFactory.put("Soul Flicker", new Card("Soul Flicker", "Deal 6 damage. A faint spark of soul energy.", 0, true, -1, new DamageAbility(6)));
        cardFactory.put("Remnant Hit", new Card("Remnant Hit", "Deal 12 damage. A precise strike fueled by lingering soul fragments.", 1, true, -1, new DamageAbility(12)));
        cardFactory.put("Echoing Strike", new Card("Echoing Strike", "Deal 30 damage to all enemies. Condense your spirit into a heavy blow.", 2, true, -1, new DamageAbility(30)));
        cardFactory.put("Void Dragon", new Card("Void Dragon", "Deal 45 damage. If target's HP < 50%, deal 80 damage instead.", 4, true, -1, new DamageAbility(45)));

        // Defense
        cardFactory.put("Spectral Veil", new Card("Spectral Veil", "Gain 10 Block. A thin veil of shattered souls to absorb impacts.", 1, true, -1, new ShieldAbility(1,10)));
        cardFactory.put("Soul Aegis", new Card("Soul Aegis", "Gain 20 Block and heal 10 HP. Converts kinetic force into life energy.", 2, true, -1, new ShieldAbility(1,20)));
        cardFactory.put("Celestial Singularity", new Card("Celestial Singularity", "Gain 35 Block. The next card played costs 0 AP.", 3, true, -1, new ShieldAbility(1,35)));
        cardFactory.put("Event Horizon", new Card("Event Horizon", "Gain 70 Block. Absolute defense. Incoming strikes vanish into the void.", 4, true, -1, new ShieldAbility(1,70)));

        // Buff (ใช้ ShieldAbility ไปก่อนชั่วคราวกัน Error)
        cardFactory.put("Soul Resonance", new Card("Soul Resonance", "Attack cards in your hand gain +6 Damage this turn.", 1, true, -1, new ShieldAbility(1,0)));
        cardFactory.put("Harmonic Resonance", new Card("Harmonic Resonance", "Whenever you play an Attack card this turn, gain 6 Block.", 2, true, -1, new ShieldAbility(1,0)));
        cardFactory.put("Sovereign's Overdrive", new Card("Sovereign's Overdrive", "Lose 5 HP. Your next attack has a 100% Critical Rate.", 0, true, -1, new ShieldAbility(1,0)));

        // Heal
        cardFactory.put("Ethereal Restoration", new Card("Ethereal Restoration", "Heal 15 HP and remove all debuffs.", 2, true, -1, new HealAbility(15)));
        cardFactory.put("Eternal Soul Rebirth", new Card("Eternal Soul Rebirth", "Heal 28 HP and gain 10 Block. If HP < 20%, gain 30 Block.", 3, true, -1, new HealAbility(28)));
    }

    public Unit spawnEnemy(String name,int x, int y){
        Unit master = unitFactory.get(name);
        if(master!=null){
            return master.copy(x, y);
        }
        else return null;
    }
}
