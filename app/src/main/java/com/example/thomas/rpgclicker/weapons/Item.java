package com.example.thomas.rpgclicker.weapons;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.thomas.rpgclicker.R;

/**
 * Created by Thomas on 31.03.2016.
 */
public class Item {
    private Bitmap icon;
    private int posX, posY;
    private String name;

    public Item(String name, Resources r, int id, int x, int y){
        this(name, r, id);
        this.setPos(x, y);
    }

    public Item (String name, Resources r, int id){
        this.name = name;
        icon = BitmapFactory.decodeResource(r, id);
        if(icon == null)
            Log.d("ITEM", "Couldn't load icon");
    }


    public void setPos(int x, int y){
        posX = x;
        posY = y;
    }

    public Bitmap getBitmap(){
        return icon;
    }
}
