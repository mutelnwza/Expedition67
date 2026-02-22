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

public class PlayerActionHandler {

    public void handlePlayerAction(Card card, Unit target) {
        PlayerBrain pb = GameManager.Instance().getPlayerBrain();
        Unit player = GameManager.Instance().getPlayer();
        List<Enemy> enemies = CombatManager.Instance().getEnemies();
        Deck deck = CombatManager.Instance().getDeck();

        if (card == null)
            CombatManager.Instance().setActionString("No card selected!");
        else if (card.getAbility().getCardType() == CardAbility.CardType.ATK && target == null)
            CombatManager.Instance().setActionString("No target selected!");
        else if (card.isLocked())
            CombatManager.Instance().setActionString("Card is locked!");
        else if (pb.getAP() < card.getAP())
            CombatManager.Instance().setActionString("Not enough AP!");
        else {
            CombatManager.Instance().setActionString(player.getName().toString());
            CardAbility.CardType cardType = card.getAbility().getCardType();
            if (cardType == CardAbility.CardType.ATK) {
                card.use(pb, target);
            } else {
                card.use(pb, player);
            }

            for (Enemy e : enemies) {
                EnemyBrain eb = (EnemyBrain) e.getBrain();
                eb.onPlayerUseCard(card);
            }
            pb.onUseCard(card);
            deck.useCard(card);
            deck.updateFreeCard(false);
        }
    }
}
