package com.Expedition67.core;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.states.CardDropState;
import com.Expedition67.states.ResultState;
import com.Expedition67.unit.Deck;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.unit.Enemy.EnemyBrain;
import com.Expedition67.unit.PlayerBrain;
import com.Expedition67.unit.Unit;
<<<<<<< Updated upstream
import com.Expedition67.unit.UnitType;

=======
>>>>>>> Stashed changes
import java.util.List;

public class CombatManager {

    private static CombatManager instance;

    private Unit player;
    private List<Enemy> enemies;
    private UnitType enemyType;
    private Deck deck;

    private Enemy target;
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
        this.enemyType = enemies.getFirst().getType();
        this.turnCount = 0;
        this.cardUsedCount = 0;

        this.target = enemies.getFirst();
        if (deck == null) {
            deck = new Deck();
        }
        deck.instantiate();

        isCombatActive = true;
        isPlayerTurn = true;

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

            currentEnemyActionIndex = 0;
            actionTimer = ACTION_DELAY;
        }
    }

    private void startPlayerTurn() {
        clearActionString();
        isPlayerTurn = true;
        deck.addToHand();
        if (player != null && player.getUnitStats().getHp() > 0) {
            turnCount++;
            cardUsedCount = 0;

            player.getBrain().onTurnStarted();

            for (Enemy e : enemies) {
                ((EnemyBrain) e.getBrain()).calculateNextMove();
            }
        }
    }

    public void onPlayerUseCard(Card card, Unit target) {
        Unit player = GameManager.Instance().getPlayer();
        PlayerBrain pb = (PlayerBrain) player.getBrain();

        if (card == null || target == null) return;
        if (pb.getAP() < card.getAP()) {
            actionString = "Not enough AP";
            return;
        }
        actionString = player.getName().toString();
        CardAbility.CardType cardType = card.getAbility().getCardType();
        if (cardType == CardAbility.CardType.ATK || cardType == CardAbility.CardType.DEBUFF) card.use(pb, target);
        else card.use(pb, player);
        //card.getAbility().apply(target, player);

        for (Enemy e : enemies) {
            EnemyBrain eb = (EnemyBrain) e.getBrain();
            eb.onPlayerUseCard(card);
        }
        pb.onUseCard(card);
        deck.useCard(card);
        deck.updateFreeCard();

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
                        actionString = currentEnemy.getName().toString();
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
            if (enemyType == UnitType.ENEMY)
                GameManager.Instance().setCurrentState(GameManager.CARD_DROP_STATE, CardDropState.NORMAL_DROP);
            else if (enemyType == UnitType.MINIBOSS)
                GameManager.Instance().setCurrentState(GameManager.CARD_DROP_STATE, CardDropState.MINIBOSS_DROP);
            else
                GameManager.Instance().setCurrentState(GameManager.RESULT_STATE, ResultState.WIN);
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

    public String getActionString() {
        return this.actionString;
    }

    public void addActionString(String action) {
        actionString += action;
    }

    public void clearActionString() {
        actionString = "";
    }

    // Temp
    public void clearEnemies() {
        enemies.clear();
    }
}
