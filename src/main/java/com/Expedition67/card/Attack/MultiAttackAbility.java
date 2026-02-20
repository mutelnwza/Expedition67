package com.Expedition67.card.Attack;

import com.Expedition67.card.CardAbility;
import com.Expedition67.card.CardAbility.CardType;
import com.Expedition67.unit.Unit;

public class MultiAttackAbility extends DamageAbility {
    private final int amount;
    public MultiAttackAbility(int value, CardType cardType, int amount) {
        super(value, cardType);
        this.amount = amount;
    }
    public MultiAttackAbility(int min, int max, CardType cardType, int amount){
        super(min,max,cardType);
        this.amount = amount;
    }

    @Override
    public void apply(Unit target, Unit src){
        for(int i=0;i<amount;i++){
            super.apply(target,src);
        }
    }
}
