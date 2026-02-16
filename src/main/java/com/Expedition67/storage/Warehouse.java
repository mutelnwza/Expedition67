package com.Expedition67.storage;
//import com.Expedition67.card.Card;
import com.Expedition67.card.*;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Enemy.EnemyBrain;
import com.Expedition67.unit.PlayerBrain;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitStats;
import java.util.HashMap;

public class Warehouse {
    private static Warehouse instance;
    private Unit player;
    private HashMap<String, Unit> unitFactory = new HashMap<>();
    private HashMap<String, Card> cardFactory = new HashMap<>();
    private Warehouse(){
        loadPlayer();
        loadEnemy();
        loadCard();
    }

    public static Warehouse Instance(){
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
    }

    private void loadPlayer() {
        player = new Unit("player", new UnitStats(100, 1, 1, 1, 0), new PlayerBrain(), 0, 0, 100, 100);
        player.getAnimator().addAnimation("idle", 0, 5, 1);
    }

    private void loadEnemy(){
        Enemy test = new Enemy("test", new UnitStats(50, 1, 1, 1, 0), new EnemyBrain(),0,0,100,100);
        test.getAnimator().addAnimation("idle", 0, 4, 2);
        unitFactory.put("test", test);
        // Unit Momo = new Unit("Momo", new UnitStats(maxHp, str, critDmg, critRate, def, agi), new MomoBrain(), 0, 0, 0, 0); <- x,y,w,h
        // Momo.getAnimator().addAnimation(name, row, speed, frameCount); <- hard code เอา
        // unitFactory.put("Momo", Momo);
    }

    private void loadCard(){

        //Attack
        cardFactory.put("Soul Flicker", new Card("Soul Flicker", 0, true, -1, new DamageAbility(2)));
        cardFactory.put("Remnant Hit", new Card("Remnant Hit", 1, true, -1, new DamageAbility(6)));
        cardFactory.put("Echoing Strike", new Card("Echoing Strike", 2, true, -1, new DamageAbility(14)));
        cardFactory.put("Void Dragon", new Card("Void Dragon", 4, true, -1,new DamageAbility(30)));


        //Defense
        cardFactory.put("Spectral Veil", new Card("Spectral Veil", 1, true, -1, new ShieldAbility(1,7)));
        cardFactory.put("Soul Aegis", new Card("Soul Aegis", 2, true, -1, new ShieldAbility(1,12)));
        cardFactory.put("Celestial Singularity", new Card("Celestial Singularity", 3, true, -1, new ShieldAbility(1,25)));
        cardFactory.put("Event Horizon", new Card("Event Horizon", 4, true, -1, new ShieldAbility(1,50)));

        //Buff
        // cardFactory.put("Soul Resonance", new Card("Soul Resonance", 1, true, -1, new BuffAbility(0.20f)));
        // cardFactory.put("Harmonic Resonance", new Card("Harmonic Resonance", 2, true, -1, new BuffAbility(0.40f)));
        // cardFactory.put("Sovereign's Overdrive", new Card("Sovereign's Overdrive", 0, true, -1, new BuffAbility(1.0f)));

        //Heal
        cardFactory.put("Ethereal Restoration", new Card("Ethereal Restoration", 2, true, -1, new HealAbility(8)));
        cardFactory.put("Eternal Soul Rebirth", new Card("Eternal Soul Rebirth", 3, true, -1, new HealAbility(18)));
    }

    public Unit spawnPlayer(int x, int y){
        Unit clone = player.copy(x, y);
        clone.getAnimator().play("idle");
        return clone;
    }

    public Enemy spawnEnemy(String name,int x, int y){
        Enemy master = (Enemy) unitFactory.get(name);
        if(master!=null){
            Enemy clone = master.copy(x, y);
            clone.getAnimator().play("idle");
            return clone;
        }
        else return null;
    }

    public Card spawnCard(String name) {
        return cardFactory.get(name).copy();
    }
}
