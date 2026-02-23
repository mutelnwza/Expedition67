package com.Expedition67.core.combat;

import com.Expedition67.card.Card;
import com.Expedition67.core.GameManager;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitType;
import com.Expedition67.unit.enemy.Enemy;
import com.Expedition67.unit.player.Deck;

import java.util.List;

/**
 * The central manager for all combat-related logic.
 */
public class CombatManager {

    private static CombatManager instance;

    private Unit player;
    private List<Enemy> enemies;
    private UnitType enemyType;
    private Deck deck;
    private Enemy target;
    private String actionString = "";
    private boolean isCombatActive;

    private final TurnManager turnManager;
    private final PlayerActionHandler playerActionHandler;
    private final CombatCompletionHandler combatCompletionHandler;

    private CombatManager() {
        this.turnManager = new TurnManager();
        this.playerActionHandler = new PlayerActionHandler();
        this.combatCompletionHandler = new CombatCompletionHandler();
    }

    /**
     * Gets the single instance of the CombatManager.
     *
     * @return The single instance of CombatManager.
     */
    public static CombatManager Instance() {
        if (instance == null) {
            instance = new CombatManager();
        }
        return instance;
    }

    /**
     * Re-initializes the singleton instance for a new combat.
     */
    public static void initNew() {
        instance = new CombatManager();
    }

    /**
     * Sets up and starts a new combat encounter.
     *
     * @param enemies The list of enemies for this fight.
     */
    public void startCombat(List<Enemy> enemies) {
        this.player = GameManager.Instance().getPlayer();
        this.enemies = enemies;
        this.enemyType = enemies.getFirst().getType();
        this.target = null;

        if (this.deck == null) {
            this.deck = new Deck();
        }
        this.deck.instantiate();
        this.deck.addToHand();

        this.turnManager.reset();
        this.isCombatActive = true;
        this.turnManager.startPlayerTurn(player, enemies);
    }

    /**
     * Ends the current combat and performs cleanup.
     */
    public void endCombat() {
        this.isCombatActive = false;
        player.getBrain().onTurnEnded();
        player.getBrain().getBuffManager().resetBuffs();
    }

    /**
     * The main update loop for combat.
     */
    public void update() {
        if (!isCombatActive) return;

        if (combatCompletionHandler.checkWinCondition()) {
            return;
        }

        turnManager.updateEnemyTurns(player, enemies);
    }

    /**
     * Executes the end of the player's turn.
     */
    public void executeTurn() {
        if (!isCombatActive || player == null || enemies == null) {
            return;
        }
        turnManager.endPlayerTurn(player, deck);
    }

    /**
     * Handles the player using a card.
     *
     * @param card   The card being used.
     * @param target The target of the card.
     */
    public void onPlayerUseCard(Card card, Unit target) {
        playerActionHandler.handlePlayerAction(card, target);
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
        this.actionString += action;
    }

    public void clearActionString() {
        this.actionString = "";
    }

    public void setActionString(String actionString) {
        this.actionString = actionString;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public UnitType getEnemyType() {
        return enemyType;
    }
}
