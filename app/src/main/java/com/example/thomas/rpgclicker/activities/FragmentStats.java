package com.example.thomas.rpgclicker.activities;

/**
 * Created by Thomas on 29.03.2016.
 */
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thomas.rpgclicker.Player;
import com.example.thomas.rpgclicker.R;

import org.w3c.dom.Text;

public class FragmentStats extends Fragment {

    public static Button[][] buttons = new Button[5][3];
    public static TextView[] textViews = new TextView[5];
    public static TextView goldView;

    public FragmentStats() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_stats, container,
                false);

        textViews[0] = (TextView) view.findViewById(R.id.textView12);
        textViews[1] = (TextView) view.findViewById(R.id.textView22);
        textViews[2] = (TextView) view.findViewById(R.id.textView32);
        textViews[3] = (TextView) view.findViewById(R.id.textView42);
        textViews[4] = (TextView) view.findViewById(R.id.textView52);
        textViews[0].setText(""+Player.dpslvl+" ("+FragmentMain.player.getPrice(Player.dpslvl+1, 1)+"G)");
        textViews[1].setText(""+Player.hpslvl+" ("+FragmentMain.player.getPrice(Player.hpslvl+1, 1)+"G)");
        textViews[2].setText(""+Player.meelelvl+" ("+FragmentMain.player.getPrice(Player.meelelvl+1, 1)+"G)");
        textViews[3].setText(""+Player.spelldlvl+" ("+FragmentMain.player.getPrice(Player.spelldlvl+1, 1)+"G)");
        textViews[4].setText(""+Player.spellhlvl+" ("+FragmentMain.player.getPrice(Player.spellhlvl+1, 1)+"G)");

        goldView = (TextView) view.findViewById(R.id.tvGold);
        goldView.setText(FragmentMain.player.getGoldString());

        return view;
    }

}