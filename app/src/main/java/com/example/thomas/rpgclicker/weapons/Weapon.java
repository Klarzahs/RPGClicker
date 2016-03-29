package com.example.thomas.rpgclicker.weapons;

/**
 * Created by Thomas on 28.03.2016.
 */
public class Weapon {
    public static float getMeeleBonus(WeaponType type){
        if(type == WeaponType.AXE2h) return 1.5f;
        if(type == WeaponType.BLADESTAFF) return 1.25f;
        if(type == WeaponType.SWORD) return 1.25f;
        return 1f;
    }

    public static float getSpellBonus(WeaponType type){
        if(type == WeaponType.BLADESTAFF) return 1.25f;
        if(type == WeaponType.DAGGER) return 1.25f;
        return 1f;
    }

    public static float getDeffBonus(WeaponType type){
        if(type == WeaponType.SHIELD) return 1.25f;
        return 1f;
    }

    public static float getHealBonus(WeaponType type){
        if(type == WeaponType.BOOK) return 1.25f;
        return 1f;
    }
}
