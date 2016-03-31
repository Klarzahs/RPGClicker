package com.example.thomas.rpgclicker;

import android.util.Log;
import android.view.View;

import com.example.thomas.rpgclicker.activities.FragmentMain;
import com.example.thomas.rpgclicker.activities.FragmentStats;
import com.example.thomas.rpgclicker.money.MoneyHandler;
import com.example.thomas.rpgclicker.weapons.Weapon;
import com.example.thomas.rpgclicker.weapons.WeaponType;

public class Player {
    private int hp = 100;

    private WeaponType typeMain, typeOff;
    private int bonus = 1;
    private MoneyHandler mh;
    private int dmgMeele = 10;
    private int dmgSpell = 50;
    private int healSpell = 30;

    public static int dpslvl = 0;
    public static int hpslvl = 0;
    public static int meelelvl = 0;
    public static int spelldlvl = 0;
    public static int spellhlvl = 0;

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
        if(hp > 100)
            hp = 100;
    }

    public float getDeffBonus(){
        return Weapon.getDeffBonus(typeOff);
    }

    public int attackMeele(){
        return (int)((dmgMeele + meelelvl) * Weapon.getMeeleBonus(typeMain));
    }

    public int attackSpell(){
        return (int)((dmgSpell + spelldlvl) * Weapon.getSpellBonus(typeMain));
    }

    public int healSpell(){ return (int) ((healSpell + spellhlvl) * Weapon.getHealBonus(typeOff));}

    public void heal(){
        setHp(getHp() + healSpell());
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

    private boolean spendGold(int l, int a){
        if(mh.getGold()*mh.getMT().getNr() >= getPrice(l, a)){
            mh.substract(getPrice(l, a));
            return true;
        }
        return false;
    }

    public void addDPS1(View v){
        if(spendGold(dpslvl, 1))
            dpslvl = dpslvl + 1;
        FragmentStats.textViews[0].setText(""+dpslvl+" ("+getPrice(Player.dpslvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addDPS5(View v){
        if(spendGold(dpslvl, 5))
            dpslvl = dpslvl + 5;
        FragmentStats.textViews[0].setText(""+dpslvl+" ("+getPrice(Player.dpslvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addDPS10(View v){
        if(spendGold(dpslvl, 10))
            dpslvl = dpslvl + 10;
        FragmentStats.textViews[0].setText(""+dpslvl+" ("+getPrice(Player.dpslvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addHPS1(View v){
        if(spendGold(hpslvl, 1))
            hpslvl = hpslvl + 1;
        FragmentStats.textViews[1].setText(""+hpslvl+" ("+getPrice(Player.hpslvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addHPS5(View v){
        if(spendGold(hpslvl, 5))
            hpslvl = hpslvl + 5;
        FragmentStats.textViews[1].setText(""+hpslvl+" ("+getPrice(Player.hpslvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addHPS10(View v){
        if(spendGold(hpslvl, 10))
            hpslvl = hpslvl + 10;
        FragmentStats.textViews[1].setText(""+hpslvl+" ("+getPrice(Player.hpslvl, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addMD1(View v){
        if(spendGold(meelelvl, 1))
            meelelvl = meelelvl + 1;
        FragmentStats.textViews[2].setText(""+meelelvl+" ("+getPrice(Player.meelelvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addMD5(View v){
        if(spendGold(meelelvl, 5))
            meelelvl = meelelvl + 5;
        FragmentStats.textViews[2].setText("" + meelelvl+" ("+getPrice(Player.meelelvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addMD10(View v){
        if(spendGold(meelelvl, 10))
            meelelvl = meelelvl + 10;
        FragmentStats.textViews[2].setText(""+meelelvl+" ("+getPrice(Player.meelelvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addSD1(View v){
        if(spendGold(spelldlvl, 1))
            spelldlvl = spelldlvl + 1;
        FragmentStats.textViews[3].setText(""+spelldlvl+" ("+getPrice(Player.spelldlvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addSD5(View v){
        if(spendGold(spelldlvl, 5))
            spelldlvl = spelldlvl + 5;
        FragmentStats.textViews[3].setText(""+spelldlvl+" ("+getPrice(Player.spelldlvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addSD10(View v){
        if(spendGold(spelldlvl, 10))
            spelldlvl = spelldlvl + 10;
        FragmentStats.textViews[3].setText(""+spelldlvl+" ("+getPrice(Player.spelldlvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addSHS1(View v){
        if(spendGold(spellhlvl, 1))
            spellhlvl = spellhlvl + 1;
        FragmentStats.textViews[4].setText(""+spellhlvl+" ("+getPrice(Player.spellhlvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addSHS5(View v){
        if(spendGold(spellhlvl, 5))
            spellhlvl = spellhlvl + 5;
        FragmentStats.textViews[4].setText(""+spellhlvl+" ("+getPrice(Player.spellhlvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public void addSHS10(View v){
        if(spendGold(spellhlvl, 10))
            spellhlvl = spellhlvl + 10;
        FragmentStats.textViews[4].setText(""+spellhlvl+" ("+getPrice(Player.spellhlvl+1, 1)+"G)");
        FragmentStats.goldView.setText(getGoldString());
    }

    public int getPrice(int l, int a){
        int ret = 0;
        for(int i = 0; i < a; i++){
            ret += l + i;
        }
        return ret;
    }

}
