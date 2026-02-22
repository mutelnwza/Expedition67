package com.Expedition67.card;

import com.Expedition67.unit.*;

public class Card {

    public enum CardTier {
        NORMAL, RARE, DEBUFF
    }

    private CardName name;
    private int baseAP;
    private int apCost;
    private boolean isPermanant;
    private int defaultUsesAmount;
    private int usesLeft;
    private CardAbility ability;
    private boolean locked = false;
    private CardTier cardTier;
    private String description;

    public Card(CardName name, int apCost, boolean isPermanant, int defaultUsesAmount, CardAbility ability, CardTier cardTier, String description) {
        this.name = name;
        this.baseAP = apCost;
        this.apCost = apCost; //for crad
        this.isPermanant = isPermanant;
        this.defaultUsesAmount = defaultUsesAmount;
        this.usesLeft = defaultUsesAmount;
        this.ability = ability;
        this.cardTier = cardTier;
        this.description = description;
    }

    public Card(Card c) {
        this.name = c.name;
        this.baseAP = apCost;
        this.apCost = c.apCost;
        this.isPermanant = c.isPermanant;
        this.defaultUsesAmount = c.defaultUsesAmount;
        this.usesLeft = c.usesLeft;
        this.ability = c.ability;
        this.cardTier = c.cardTier;
        this.description = c.description;
    }

    public void addCost(int cost) {
        this.apCost += cost;
    }

    public void use(Unit src, Unit target) {
        target.getBrain().applyCard(ability, src);
    }

    public void use(PlayerBrain playerBrain, Unit target) {
        target.getBrain().applyCard(ability, playerBrain.getOwner());
    }

    public void setLocked(boolean state) {
        this.locked = state;
    }

    public boolean canUse(UnitBrain ub) {
        PlayerBrain pb = (PlayerBrain) ub;
        return (!isPermanant && usesLeft > 0) && pb.getAP() > apCost;
    }

    public boolean isLocked() {
        return locked;
    }

    public CardName getName() {
        return this.name;
    }

    public int getAP() {
        return this.apCost;
    }

    public void setAP(int newAP){
        apCost = newAP;
    }

    public void resetAP(){
        apCost = baseAP;
    }

    public boolean getPerm() {
        return this.isPermanant;
    }

    public int getDefaultUsesAmount() {
        return this.defaultUsesAmount;
    }

    public int getUsesLeft() {
        return this.usesLeft;
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
        if (this.isPermanant) sb.append(" (Permanent)\n");
        else sb.append(" (").append(this.usesLeft).append(" uses)\n");
        sb.append("AP: ").append(this.apCost).append("\n");
        sb.append(this.description);
        return sb.toString();
    }
}
