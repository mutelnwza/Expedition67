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

/**
 * A utility class for handling all random events in the game.
 */
public class GameRandom {

    private static GameRandom instance;
    private final Random random;

    private GameRandom() {
        this.random = new Random();
    }

    /**
     * Gets the single instance of the GameRandom utility.
     *
     * @return The single instance of GameRandom.
     */
    public static GameRandom Instance() {
        if (instance == null) {
            instance = new GameRandom();
        }
        return instance;
    }

    /**
     * Generates a list of enemies for a combat encounter.
     *
     * @param centerX The center x-coordinate for positioning the enemies.
     * @param y       The y-coordinate for the enemies.
     * @return An ArrayList of Enemy objects.
     */
    public ArrayList<Enemy> getRandomEnemies(int centerX, int y) {
        ArrayList<Enemy> enemies = new ArrayList<>();
        int room = GameManager.Instance().getGameData().getRoom();
        int minibossChance = 20;

        if (room > 3) {
            minibossChance = 70;
        } else if (room > 1) {
            minibossChance = 40;
        }

        if (random.nextInt(100) < minibossChance) {
            spawnMiniboss(enemies, centerX, y);
        } else {
            spawnNormalEnemies(enemies, centerX, y);
        }
        return enemies;
    }

    /**
     * Gets a random card from the warehouse.
     *
     * @param isTreasure     True if this method called from treasure room. Otherwise, false.
     * @param rareDropChance The chance to get rare card. Don't use if isTreasure.
     */
    public Card getRandomCard(boolean isTreasure, int rareDropChance) {
        List<Card> allCards = Warehouse.Instance().getCards();
        List<Card> cards;

        if (isTreasure) {
            cards = allCards.stream()
                    .filter(c -> c.getName() != CardName.VOID)
                    .toList();
        } else {
            if (random.nextInt(100) < rareDropChance) {
                cards = allCards.stream()
                        .filter(c -> c.getName() != CardName.VOID && c.getTier() == Card.CardTier.RARE)
                        .toList();
            } else {
                cards = allCards.stream()
                        .filter(c -> c.getName() != CardName.VOID && c.getTier() == Card.CardTier.NORMAL)
                        .toList();
            }
        }

        Card randomCard = getRandomElement(cards);
        return (randomCard != null) ? randomCard.copy() : null;
    }

    /**
     * Gets a random card from the player's current hand.
     *
     * @param cardType The desired type of card (ATK, DEF, etc.). If null, any card is considered.
     * @return A card from the hand, or null if no matching card is found.
     */
    public Card getRandomCardFromHand(CardAbility.CardType cardType) {
        List<Card> hand = CombatManager.Instance().getDeck().getHand();
        if (hand == null || hand.isEmpty()) {
            return null;
        }

        List<Card> filteredHand;
        if (cardType == null) {
            filteredHand = hand;
        } else {
            filteredHand = hand.stream()
                    .filter(c -> c.getAbility().getCardType() == cardType)
                    .toList();
        }

        return getRandomElement(filteredHand);
    }

    /**
     * Determines the type of the next room and transitions the game state accordingly.
     */
    public void enterRandomRoom() {
        if (random.nextInt(100) < 15) {
            GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.CARD_DROP_STATE, CardDropState.TREASURE_ROOM);
        } else {
            GameManager.Instance().getGameStateManager().setCurrentState(GameStateManager.COMBAT_STATE, CombatState.MONSTER_ROOM);
        }
    }

    private <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(random.nextInt(list.size()));
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
            int amount = random.nextInt(1, 4);
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
}
