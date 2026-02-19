package com.Expedition67.unit.Enemy;

import com.Expedition67.card.Card;
import com.Expedition67.core.CombatManager;
import com.Expedition67.core.GameManager;
import com.Expedition67.storage.Warehouse;
import com.Expedition67.unit.UnitBrain;
import java.util.ArrayList;

public class SonAndDadBrain extends EnemyBrain {

    boolean isDad = false;
    ArrayList<Card> stolenCards = new ArrayList<>();

    @Override
    public void calculateNextMove() {
        target = GameManager.Instance().getPlayer();
        if (isDad) {
            calculateDadAction();
        } else {
            nextAction = Warehouse.Instance().spawnAction(owner.getName(), "SONATTACK");
        }
    }

    @Override
    public void onTurnStarted() {
        super.onTurnStarted();
        if(!isDad){
            Card c = CombatManager.Instance().getDeck().getRandomCardFromHand();
            CombatManager.Instance().getDeck().removeFromHand(c);
            stolenCards.add(c);
        }
    }

    @Override
    public float takeDamage(float amount) {
        float d = super.takeDamage(amount);
        if (!isDad) {
            if (owner.getUnitStats().getHp() <= 67) {
                spawnDad(false);
            } else if (owner.getUnitStats().getHp() <= 0) {
                spawnDad(true);
            }
        }
        return d;
    }

    @Override
    public void onPlayerUseCard(Card c) {
        if (CombatManager.Instance().getTurnCount() >= 5 && isDad) {
            GameManager.Instance().getPlayer().takeDamage(2);
        }
    }

    private void spawnDad(boolean isRage) {
        //add animation later

        isDad = true;
        owner.getUnitStats().setMaxHp(220);
        heal(500);
        owner.getAnimator().play("dadidle");
        if (isRage) {
            addStr(5);
        }

        CombatManager.Instance().executeTurn();
        Warehouse.Instance().spawnAction(owner.getName(), "NERF").apply(target);
        returnCard();
    }

    private void returnCard() {
        for (Card c : stolenCards) {
            CombatManager.Instance().getDeck().addCard(c);
        }
        stolenCards.clear();
    }

    private void calculateDadAction() {
        if (CombatManager.Instance().getTurnCount() == 5) {
            nextAction = Warehouse.Instance().spawnAction(owner.getName(), "POISON");
        } else {
            int choice = (int)(Math.random() * (2)) + 1;
            nextAction = switch (choice) {
                case 1 -> Warehouse.Instance().spawnAction(owner.getName(), "DADATTACK");
                default -> Warehouse.Instance().spawnAction(owner.getName(), "DADDEFENSE");
            };
        }
    }

    @Override
    public UnitBrain copy() {
        return new SonAndDadBrain();
    }

}
