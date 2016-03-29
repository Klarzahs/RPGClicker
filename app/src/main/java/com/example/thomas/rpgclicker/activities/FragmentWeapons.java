package com.example.thomas.rpgclicker.activities;

/**
 * Created by Thomas on 29.03.2016.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thomas.rpgclicker.R;

public class FragmentWeapons extends Fragment {

    ImageView ivIcon;
    TextView tvItemName;

    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";

    public FragmentWeapons() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_weapons, container,
                false);

        ivIcon = (ImageView) view.findViewById(R.id.frag2_icon);
        tvItemName = (TextView) view.findViewById(R.id.frag2_text);

        String str = getArguments().getString(ITEM_NAME);
        if(str == null) Log.d("LL", "LOL");
        tvItemName.setText(str);
        ivIcon.setImageDrawable(view.getResources().getDrawable(
                getArguments().getInt(IMAGE_RESOURCE_ID)));
        return view;
    }

}