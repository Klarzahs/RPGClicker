package com.example.thomas.rpgclicker;

import android.util.Log;

import com.example.thomas.rpgclicker.Player;

/**
 * Created by Thomas on 28.03.2016.
 */
public class Monster {
    private int lvl = 0;
    private int hp;
    private int dmg;
    private int loot;
    private Player player;

    public Monster(Player p){
        player = p;
        lvlUp();
    }

    public void lvlUp(){
        lvl++;
        hp = (int)(lvl * 5 + 100);
        dmg = lvl * 2;
        loot = lvl + 5;
    }

    public void attack(){
        player.setHp(player.getHp() - ((int)(dmg / player.getDeffBonus())));
    }

    public void getAttacked(int dmg){
        hp = hp - dmg;
        if(hp <= 0){
            Log.d("MONSTER", "Monster killed");
            lvlUp();
            player.reward();
        }
    }

    public int getHp(){
        return hp;
    }

    public int getLvl(){
        return lvl;
    }

    public int getDmg(){
        return dmg;
    }

    public int getMaxHP(){
        return (lvl * 5 + 100);
    }

    public static void test(){

    }
}
