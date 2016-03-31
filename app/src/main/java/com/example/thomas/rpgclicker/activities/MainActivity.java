package com.example.thomas.rpgclicker.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.thomas.rpgclicker.ui.DrawingPanel;
import com.example.thomas.rpgclicker.Player;
import com.example.thomas.rpgclicker.R;
import com.example.thomas.rpgclicker.money.MoneyHandler;
import com.example.thomas.rpgclicker.Monster;
import com.example.thomas.rpgclicker.processes.MonsterThread;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity{

    private FragmentMain fmain;
    private FragmentStats fstats;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    CustomDrawerAdapter adapter;

    List<DrawerItem> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing
        dataList = new ArrayList<>();
        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                GravityCompat.START);

        dataList.add(new DrawerItem("Main", R.drawable.main));
        dataList.add(new DrawerItem("Weapons", R.drawable.weapons));
        dataList.add(new DrawerItem("Armor", R.drawable.armor));
        dataList.add(new DrawerItem("Stats", R.drawable.stats));
        dataList.add(new DrawerItem("Leaderboard", R.drawable.leaderboard));

        adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                dataList);

        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open,
                R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to
                // onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            SelectItem(0);
        }
    }

    // deferer onCLickListener
    // to Fragment Main:
    public void meeleAttack(View v){
        FragmentMain.panel.meeleAttack();
    }

    public void spellAttack(View v){
        FragmentMain.panel.spellAttack();
    }

    public void spellHeal(View v){
        FragmentMain.panel.spellHeal();
    }

    // to Fragment Stats:
    public void addDPS1(View v){FragmentMain.player.addDPS1(v);}

    public void addDPS5(View v){FragmentMain.player.addDPS5(v);}

    public void addDPS10(View v){FragmentMain.player.addDPS10(v);}

    public void addHPS1(View v){FragmentMain.player.addHPS1(v);}

    public void addHPS5(View v){FragmentMain.player.addHPS5(v);}

    public void addHPS10(View v){FragmentMain.player.addHPS10(v);}

    public void addMD1(View v){FragmentMain.player.addMD1(v);}

    public void addMD5(View v){FragmentMain.player.addMD5(v);}

    public void addMD10(View v){FragmentMain.player.addMD10(v);}

    public void addSD1(View v){FragmentMain.player.addSD1(v);}

    public void addSD5(View v){FragmentMain.player.addSD5(v);}

    public void addSD10(View v){FragmentMain.player.addSD10(v);}

    public void addSHS1(View v){FragmentMain.player.addSHS1(v);}

    public void addSHS5(View v){FragmentMain.player.addSHS5(v);}

    public void addSHS10(View v){FragmentMain.player.addSHS10(v);}

    public void SelectItem(int position) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (position) {
            case 0:
                if (fmain == null)
                    fmain = new FragmentMain();
                fmain.continueThread();
                args.putString(FragmentMain.ITEM_NAME, dataList.get(position)
                        .getItemName());
                args.putInt(FragmentMain.IMAGE_RESOURCE_ID, dataList.get(position)
                        .getImgResID());

                fmain.setArguments(args);
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fmain)
                        .commit();

                mDrawerList.setItemChecked(position, true);
                setTitle(dataList.get(position).getItemName());
                mDrawerLayout.closeDrawer(mDrawerList);

                return;
            case 1:
                fragment = new FragmentWeapons();
                args.putString(FragmentWeapons.ITEM_NAME, dataList.get(position)
                        .getItemName());
                args.putInt(FragmentWeapons.IMAGE_RESOURCE_ID, dataList.get(position)
                        .getImgResID());
                break;
            case 2:
                fragment = new FragmentWeapons();
                args.putString(FragmentWeapons.ITEM_NAME, dataList.get(position)
                        .getItemName());
                args.putInt(FragmentWeapons.IMAGE_RESOURCE_ID, dataList.get(position)
                        .getImgResID());
                break;
            case 3:
                if (fstats == null) {
                    fstats = new FragmentStats();
                }
                args.putString(FragmentMain.ITEM_NAME, dataList.get(position)
                        .getItemName());
                args.putInt(FragmentMain.IMAGE_RESOURCE_ID, dataList.get(position)
                        .getImgResID());

                fstats.setArguments(args);
                frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fstats)
                        .commit();

                mDrawerList.setItemChecked(position, true);
                setTitle(dataList.get(position).getItemName());
                mDrawerLayout.closeDrawer(mDrawerList);

                return;
            case 4:
                fragment = new FragmentMain();
                args.putString(FragmentMain.ITEM_NAME, dataList.get(position)
                        .getItemName());
                args.putInt(FragmentMain.IMAGE_RESOURCE_ID, dataList.get(position)
                        .getImgResID());
                break;
            default:
                break;
        }

        fmain.pause();

        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                .commit();

        mDrawerList.setItemChecked(position, true);
        setTitle(dataList.get(position).getItemName());
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return false;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private class DrawerItemClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            SelectItem(position);

        }
    }
}
