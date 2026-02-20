package com.Expedition67.core;

import com.Expedition67.card.Card;
import com.Expedition67.states.CardDropState;
import com.Expedition67.states.ResultState;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Enemy.EnemyBrain;
import com.Expedition67.unit.PlayerBrain;
import com.Expedition67.unit.Unit;

import java.util.List;

public class CombatManager {

    private static CombatManager instance;

    private Unit player;
    private List<Enemy> enemies;
    private Enemy target;
    private Deck deck;
    private String actionString = "";

    private boolean isPlayerTurn = true;
    private boolean isCombatActive;

    private int turnCount;
    private int cardUsedCount;

    private int actionTimer = 0;
    private final int ACTION_DELAY = 50;
    private int currentEnemyActionIndex = 0;

    private CombatManager() {
        isCombatActive = false;
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
        this.target = enemies.getFirst();
        this.turnCount = 0;
        this.cardUsedCount = 0;

        if (deck == null) {
            deck = new Deck();
        }
        deck.instantiate();
        // deck.addToHand();

        isCombatActive = true;
        isPlayerTurn = true;

        // player.getBrain().onTurnStarted();
        for (Enemy e : enemies) {
            ((EnemyBrain) e.getBrain()).calculateNextMove();
        }
        startPlayerTurn();
    }

    public void executeTurn() {
        if (!isCombatActive || player == null || enemies == null) {
            return;
        }

        if (isPlayerTurn) {
            player.getBrain().onTurnEnded();
            isPlayerTurn = false;
            //
            player.getBrain().onTurnEnded();
                isPlayerTurn = false;

            currentEnemyActionIndex = 0;
            actionTimer = ACTION_DELAY;
        }
    }

    private void startPlayerTurn() {
        clearActionString();
        isPlayerTurn = true;
        if (player != null && player.getUnitStats().getHp() > 0) {
            turnCount++;
            cardUsedCount = 0;
            deck.addToHand();
            player.getBrain().onTurnStarted();

            for (Enemy e : enemies) {
                ((EnemyBrain) e.getBrain()).calculateNextMove();
            }
        }
    }

    public void onPlayerUseCard(Card card, Unit target) {
        Unit player = GameManager.Instance().getPlayer();
        PlayerBrain pb = (PlayerBrain) player.getBrain();

        if (card == null || target == null || pb.getAP() < card.getAP()) {
            return;
        }
        actionString = player.getName();
        card.use(pb, target);
        //card.getAbility().apply(target, player);

        for (Enemy e : enemies) {
            EnemyBrain eb = (EnemyBrain) e.getBrain();
            eb.onPlayerUseCard(card);
        }
        pb.onUseCard(card.getAP());
        deck.useCard(card);

        cardUsedCount++;
    }

    public void update() {
        if (!isCombatActive) return;
        checkWinCondition();

        if (!isPlayerTurn) {
            actionTimer--;

            if (actionTimer <= 0) {
                if (currentEnemyActionIndex < enemies.size()) {
                    Enemy currentEnemy = enemies.get(currentEnemyActionIndex);

                    if (currentEnemy.getUnitStats().getHp() > 0) {
                        EnemyBrain eb = (EnemyBrain) currentEnemy.getBrain();
                        eb.onTurnStarted();
                        actionString = currentEnemy.getName();
                        eb.getNextAction().apply(eb.getTarget());
                        eb.onTurnEnded();
                    }

                    currentEnemyActionIndex++;
                    actionTimer = ACTION_DELAY;
                } else
                    startPlayerTurn();
            }
        }
    }

    private void checkWinCondition() {
        if (player.getUnitStats().getHp() <= 0) {
            isCombatActive = false;
            GameManager.Instance().setCurrentState(GameManager.RESULT_STATE, ResultState.LOSE);
            return;
        }

        int i = 0;
        while (i < enemies.size()) {
            if (enemies.get(i).getUnitStats().getHp() <= 0) {
                enemies.remove(i);
                target = null;
            } else {
                i++;
            }
        }

        if (enemies.isEmpty()) {
            isCombatActive = false;
            GameManager.Instance().setCurrentState(GameManager.CARD_DROP_STATE, CardDropState.MONSTER_DROP);
        }
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
        return turnCount;
    }
    public String getActionString(){
        return this.actionString;
    }
    public void addActionString(String action){
        actionString += action;
    }
    public void clearActionString() {
    actionString = "";
}
}
