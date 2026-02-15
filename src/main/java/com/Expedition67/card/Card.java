package com.Expedition67.card;
import com.Expedition67.unit.*;

public class Card {
    private String name;
    private int apCost;
    private boolean isPermanant;
    private int usesLeft;
    private CardAbility ability;
    private boolean locked = false;

    public Card(String name,int apCost,boolean isPermanant,int usesLeft,CardAbility ability){
        this.name = name;
        this.apCost = apCost; //for crad
        this.isPermanant = isPermanant; 
        this.usesLeft = usesLeft;
        this.ability = ability;
    }

    public Card (Card c){
        this.name=c.getName();
        this.apCost=c.getAP();
        this.ability=c.getAbility();
        this.usesLeft=c.usesLeft();
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
    public int usesLeft(){return this.usesLeft;}
    public CardAbility getAbility() {return this.ability;}

}
