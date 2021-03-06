package com.example.thomas.rpgclicker.processes;

import android.util.Log;

import com.example.thomas.rpgclicker.Monster;
import com.example.thomas.rpgclicker.Player;
import com.example.thomas.rpgclicker.ui.DrawingPanel;

import java.util.Random;

/**
 * Created by Thomas on 28.03.2016.
 */
public class MonsterThread implements Runnable{
    private boolean running = false;
    private boolean paused = true;
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
            if(paused){
                try{
                    Thread.sleep(10);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            while(!paused) {
                if (System.currentTimeMillis() - lastTime >= offset) {
                    monster.attack();
                    monster.getAttacked(Player.dpslvl);
                    lastTime = System.currentTimeMillis();
                    //Log.d("MTHREAD", "Monster attacked");

                    Random r = new Random();
                    int rand = r.nextInt(100) + 1;
                    if(rand < 5){
                        DrawingPanel.riposte();
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (Exception e) {
                    Log.d("MTHREAD", "Exception: " + e.getLocalizedMessage());
                }
            }
        }
    }

    public void stop(){
        running = false;
        paused = true;
    }

    public boolean isRunning(){
        return running;
    }

    public boolean isPaused(){
        return paused;
    }

    public void pause(){
        paused = true;
    }

    public void continueThread(){
        paused = false;
        Log.d("MTHREAD", "Contiued.");
    }
}
