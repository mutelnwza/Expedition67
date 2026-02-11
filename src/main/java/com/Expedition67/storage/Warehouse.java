package com.Expedition67.storage;
import com.Expedition67.card.BuffAbility;
import com.Expedition67.card.Card;
import com.Expedition67.card.DamageAbility;
import com.Expedition67.card.HealAbility;
import com.Expedition67.card.ShieldAbility;
import com.Expedition67.unit.Unit;
import java.util.HashMap;

public class Warehouse {
    private static Warehouse instance;
    private HashMap<String, Unit> unitFactory = new HashMap<>();
    private HashMap<String, Card> cardFactory = new HashMap<>();
    private Warehouse(){
        loadCard();
    }

    public static Warehouse Instance(){
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
    }

    private void loadEnemy(){
        // UnitBrain momoBrain = new UnitBrain();
        // Unit Momo = new Unit("Momo", new UnitStats(maxHp, str, critDmg, critRate, def, agi), momoBrain, new, 0, 0, 0, 0);
        // unitFactory.put("Momo", Momo);
        // momoBrain.setOwner(Momo);
        // Momo.getAnimator().addAnimation(name, row, speed, frameCount);
    }

    private void loadCard(){

        //Attack
        cardFactory.put("Soul Flicker", new Card("Soul Flicker", 0, true, -1, new DamageAbility(2)));
        cardFactory.put("Remnant Hit", new Card("Remnant Hit", 1, true, -1, new DamageAbility(6)));
        cardFactory.put("Echoing Strike", new Card("Echoing Strike", 2, true, -1, new DamageAbility(14)));
        cardFactory.put("Void Dragon", new Card("Void Dragon", 4, true, -1,new DamageAbility(30)));


        //Defense
        cardFactory.put("Spectral Veil", new Card("Spectral Veil", 1, true, -1, new ShieldAbility(7)));
        cardFactory.put("Soul Aegis", new Card("Soul Aegis", 2, true, -1, new ShieldAbility(12)));
        cardFactory.put("Celestial Singularity", new Card("Celestial Singularity", 3, true, -1, new ShieldAbility(25)));
        cardFactory.put("Event Horizon", new Card("Event Horizon", 4, true, -1, new ShieldAbility(50)));

        //Buff
        cardFactory.put("Soul Resonance", new Card("Soul Resonance", 1, true, -1, new BuffAbility(0.20f)));
        cardFactory.put("Harmonic Resonance", new Card("Harmonic Resonance", 2, true, -1, new BuffAbility(0.40f)));
        cardFactory.put("Sovereign's Overdrive", new Card("Sovereign's Overdrive", 0, true, -1, new BuffAbility(1.0f)));

        //Heal
        cardFactory.put("Ethereal Restoration", new Card("Ethereal Restoration", 2, true, -1, new HealAbility(8)));
        cardFactory.put("Eternal Soul Rebirth", new Card("Eternal Soul Rebirth", 3, true, -1, new HealAbility(18)));
    }

    public Unit getEnemy(String name){
        return null;
    }
}
