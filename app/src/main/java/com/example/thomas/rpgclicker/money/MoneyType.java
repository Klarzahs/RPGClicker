package com.example.thomas.rpgclicker.money;

public enum MoneyType {
    ONE(1),
    THOUSAND(1000),
    TENTHOUSAND(10000),
    HUNDREDTHOUSAND(100000),
    MILLION(1000000),
    TENMILLION(10000000);

    private int nr;

    MoneyType(int i){
        nr = i;
    }

    public int getNr(){
        return nr;
    }

    public MoneyType getType(long amount){
        if(amount >= 10000000) return TENMILLION;
        if(amount >= 1000000) return MILLION;
        if(amount >= 100000) return HUNDREDTHOUSAND;
        if(amount >= 10000) return TENTHOUSAND;
        if(amount >= 1000) return THOUSAND;
        if(amount >= 1) return ONE;
        return ONE;
    }

}
