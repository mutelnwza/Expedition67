package com.Expedition67.core.combat;

import com.Expedition67.card.Card;
import com.Expedition67.core.GameManager;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitType;
import com.Expedition67.unit.enemy.Enemy;
import com.Expedition67.unit.player.Deck;
import java.util.List;

public class CombatManager {

    private static CombatManager instance;

    private Unit player;
    private List<Enemy> enemies;
    private UnitType enemyType;
    private Deck deck;
    private final TurnManager turnManager;
    private final PlayerActionHandler playerActionHandler;
    private final CombatCompletionHandler combatCompletionHandler;

    private Enemy target;
    private String actionString = "";
    private boolean isCombatActive;

    private CombatManager() {
        turnManager = new TurnManager();
        playerActionHandler = new PlayerActionHandler();
        combatCompletionHandler = new CombatCompletionHandler();
    }

    public static CombatManager Instance() {
        if (instance == null) {
            instance = new CombatManager();
        }
        return instance;
    }

    public static void initNew() {
        instance = new CombatManager();
    }

    public void startCombat(List<Enemy> enemies) {
        this.player = GameManager.Instance().getPlayer();
        this.enemies = enemies;
        this.enemyType = enemies.getFirst().getType();
        this.target = enemies.getFirst();

        if (deck == null) {
            deck = new Deck();
        }
        deck.instantiate();
        deck.addToHand();
        turnManager.reset();
        isCombatActive = true;

        turnManager.startPlayerTurn(player, enemies, deck);
    }

    public void endCombat() {
        isCombatActive = false;
        player.getBrain().onTurnEnded();
        player.getBrain().getBuffManager().resetBuffs();
    }

    public void executeTurn() {
        if (!isCombatActive || player == null || enemies == null) {
            return;
        }
        turnManager.endPlayerTurn(player,deck);
    }

    public void onPlayerUseCard(Card card, Unit target) {
        playerActionHandler.handlePlayerAction(card, target);
    }

    public void update() {
        if (!isCombatActive) return;
        if (combatCompletionHandler.checkWinCondition()) {
            return;
        }
        turnManager.updateEnemyTurns(player, enemies, deck);
    }

    public Enemy getTarget() {
        return target;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public Deck getDeck() {
        return deck;
    }

    public int getTurnCount() {
        return turnManager.getTurnCount();
    }

    public String getActionString() {
        return this.actionString;
    }

    public void addActionString(String action) {
        actionString += action;
    }

    public void clearActionString() {
        actionString = "";
    }

    public void clearEnemies() {
        enemies.clear();
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public UnitType getEnemyType() {
        return enemyType;
    }

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }
}
