package com.Expedition67.core.combat;

import com.Expedition67.card.Card;
import com.Expedition67.card.CardAbility;
import com.Expedition67.core.GameManager;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.enemy.Enemy;
import com.Expedition67.unit.enemy.EnemyBrain;
import com.Expedition67.unit.player.Deck;
import com.Expedition67.unit.player.PlayerBrain;

import java.util.List;

/**
 * Handles the logic for a player using a card during combat.
 */
public class PlayerActionHandler {

    /**
     * Processes a player's card usage, including validation and execution.
     *
     * @param card   The card the player is using.
     * @param target The intended target of the card.
     */
    public void handlePlayerAction(Card card, Unit target) {
        PlayerBrain pb = GameManager.Instance().getPlayerBrain();
        Unit player = GameManager.Instance().getPlayer();
        List<Enemy> enemies = CombatManager.Instance().getEnemies();
        Deck deck = CombatManager.Instance().getDeck();

        if (!isActionValid(card, target, pb)) {
            return;
        }

        CombatManager.Instance().setActionString(player.getName().toString());
        deck.updateFreeCard(false);

        if (card.getAbility().getCardType() == CardAbility.CardType.ATK) {
            card.use(pb, target);
        } else {
            card.use(pb, player);
        }

        for (Enemy e : enemies) {
            ((EnemyBrain) e.getBrain()).onPlayerUseCard(card);
        }
        pb.onUseCard(card);
        deck.useCard(card);
    }

    /**
     * Validates if the player's action is allowed.
     *
     * @param card   The card being used.
     * @param target The target of the card.
     * @param pb     The player's brain.
     * @return true if the action is valid, false otherwise.
     */
    private boolean isActionValid(Card card, Unit target, PlayerBrain pb) {
        if (card == null) {
            CombatManager.Instance().setActionString("No card selected!");
            return false;
        }
        if (card.getAbility().getCardType() == CardAbility.CardType.ATK && target == null) {
            CombatManager.Instance().setActionString("No target selected!");
            return false;
        }
        if (card.isLocked()) {
            CombatManager.Instance().setActionString("Card is locked!");
            return false;
        }
        if (pb.getAP() < card.getAP()) {
            CombatManager.Instance().setActionString("Not enough AP!");
            return false;
        }
        return true;
    }
}
