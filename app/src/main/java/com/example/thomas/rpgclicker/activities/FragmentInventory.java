package com.example.thomas.rpgclicker.activities;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thomas.rpgclicker.R;
import com.example.thomas.rpgclicker.ui.InventoryPanel;

public class FragmentInventory extends Fragment {
    private InventoryPanel panel;

    public FragmentInventory() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewg = (ViewGroup)inflater.inflate(R.layout.fragment_layout_inventory, container,false);
        panel = new InventoryPanel(getActivity(), this);
        panel.setPadding(0, 0, 0, 0);
        panel.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewg.addView(panel);

        return viewg;
    }

}