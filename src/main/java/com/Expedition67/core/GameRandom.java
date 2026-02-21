package com.Expedition67.core;

import com.Expedition67.states.CardDropState;
import com.Expedition67.states.CombatState;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.Enemy.Enemy;
import com.Expedition67.card.Card;

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
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    public ArrayList<Enemy> getRandomEnemies(int centerX, int y) {
        ArrayList<Enemy> enemies = new ArrayList<>();

        // 15% chance to spawn a miniboss
        if (random.nextInt(100) < 15) {
            List<Enemy> miniBosses = Warehouse.Instance().getMinibosses();
            Enemy randomMiniBoss = getRandomElement(miniBosses);

            if (randomMiniBoss != null) {
                int enemyX = centerX - randomMiniBoss.getWidth() / 2;
                enemies.add(randomMiniBoss.copy(enemyX, y));
                enemies.getLast().getAnimator().play("idle");
            }
        } else {
            List<Enemy> normalEnemies = Warehouse.Instance().getNormalEnemies();
            Enemy randomNormalEnemy = getRandomElement(normalEnemies);

            if (randomNormalEnemy != null) {
                int amount;

                int randomNum = random.nextInt(10);
                if (randomNum < 5) amount = 1;
                else if (randomNum < 8) amount = 2;
                else amount = 3;

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

        return enemies;
    }

    public Card getRandomCard(Card.CardTier tier) {
        List<Card> cards;

        if (tier == null) cards = Warehouse.Instance().getCards();
        else cards = Warehouse.Instance().getCards().stream().filter(c -> c.getTier() == tier).toList();

        Card randomCard = getRandomElement(cards);
        return randomCard.copy();
    }

    public void enterRandomRoom() {
        // 15% chance to find a Treasure Room
        if (random.nextInt(100) < 15)
            GameManager.Instance().setCurrentState(GameManager.CARD_DROP_STATE, CardDropState.TREASURE_ROOM);
        else
            GameManager.Instance().setCurrentState(GameManager.COMBAT_STATE, CombatState.MONSTER_ROOM);
    }
}
