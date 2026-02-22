package com.Expedition67.core.util;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.card.CardName;
import com.Expedition67.core.GameManager;
import com.Expedition67.core.GameStateManager;
import com.Expedition67.core.combat.CombatManager;
import com.Expedition67.states.CardDropState;
import com.Expedition67.states.CombatState;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.enemy.Enemy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameRandom {

    private static GameRandom instance;
    private final Random random;

    private GameRandom() {
        random = new Random();
    }

    public static GameRandom Instance() {
        if (instance == null) {
            instance = new GameRandom();
        }
        return instance;
    }

    private <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(random.nextInt(list.size()));
    }

    public ArrayList<Enemy> getRandomEnemies(int centerX, int y) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        int minibossChance = 20;
        if(GameManager.Instance().getGameData().getRoom()>1){
            minibossChance = 40;
        }
        else if(GameManager.Instance().getGameData().getRoom()>3){
            minibossChance = 70;
        }
        if (random.nextInt(100) < minibossChance) { // 15% chance for a miniboss
            spawnMiniboss(enemies, centerX, y);
        } else {
            spawnNormalEnemies(enemies, centerX, y);
        }

        return enemies;
    }

    private void spawnMiniboss(ArrayList<Enemy> enemies, int centerX, int y) {
        Enemy randomMiniBoss = getRandomElement(Warehouse.Instance().getMinibosses());
        if (randomMiniBoss != null) {
            int enemyX = centerX - randomMiniBoss.getWidth() / 2;
            enemies.add(randomMiniBoss.copy(enemyX, y));
            enemies.getLast().getAnimator().play("idle");
        }
    }

    private void spawnNormalEnemies(ArrayList<Enemy> enemies, int centerX, int y) {
        Enemy randomNormalEnemy = getRandomElement(Warehouse.Instance().getNormalEnemies());
        if (randomNormalEnemy != null) {
            int amount = random.nextInt(3) + 1; // Spawn 1 to 3 enemies
            int enemyWidth = randomNormalEnemy.getWidth();
            int spacing = 20;
            int totalGroupWidth = (amount * enemyWidth) + ((amount - 1) * spacing);
            int startX = centerX - totalGroupWidth / 2;

            for (int i = 0; i < amount; i++) {
                int currentX = startX + i * (enemyWidth + spacing);
                enemies.add(randomNormalEnemy.copy(currentX, y));
                enemies.getLast().getAnimator().play("idle");
            }
        }
    }

    public Card getRandomCard(Card.CardTier tier) {
        List<Card> cards;

        if (tier == null)
            cards = Warehouse.Instance().getCards().stream()
                    .filter(c -> !c.getName().equals(CardName.VOID))
                    .toList();
        else
            cards = Warehouse.Instance().getCards().stream()
                    .filter(c -> !c.getName().equals(CardName.VOID))
                    .filter(c -> c.getTier() == tier)
                    .toList();

        return getRandomElement(cards).copy();
    }

    public Card getRandomCardFromHand(CardAbility.CardType cardType) {
        List<Card> handCards;

        if (cardType == null)
            handCards = CombatManager.Instance().getDeck().getHand();
        else
            handCards = CombatManager.Instance().getDeck().getHand().stream()
                    .filter(c -> c.getAbility().getCardType() == cardType)
                    .toList();

        return getRandomElement(handCards);
    }

    public void enterRandomRoom() {
        if (random.nextInt(100) < 15) { // 15% chance for a treasure room
            GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.CARD_DROP_STATE, CardDropState.TREASURE_ROOM);
        } else {
            GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.COMBAT_STATE, CombatState.MONSTER_ROOM);
        }
    }
}
