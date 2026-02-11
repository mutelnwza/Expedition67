package com.Expedition67.card;
import com.Expedition67.unit.Unit;

public class Card {
    private String name;
    private int apCost;
    private boolean isPermanant;
    private int usesLeft;
    private CardAbility ability;


    public Card(String name,int apCost,boolean isPermanant,int usesLeft,CardAbility ability){
        this.name = name;
        this.apCost = apCost; //for crad
        this.isPermanant = isPermanant; 
        this.usesLeft = usesLeft;
        this.ability = ability;
    }
    public void use(Unit src,Unit target){
        if(!canUse(src)){
              System.out.println("Cannot use card: " + name);
        }
        src.useAp(apCost);
        ability.apply(src, target);
        if(!isPermanant){
            usesLeft--;
        }
    }

    public boolean canUse(Unit src){
        if(!isPermanant && usesLeft <= 0 ){
            return false;
        }
        if(src.apCost <= apCost){
            return false;
        }
        return true;
    }

}
