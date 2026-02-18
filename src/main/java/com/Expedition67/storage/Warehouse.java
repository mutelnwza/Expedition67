package com.Expedition67.storage;

import com.Expedition67.card.*;
import com.Expedition67.unit.Enemy.CryingSlimeBrain;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Enemy.LukchinBrain;
import com.Expedition67.unit.Enemy.VisionBrain;
import com.Expedition67.unit.PlayerBrain;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitStats;
import com.Expedition67.unit.UnitType;
import java.util.*;

public class Warehouse {

    private static Warehouse instance;
    private Unit player;
    private HashMap<String, EnemyData> unitFactory = new HashMap<>();
    private HashMap<String, Card> cardFactory = new HashMap<>();

    private Warehouse() {
        loadPlayer();
        loadEnemy();
        loadCard();
    }

    public static Warehouse Instance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }
    private void loadPlayer() {
        player = new Unit("Player", new UnitStats(100,1,0), new PlayerBrain(),UnitType.PLAYER, 50,50,500,500);
        player.getAnimator().addAnimation("idle", 0, 5, 1);
    }

    private void loadEnemy() {
        //* CRYING SLIME */
        EnemyData cryingSlime = new EnemyData(new Enemy("CryingSlime",
                new UnitStats(75, 1, 0), new CryingSlimeBrain(),
                UnitType.ENEMY, 0, 0, 200, 200));
        cryingSlime.getUnit().getAnimator().addAnimation("idle", 0, 5, 2);
        cryingSlime.addActions("ATTACK", new DamageAbility(8, CardAbility.CardType.ATK));
        cryingSlime.addActions("DEF", new ShieldAbility(6,CardAbility.CardType.DEF));
        unitFactory.put("CryingSlime", cryingSlime);
        
        /* LUKCHIN */
        EnemyData lukchin = new EnemyData(new Enemy("Lukchin",
                new UnitStats(67, 1, 0), new LukchinBrain(),
                UnitType.ENEMY, 0, 0, 200, 200));
        lukchin.getUnit().getAnimator().addAnimation("idle", 0, 5, 2);
        lukchin.addActions("ATTACK", new DamageAbility(8, CardAbility.CardType.ATK));
        unitFactory.put("Lukchin", lukchin);

        /*VISION */
        EnemyData vision = new EnemyData(new Enemy("Vision", new UnitStats(150, 1, 0),
                new VisionBrain(), UnitType.MINIBOSS, 0, 0, 200, 200));
        vision.getUnit().getAnimator().addAnimation("idle", 0, 5, 2);
        vision.addActions("HEAL", new HealAbility(10,18,CardAbility.CardType.HEAL));
        unitFactory.put("Vision", vision);
    }

    private void loadCard() {

        //Attack
        cardFactory.put("Soul Flicker", new Card("Soul Flicker", 0, true, -1, new DamageAbility(6, CardAbility.CardType.ATK)));
        cardFactory.put("Remnant Hit", new Card("Remnant Hit", 1, true, -1, new DamageAbility(12, CardAbility.CardType.ATK)));
        cardFactory.put("Echoing Strike", new Card("Echoing Strike", 2, true, -1, new DamageAbility(30, CardAbility.CardType.ATK)));
        cardFactory.put("Void Dragon", new Card("Void Dragon", 4, true, -1, new DamageAbility(45, CardAbility.CardType.ATK)));


        //Defense
        cardFactory.put("Spectral Veil", new Card("Spectral Veil", 1, true, -1, new ShieldAbility(10,CardAbility.CardType.DEF)));
        // cardFactory.put("Soul Aegis", new Card("Soul Aegis", 2, true, -1, new ShieldHealAbility(20, 10,CardAbility.CardType.DEF)));
        cardFactory.put("Celestial Singularity", new Card("Celestial Singularity", 3, true, -1, new ShieldAbility(35,CardAbility.CardType.DEF)));
        cardFactory.put("Event Horizon", new Card("Event Horizon", 4, true, -1, new ShieldAbility(70,CardAbility.CardType.DEF)));

        //Buff
        // cardFactory.put("Soul Resonance", new Card("Soul Resonance", 1, true, -1, new BuffAbility(2)));
        // cardFactory.put("Harmonic Resonance", new Card("Harmonic Resonance", 2, true, -1, new BuffAbility(4)));
        cardFactory.put("Sovereign's Overdrive", new Card("Sovereign’s Overdrive", 0, false, 2, new OverdriveAbility(1,5,1,CardAbility.CardType.BUFF)));

        //Heal
        cardFactory.put("Ethereal Restoration", new Card("Ethereal Restoration", 2, true, -1, new HealAbility(8,CardAbility.CardType.HEAL)));
        cardFactory.put("Eternal Soul Rebirth", new Card("Eternal Soul Rebirth", 3, true, -1, new HealAbility(18,CardAbility.CardType.HEAL)));
    }

    public Unit spawnPlayer(int x, int y) {
        Unit clone = player.copy(x, y);
        clone.getAnimator().play("idle");
        return clone;
    }

    public Enemy spawnEnemy(String name, int x, int y) {
        Enemy master = (Enemy) unitFactory.get(name).getUnit();
        if (master != null) {
            Enemy clone = master.copy(x, y);
            clone.getAnimator().play("idle");
            return clone;
        } else return null;
    }

    public Card spawnCard(String name) {
        return cardFactory.get(name).copy();
    }

    public Enemy spawnRandomEnemy(int x, int y) {
        Random rand = new Random();
        int randIndex = rand.nextInt(unitFactory.size());

        List<EnemyData> unitList = new ArrayList<>(unitFactory.values());
        Enemy randEnemy = (Enemy) unitList.get(randIndex).getUnit();
        Enemy clone = randEnemy.copy(x, y);
        clone.getAnimator().play("idle");
        return clone;
    }

    public Card spawnRandomCard() {
        Random rand = new Random();
        int randIndex = rand.nextInt(cardFactory.size());

        List<Card> cardList = new ArrayList<>(cardFactory.values());
        Card randCard = cardList.get(randIndex);
        return randCard.copy();
    }

    public CardAbility spawnAction(String enemyName, String actionName) {
        EnemyData master = unitFactory.get(enemyName);
        if (master != null) {
            return master.getAbility(actionName);
        }
        System.out.println("NULL");
        return null;
    }
}
