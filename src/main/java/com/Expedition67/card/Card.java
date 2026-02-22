package com.Expedition67.card;

import com.Expedition67.unit.Unit;
import com.Expedition67.unit.player.PlayerBrain;

public class Card {

    private final CardName name;
    private final int baseAP;
    private final boolean isPermanent;
    private final int defaultUsesAmount;
    private final CardAbility ability;
    private final CardTier cardTier;
    private final String description;
    private int apCost;
    private int usesLeft;
    private boolean locked = false;

    public Card(CardName name, int apCost, boolean isPermanent, int defaultUsesAmount, CardAbility ability, CardTier cardTier, String description) {
        this.name = name;
        this.baseAP = apCost;
        this.apCost = apCost;
        this.isPermanent = isPermanent;
        this.defaultUsesAmount = defaultUsesAmount;
        this.usesLeft = defaultUsesAmount;
        this.ability = ability;
        this.cardTier = cardTier;
        this.description = description;
    }

    public Card(Card c) {
        this.name = c.name;
        this.baseAP = c.apCost;
        this.apCost = c.apCost;
        this.isPermanent = c.isPermanent;
        this.defaultUsesAmount = c.defaultUsesAmount;
        this.usesLeft = c.usesLeft;
        this.ability = c.ability;
        this.cardTier = c.cardTier;
        this.description = c.description;
        this.locked = c.locked;
    }

    public void addCost(int cost) {
        this.apCost += cost;
    }

    public void use(PlayerBrain playerBrain, Unit target) {
        target.getBrain().applyCard(ability, playerBrain.getOwner());
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean state) {
        this.locked = state;
    }

    public CardName getName() {
        return this.name;
    }

    public int getAP() {
        return this.apCost;
    }

    public void setAP(int newAP) {
        apCost = newAP;
    }

    public void resetAP() {
        apCost = baseAP;
    }

    public int getDefaultUsesAmount() {
        return this.defaultUsesAmount;
    }

    public void addUsesLeft(int usesLeft) {
        this.usesLeft += usesLeft;
    }

    public CardAbility getAbility() {
        return this.ability;
    }

    public CardTier getTier() {
        return this.cardTier;
    }

    public Card copy() {
        return new Card(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(this.name);
        if (this.isPermanent) sb.append(" (Permanent)");
        else sb.append(" (").append(this.usesLeft).append(" uses)");
        if (this.locked) sb.append(" (Locked)");
        sb.append("\nAP: ").append(this.apCost).append("\n");
        sb.append(this.description);
        return sb.toString();
    }

    public enum CardTier {
        NORMAL, RARE, DEBUFF
    }
}
