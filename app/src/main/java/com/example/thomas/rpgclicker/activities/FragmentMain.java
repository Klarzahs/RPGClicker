package com.example.thomas.rpgclicker.activities;

/**
 * Created by Thomas on 29.03.2016.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thomas.rpgclicker.Monster;
import com.example.thomas.rpgclicker.Player;
import com.example.thomas.rpgclicker.R;
import com.example.thomas.rpgclicker.money.MoneyHandler;
import com.example.thomas.rpgclicker.processes.MonsterThread;
import com.example.thomas.rpgclicker.ui.DrawingPanel;

public class FragmentMain extends Fragment {

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public static DrawingPanel panel;

    public static Player player;
    public static Monster monster;
    public static MoneyHandler mh;

    public static MonsterThread mt;

    public FragmentMain() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewg = (ViewGroup)inflater.inflate(R.layout.fragment_layout_main, container,
                false);
/*
        ivIcon = (ImageView) view.findViewById(R.id.frag1_icon);
        tvItemName = (TextView) view.findViewById(R.id.frag1_text);

        tvItemName.setText(getArguments().getString(ITEM_NAME));
        ivIcon.setImageDrawable(view.getResources().getDrawable(
                getArguments().getInt(IMAGE_RESOURCE_ID)));*/

        panel = new DrawingPanel(getActivity(), this);
        panel.setPadding(0,0,0,0);
        panel.setMinimumWidth(400);
        panel.setMinimumHeight(400);

        viewg.addView(panel);

        mh = new MoneyHandler();
        player = new Player(mh);
        monster = new Monster(player);

        mt = new MonsterThread();
        mt.init(monster);
        new Thread(mt).start();

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

}