package com.example.thomas.rpgclicker.money;

import android.util.Log;

/**
 * Created by Thomas on 28.03.2016.
 */
public class MoneyHandler {
    private MoneyType type = MoneyType.ONE;
    private float amount = 1f;

    public void add(MoneyType t, float a){
        long temp = (long)(amount * type.getNr());
        long ntemp = (long)(a * t.getNr());

        long ctemp = temp + ntemp;
        type = type.getType(ctemp);
        amount = ctemp / type.getNr();

        //Log.d("GOLD", temp + " " + ntemp + " " + ctemp + " " + type + " " + amount);
    }

    public MoneyType getMT(){
        return type;
    }

    public float getGold(){
        return amount;
    }
}
