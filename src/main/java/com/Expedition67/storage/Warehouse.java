package com.Expedition67.storage;
//import com.Expedition67.card.Card;

import com.Expedition67.card.*;
import com.Expedition67.unit.Enemy.CryingSlimeBrain;
import com.Expedition67.unit.Enemy.LukchinBrain;
import com.Expedition67.unit.Enemy.VisionBrain;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitStats;
import com.Expedition67.unit.UnitType;
import java.util.HashMap;

public class Warehouse {

    private static Warehouse instance;
    private HashMap<String, EnemyData> unitFactory = new HashMap<>();
    private HashMap<String, Card> cardFactory = new HashMap<>();

    private Warehouse() {

    }

    public static Warehouse Instance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    public void invoke() {
        loadEnemy();
        loadCard();
    }

    private void loadEnemy() {
        //* CRYING SLIME */
        EnemyData cryingSlime = new EnemyData(new Unit("CryingSlime",
                new UnitStats(75, 1, 0), new CryingSlimeBrain(),
                UnitType.ENEMY, 0, 0, 200, 200));
        cryingSlime.getUnit().getAnimator().addAnimation("idle", 0, 5, 2);
        cryingSlime.addActions("ATTACK", new DamageAbility(8));
        cryingSlime.addActions("DEF", new ShieldAbility(6));
        unitFactory.put("CryingSlime", cryingSlime);
        
        /* LUKCHIN */
        EnemyData lukchin = new EnemyData(new Unit("Lukchin",
                new UnitStats(67, 1, 0), new LukchinBrain(),
                UnitType.ENEMY, 0, 0, 200, 200));
        lukchin.getUnit().getAnimator().addAnimation("idle", 0, 5, 2);
        unitFactory.put("Lukchin", lukchin);

        /*VISION */
        EnemyData vision = new EnemyData(new Unit("Vision", new UnitStats(150, 1, 0),
                new VisionBrain(), UnitType.MINIBOSS, 0, 0, 200, 200));
        vision.getUnit().getAnimator().addAnimation("idle", 0, 5, 2);
        vision.addActions("HEAL", new HealAbility(10,18));
        unitFactory.put("Vision", vision);
    }

    private void loadCard() {

        //Attack
        cardFactory.put("Soul Flicker", new Card("Soul Flicker", 0, true, -1, new DamageAbility(6)));
        cardFactory.put("Remnant Hit", new Card("Remnant Hit", 1, true, -1, new DamageAbility(12)));
        cardFactory.put("Echoing Strike", new Card("Echoing Strike", 2, true, -1, new DamageAbility(30)));
        cardFactory.put("Void Dragon", new Card("Void Dragon", 4, true, -1,new DamageAbility(45)));


        //Defense
        cardFactory.put("Spectral Veil", new Card("Spectral Veil", 1, true, -1, new ShieldAbility(10)));
        cardFactory.put("Soul Aegis", new Card("Soul Aegis", 2, true, -1, new ShieldHealAbility(20, 10)));
        cardFactory.put("Celestial Singularity", new Card("Celestial Singularity", 3, true, -1, new ShieldAbility(35)));
        cardFactory.put("Event Horizon", new Card("Event Horizon", 4, true, -1, new ShieldAbility(70)));

        //Buff
        // cardFactory.put("Soul Resonance", new Card("Soul Resonance", 1, true, -1, new BuffAbility(0.20f)));
        // cardFactory.put("Harmonic Resonance", new Card("Harmonic Resonance", 2, true, -1, new BuffAbility(0.40f)));
        // cardFactory.put("Sovereign's Overdrive", new Card("Sovereign’s Overdrive", 0, false, 2, new OverdriveAbility(5, 1.0f)));

        //Heal
        cardFactory.put("Ethereal Restoration", new Card("Ethereal Restoration", 2, true, -1, new HealAbility(8)));
        cardFactory.put("Eternal Soul Rebirth", new Card("Eternal Soul Rebirth", 3, true, -1, new HealAbility(18)));
    }

    public Card spawnCard(String name) {
        return cardFactory.get(name);
    }

    public Unit spawnEnemy(String name, int x, int y) {
        Unit master = unitFactory.get(name).getUnit();
        if (master != null) {
            return master.copy(x, y);
        } else {
            return null;
        }
    }

    public CardAbility spawnAction(String enemyName, String actionName) {
        EnemyData master = unitFactory.get(enemyName);
        if (master != null) {
            return master.getAbility(actionName);
        }
        return null;
    }
}
