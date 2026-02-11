package com.Expedition67.storage;
//import com.Expedition67.card.Card;
import com.Expedition67.card.*;
import com.Expedition67.unit.Enemy.EnemyBrain;
import com.Expedition67.unit.Unit;
import com.Expedition67.unit.UnitStats;
import java.util.HashMap;

public class Warehouse {
    private static Warehouse instance;
    private HashMap<String, Unit> unitFactory = new HashMap<>();
    private HashMap<String, Card> cardFactory = new HashMap<>();
    private Warehouse(){
        loadEnemy();
        loadCard();
    }

    public static Warehouse Instance(){
        if(instance == null){
            instance = new Warehouse();
        }
        return instance;
    }

    private void loadEnemy(){
        Unit test = new Unit("test", new UnitStats(50, 1, 1, 1, 0), new EnemyBrain(),30,30,500,500);
        test.getAnimator().addAnimation("idle", 0, 4, 4);
        unitFactory.put("test", test);
        // Unit Momo = new Unit("Momo", new UnitStats(maxHp, str, critDmg, critRate, def, agi), new MomoBrain(), 0, 0, 0, 0); <- x,y,w,h
        // Momo.getAnimator().addAnimation(name, row, speed, frameCount); <- hard code เอา
        // unitFactory.put("Momo", Momo);
    }

    private void loadCard(){
        Card Attack = new Card("Remnant Hit", 1, false, 1,new DamageAbility(6));
        cardFactory.put("Remnant Hit" , Attack);
    }

    public Unit spawnEnemy(String name,int x, int y){
        Unit master = unitFactory.get(name);
        if(master!=null){
            return master.copy(x, y);
        }
        else return null;
    }
}
