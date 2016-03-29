package com.example.thomas.rpgclicker.processes;

import android.util.Log;

import com.example.thomas.rpgclicker.Monster;

/**
 * Created by Thomas on 28.03.2016.
 */
public class MonsterThread implements Runnable{
    private boolean running = false;
    private long lastTime;
    private long offset = 1000;     // in millis
    private Monster monster;

    public void init(Monster m){
        monster = m;
    }

    @Override
    public void run(){
        running = true;
        Log.d("MTHREAD", "Starting..");
        lastTime = System.currentTimeMillis();
        while(running){
            if(System.currentTimeMillis() - lastTime >= offset){
                monster.attack();
                lastTime = System.currentTimeMillis();
                //Log.d("MTHREAD", "Monster attacked");
            }
            try {
                Thread.sleep(10);
            }catch (Exception e){
                Log.d("MTHREAD", "Exception: "+e.getLocalizedMessage());
            }
        }
    }
}
