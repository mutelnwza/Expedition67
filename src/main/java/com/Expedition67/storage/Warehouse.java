package com.Expedition67.storage;
import com.Expedition67.card.Card;
import com.Expedition67.card.DamageAbility;
import com.Expedition67.unit.Unit;
import java.util.HashMap;

public class Warehouse {
    private static Warehouse instance;
    private HashMap<String, Unit> unitFactory = new HashMap<>();
    private HashMap<String, Card> cardFactory = new HashMap<>();
    private Warehouse(){
        loadCard();
    }

    public static Warehouse Instance(){
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
    }

    private void loadEnemy(){
        // UnitBrain momoBrain = new UnitBrain();
        // Unit Momo = new Unit("Momo", new UnitStats(maxHp, str, critDmg, critRate, def, agi), momoBrain, new, 0, 0, 0, 0);
        // unitFactory.put("Momo", Momo);
        // momoBrain.setOwner(Momo);
        // Momo.getAnimator().addAnimation(name, row, speed, frameCount);
    }

    private void loadCard(){
        Card Attack = new Card("Remnant Hit", 1, false, 1,new DamageAbility(6));
        cardFactory.put("Remnant Hit" , Attack);


    }

    public Unit getEnemy(String name){
        return null;
    }
}
