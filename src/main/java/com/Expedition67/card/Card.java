package com.Expedition67.card;
import com.Expedition67.unit.*;

public class Card {
    private String name;
    private int apCost;
    private boolean isPermanant;
    private int defaultUsesAmount;
    private int usesLeft;
    private CardAbility ability;
    private boolean locked = false;

    public Card(String name,int apCost,boolean isPermanant,int defaultUsesAmount,CardAbility ability){
        this.name = name;
        this.apCost = apCost; //for crad
        this.isPermanant = isPermanant;
        this.defaultUsesAmount = defaultUsesAmount;
        this.usesLeft = defaultUsesAmount;
        this.ability = ability;
    }

    public Card (Card c){
        this.name=c.getName();
        this.apCost=c.getAP();
        this.ability=c.getAbility();
        this.defaultUsesAmount=c.getDefaultUsesAmount();
        this.usesLeft=c.getUsesLeft();
        this.isPermanant=c.getPerm();
    }
    
    public void use(Unit src,Unit target){
        target.getBrain().applyCard(ability, src);
    }

    public void use(PlayerBrain playerBrain,Unit target){
        target.getBrain().applyCard(ability, playerBrain.getOwner());
    }

    public void setLocked(boolean state) { this.locked = state; }
    
    public boolean canUse(UnitBrain ub){
        PlayerBrain pb = (PlayerBrain) ub;
        return (!isPermanant && usesLeft > 0) && pb.getAP() > apCost;
    }
    public boolean isLocked() { return locked; }
    public String getName(){return this.name;}
    public int getAP(){return this.apCost;}
    public boolean getPerm(){return this.isPermanant;}
    public int getDefaultUsesAmount(){return this.defaultUsesAmount;}
    public int getUsesLeft(){return this.usesLeft;}
    public void addUsesLeft(int usesLeft) {this.usesLeft += usesLeft;}
    public CardAbility getAbility() {return this.ability;}

    public Card copy() {
        return new Card(this.name,this.apCost,this.isPermanant,this.usesLeft,this.ability);
    }

}
