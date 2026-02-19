package com.Expedition67.card;
import com.Expedition67.unit.PlayerBrain;
import com.Expedition67.unit.Unit;

public class Card {
    private String name;
    private String description; 
    private int apCost; //for card
    private boolean isPermanant;
    private int usesLeft;
    private CardAbility ability;

    public Card(String name, String description, int apCost, boolean isPermanant, int usesLeft, CardAbility ability){
        this.name = name;
        this.description = description; 
        this.apCost = apCost; 
        this.isPermanant = isPermanant; 
        this.usesLeft = usesLeft;
        this.ability = ability;
    }

    public Card (Card c){
        this.name = c.getName();
        this.description = c.getDescription(); 
        this.apCost = c.getAP();
        this.ability = c.getAbility();
        this.usesLeft = c.usesLeft();
        this.isPermanant = c.getPerm();
    }
    
    public void use(Unit src,Unit target){
        target.getBrain().applyCard(ability, src);
    }

    public void use(PlayerBrain playerBrain,Unit target){
        if(canUse(playerBrain)){
            target.getBrain().applyCard(ability, playerBrain.getOwner());
            playerBrain.onUseCard(apCost);
        }
    }

    public boolean canUse(PlayerBrain pb){
        return (!isPermanant && usesLeft > 0) && pb.getAP() > apCost;
    }

    public String getName(){return this.name;}
    public String getDescription(){return this.description;} 
    public int getAP(){return this.apCost;}
    public boolean getPerm(){return this.isPermanant;}
    public int usesLeft(){return this.usesLeft;}
    public CardAbility getAbility() {return this.ability;}
}