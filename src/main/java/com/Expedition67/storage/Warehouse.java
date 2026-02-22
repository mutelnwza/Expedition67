package com.Expedition67.storage;

import com.Expedition67.card.*;
import com.Expedition67.card.attack.AttackAndDiscardAbility;
import com.Expedition67.card.attack.AttackAndStealHPAbility;
import com.Expedition67.card.attack.DamageAbility;
import com.Expedition67.card.attack.MultiAttackAbility;
import com.Expedition67.card.attack.PoisonAbility;
import com.Expedition67.card.attack.StackAttackAbility;
import com.Expedition67.card.attack.VoidAttackAbility;
import com.Expedition67.card.attack.AoEDamageAbility;
import com.Expedition67.card.attack.VoidDragonAbility;
import com.Expedition67.card.buff.*;
import com.Expedition67.card.debuff.CardModifyAbility;
import com.Expedition67.card.debuff.LockCardAbility;
import com.Expedition67.card.debuff.NerfStatsAbility;
import com.Expedition67.card.defense.*;
import com.Expedition67.card.heal.*;
import com.Expedition67.card.special.*;
import com.Expedition67.unit.*;
import com.Expedition67.unit.Enemy.BigBadBossBrain;
import com.Expedition67.unit.Enemy.CryingSlimeBrain;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Enemy.LukchinBrain;
import com.Expedition67.unit.Enemy.RedEyeBrain;
import com.Expedition67.unit.Enemy.SonAndDadBrain;
import com.Expedition67.unit.Enemy.TillyTheBirdBrain;
import com.Expedition67.unit.Enemy.VisionBrain;

import java.util.*;

public class Warehouse {

    private static Warehouse instance;
    private Unit player;
    private HashMap<UnitName, EnemyData> unitFactory = new HashMap<>();
    private HashMap<CardName, Card> cardFactory = new HashMap<>();

    private List<EnemyData> normalEnemies;
    private List<EnemyData> miniBosses;
    private EnemyData boss;

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
        player = new Unit(UnitName.PLAYER, new UnitStats(100, 1, 0), new PlayerBrain(), UnitType.PLAYER, 50, 50, 150, 150);
        player.getAnimator().addAnimation("idle", 0, 20, 2);
    }

    private void loadEnemy() {
        //* CRYING SLIME */
        EnemyData cryingSlime = new EnemyData(new Enemy(UnitName.CRYING_SLIME,
                new UnitStats(75, 0, 0), new CryingSlimeBrain(),
                UnitType.ENEMY, 0, 0, 150, 150));
        cryingSlime.getUnit().getAnimator().addAnimation("idle", 0, 20, 2);
        cryingSlime.addActions("ATTACK", new DamageAbility(8, CardAbility.CardType.ATK));
        cryingSlime.addActions("DEF", new ShieldAbility(6, CardAbility.CardType.DEF));
        unitFactory.put(UnitName.CRYING_SLIME, cryingSlime);

        /* LUKCHIN */
        EnemyData lukchin = new EnemyData(new Enemy(UnitName.LUKCHIN,
                new UnitStats(67, 0, 0), new LukchinBrain(),
                UnitType.ENEMY, 0, 0, 150, 150));
        lukchin.getUnit().getAnimator().addAnimation("idle", 0, 20, 2);
        lukchin.addActions("ATTACK", new DamageAbility(8, CardAbility.CardType.ATK));
        unitFactory.put(UnitName.LUKCHIN, lukchin);

        /* VISION */
        EnemyData vision = new EnemyData(new Enemy(UnitName.VISION, new UnitStats(150, 0, 0),
                new VisionBrain(), UnitType.MINIBOSS, 0, 0, 150, 150));
        vision.getUnit().getAnimator().addAnimation("idle", 0, 20, 2);
        vision.addActions("HEAL", new HealAbility(10, 18, CardAbility.CardType.HEAL));
        unitFactory.put(UnitName.VISION, vision);

        /* RED EYES */
        EnemyData redEyes = new EnemyData(new Enemy(UnitName.RED_EYES, new UnitStats(180, 0, 0),
                new RedEyeBrain(), UnitType.MINIBOSS, 0, 0, 150, 150));
        redEyes.getUnit().getAnimator().addAnimation("idle", 0, 20, 2);
        redEyes.addActions("ATTACK", new DamageAbility(12, 15, CardAbility.CardType.ATK));
        unitFactory.put(UnitName.RED_EYES, redEyes);

        /* TILLY BIRD */
        EnemyData tillyTheBird = new EnemyData(new Enemy(UnitName.TILLY_THE_BIRD, new UnitStats(350, 0, 0),
                new TillyTheBirdBrain(), UnitType.MINIBOSS, 0, 0, 150, 150));
        tillyTheBird.getUnit().getAnimator().addAnimation("idle", 0, 20, 2);
        tillyTheBird.addActions("DEBUFF1", new CardModifyAbility(1, CardAbility.CardType.DEBUFF, CardAbility.CardType.ATK));
        tillyTheBird.addActions("NORMALATTACK", new DamageAbility(8, CardAbility.CardType.ATK));
        tillyTheBird.addActions("MULTIATTACK", new MultiAttackAbility(4, CardAbility.CardType.ATK, 5));
        tillyTheBird.addActions("DISCARD", new AttackAndDiscardAbility(6, CardAbility.CardType.ATK));
        tillyTheBird.addActions("HEAVYATTACK", new AttackAndStealHPAbility(24, 2, CardAbility.CardType.ATK));
        tillyTheBird.addActions("NERF", new NerfStatsAbility(-1, 3, CardAbility.CardType.DEBUFF));
        unitFactory.put(UnitName.TILLY_THE_BIRD, tillyTheBird);

        /* SON AND DAD */
        EnemyData sonAndDad = new EnemyData(new Enemy(UnitName.SON_AND_DAD, new UnitStats(100, 0, 0),
                new SonAndDadBrain(), UnitType.MINIBOSS, 0, 0, 150, 150));
        sonAndDad.getUnit().getAnimator().addAnimation("idle", 0, 20, 2);
        sonAndDad.getUnit().getAnimator().addAnimation("dad_idle", 1, 20, 2);
        sonAndDad.addActions("DADATTACK", new DamageAbility(18, CardAbility.CardType.ATK));
        sonAndDad.addActions("SONATTACK", new DamageAbility(8, CardAbility.CardType.ATK));
        sonAndDad.addActions("DADDEFENSE", new ShieldAbility(6, 15, CardAbility.CardType.DEF));
        sonAndDad.addActions("POISON", new PoisonAbility(3, 5, CardAbility.CardType.ATK));
        sonAndDad.addActions("NERF", new NerfStatsAbility(3, 4, CardAbility.CardType.DEBUFF));
        unitFactory.put(UnitName.SON_AND_DAD, sonAndDad);

        /* BIG BAD BOSS */
        boss = new EnemyData(new Enemy(UnitName.BIG_BAD_BOSS, new UnitStats(500, 0, 0),
                new BigBadBossBrain(), UnitType.BOSS, 0, 0, 150, 150));
        boss.getUnit().getAnimator().addAnimation("idle", 0, 20, 2);
        boss.addActions("ATTACK1", new VoidAttackAbility(15, CardAbility.CardType.ATK));
        boss.addActions("ATTACK2", new MultiAttackAbility(4, CardAbility.CardType.ATK, 6));
        boss.addActions("ATTACK3", new StackAttackAbility(10, 2, CardAbility.CardType.ATK));
        boss.addActions("DEFENSE", new ShieldAbility(8, 19, CardAbility.CardType.DEF));
        boss.addActions("LOCK", new LockCardAbility(1, 2, CardAbility.CardType.DEBUFF));
        boss.addActions("MIMIC", new MimicAbility(CardAbility.CardType.ATK));
        unitFactory.put(UnitName.BIG_BAD_BOSS, boss);

        normalEnemies = unitFactory.values().stream().filter(u -> u.getUnit().getType() == UnitType.ENEMY).toList();
        miniBosses = unitFactory.values().stream().filter(u -> u.getUnit().getType() == UnitType.MINIBOSS).toList();
    }

    private void loadCard() {
        // Void
        cardFactory.put(CardName.VOID, new Card(CardName.VOID, 1, true, -1, new VoidCard(CardAbility.CardType.VOID), Card.CardTier.DEBUFF, "GET VOID"));

        // Attack
        cardFactory.put(CardName.SOUL_FLICKER, new Card(CardName.SOUL_FLICKER, 0, true, -1, new DamageAbility(6, CardAbility.CardType.ATK), Card.CardTier.NORMAL, "Deal 6 damage. A faint spark of soul energy."));
        cardFactory.put(CardName.REMNANT_HIT, new Card(CardName.REMNANT_HIT, 1, true, -1, new DamageAbility(12, CardAbility.CardType.ATK), Card.CardTier.NORMAL, "Deal 12 damage. A precise strike fueled by lingering soul fragments."));
        cardFactory.put(CardName.ECHOING_STRIKE, new Card(CardName.ECHOING_STRIKE, 2, true, -1, new AoEDamageAbility(30, CardAbility.CardType.ATK), Card.CardTier.RARE, "Deal 30 damage to all enemies. A strong condensed of your spirit"));
        cardFactory.put(CardName.VOID_DRAGON, new Card(CardName.VOID_DRAGON, 4, true, -1, new VoidDragonAbility(45,80, CardAbility.CardType.ATK), Card.CardTier.RARE, "Deal 45 damage. If target's HP < 50%, deal 80 damage instead.\nAnd remove all the void cards from hand"));

        // Defense
        cardFactory.put(CardName.SPECTRAL_VEIL, new Card(CardName.SPECTRAL_VEIL, 1, true, -1, new ShieldAbility(10, CardAbility.CardType.DEF), Card.CardTier.NORMAL, "Gain 10 Block. A thin veil of shattered souls to absorb impacts."));
        cardFactory.put(CardName.SOUL_AEGIS, new Card(CardName.SOUL_AEGIS, 2, true, -1, new ShieldHealAbility(20, 10,CardAbility.CardType.DEF), Card.CardTier.NORMAL, "Gain 20 Block and heal 10 HP. Converts kinetic force into life energy."));
        cardFactory.put(CardName.CELESTIAL_SINGULARITY, new Card(CardName.CELESTIAL_SINGULARITY, 3, true, -1, new SingularityAbility(35, CardAbility.CardType.DEF), Card.CardTier.NORMAL, "Gain 35 Block. The next card played costs 0 AP."));
        cardFactory.put(CardName.EVENT_HORIZON, new Card(CardName.EVENT_HORIZON, 4, true, -1, new ShieldAbility(70, CardAbility.CardType.DEF), Card.CardTier.RARE, "Gain 70 Block. Incoming strikes vanish into the void."));

        // Buff
        cardFactory.put(CardName.SOUL_RESONANCE, new Card(CardName.SOUL_RESONANCE, 1, true, -1, new SoulResonanceAbility(6, CardAbility.CardType.BUFF), Card.CardTier.NORMAL, "Attack cards in your hand gain +6 Damage this turn."));
        cardFactory.put(CardName.HARMONIC_RESONANCE, new Card(CardName.HARMONIC_RESONANCE, 2, true, -1, new HarmonicResonanceAbility(6,CardAbility.CardType.BUFF), Card.CardTier.NORMAL, "Whenever you play an Attack card this turn, gain 6 Block."));
        cardFactory.put(CardName.SOVEREIGNS_OVERDRIVE, new Card(CardName.SOVEREIGNS_OVERDRIVE, 0, false, 2, new OverdriveAbility(1, 5, 1, CardAbility.CardType.BUFF), Card.CardTier.NORMAL, "Lose 5 HP. Your next attack has a 100% Critical Rate."));

        // Heal
        cardFactory.put(CardName.ETHEREAL_RESTORATION, new Card(CardName.ETHEREAL_RESTORATION, 2, true, -1, new HealAndRemoveDebuffAbility(15, CardAbility.CardType.HEAL), Card.CardTier.NORMAL, "Heal 15 HP and remove all debuffs."));
        cardFactory.put(CardName.ETERNAL_SOUL_REBIRTH, new Card(CardName.ETERNAL_SOUL_REBIRTH, 3, true, -1, new RebirthAbility(10,30,28 ,CardAbility.CardType.HEAL), Card.CardTier.RARE, "Heal 28 HP and gain 10 Block. If HP < 20%, gain 30 Block."));
    }

    public Unit spawnPlayer(int x, int y) {
        Unit clone = player.copy(x, y);
        clone.getAnimator().play("idle");
        return clone;
    }

    public Enemy spawnBoss(int x, int y) {
        Enemy clone = boss.getUnit().copy(x, y);
        clone.getAnimator().play("idle");
        return clone;
    }

    public Card spawnCard(CardName name) {
        return cardFactory.get(name).copy();
    }

    public CardAbility spawnAction(UnitName enemyName, String actionName) {
        EnemyData master = unitFactory.get(enemyName);
        if (master != null) {
            return master.getAbility(actionName).copy();
        }
        System.err.println("Error: Action '" + actionName + "' not found for enemy " + enemyName);
        return null;
    }

    public List<Enemy> getNormalEnemies() {
        return normalEnemies.stream().map(EnemyData::getUnit).toList();
    }

    public List<Enemy> getMinibosses() {
        return miniBosses.stream().map(EnemyData::getUnit).toList();
    }

    public List<Card> getCards() {
        return new ArrayList<>(cardFactory.values());
    }
}
