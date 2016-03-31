package com.example.thomas.rpgclicker.activities;

/**
 * Created by Thomas on 29.03.2016.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.thomas.rpgclicker.Monster;
import com.example.thomas.rpgclicker.Player;
import com.example.thomas.rpgclicker.R;
import com.example.thomas.rpgclicker.money.MoneyHandler;
import com.example.thomas.rpgclicker.processes.MonsterThread;
import com.example.thomas.rpgclicker.ui.DrawingPanel;

public class FragmentMain extends Fragment{

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public static DrawingPanel panel;

    public static Player player;
    public static Monster monster;
    public static MoneyHandler mh;

    public static MonsterThread mt;
    public static Thread thread;

    public FragmentMain() {
        if(mh == null) mh = new MoneyHandler();
        if(player == null) player = new Player(mh);
        if(monster == null) monster = new Monster(player);

        if(mt == null){
            mt = new MonsterThread();
            mt.init(monster);
        }
        thread = new Thread(mt);
        thread.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewg = (ViewGroup)inflater.inflate(R.layout.fragment_layout_main, container,
                false);


        panel = new DrawingPanel(getActivity(), this);
        panel.setPadding(0,0,0,0);
        panel.setMinimumWidth(400);
        panel.setMinimumHeight(400);

        viewg.addView(panel);

        return viewg;
    }

    public Button getMeeleButton(){
        return (Button) getActivity().findViewById(R.id.meeleAttack);
    }

    public Button getHealButton(){
        return (Button) getActivity().findViewById(R.id.spellHeal);
    }

    public Button getSpellButton(){
        return (Button) getActivity().findViewById(R.id.spellAttack);
    }

    public void stop(){
        mt.stop();
        Log.d("MTHREAD", "Stopping");
    }

    public void pause(){
        mt.pause();
        Log.d("MTHREAD", "Pausing..");
    }

    public boolean isRunning(){
        return mt.isRunning();
    }

    public void continueThread(){
        mt.continueThread();
        Log.d("MTHREAD", "Continue..");
    }

}