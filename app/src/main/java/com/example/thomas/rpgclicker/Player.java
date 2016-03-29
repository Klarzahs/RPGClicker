package com.example.thomas.rpgclicker;

import android.util.Log;

import com.example.thomas.rpgclicker.money.MoneyHandler;
import com.example.thomas.rpgclicker.weapons.Weapon;
import com.example.thomas.rpgclicker.weapons.WeaponType;

/**
 * Created by Thomas on 28.03.2016.
 */
public class Player {
    private int hp = 100;

    private WeaponType typeMain, typeOff;
    private int bonus = 1;
    private MoneyHandler mh;
    private int dmgMeele = 10;
    private int dmgSpell = 50;
    private int healSpell = 30;

    public Player(MoneyHandler mh){
        this.mh = mh;
        typeMain = WeaponType.SWORD;
        typeOff = WeaponType.SHIELD;
    }

    public void reward(){
        mh.add(mh.getMT(), bonus);
        bonus ++;
    }

    public int getHp(){
        return hp;
    }

    public void setHp(int h){
        hp = h;
        if(hp <= 0) {
            Log.d("GAME", "GAME OVER: HP <= 0");
            System.exit(1);
        }
    }

    public float getDeffBonus(){
        return Weapon.getDeffBonus(typeOff);
    }

    public int attackMeele(){
        return (int)(dmgMeele * Weapon.getMeeleBonus(typeMain));
    }

    public int attackSpell(){
        return (int)(dmgSpell * Weapon.getSpellBonus(typeMain));
    }

    public int healSpell(){ return (int) (healSpell * Weapon.getHealBonus(typeOff));}

    public void heal(){
        hp += healSpell();
        if(hp >= 100) hp = 100;
        Log.d("ATTACK", "Healed to "+hp+" hp");
    }

    public float getGold(){
        return mh.getGold();
    }

    public String getGoldString(){
        String str = ""+(int)getGold();
        switch(mh.getMT()){
            case ONE:
                str += "g";
                break;
            case THOUSAND:
                str += "1k g";
                break;
            case TENTHOUSAND:
                str += "10k g";
                break;
            case HUNDREDTHOUSAND:
                str += "100k g";
                break;
            case MILLION:
                str += "1m g";
                break;
            default:
                str += "g";
                break;
        }
        return str;
    }

}
