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

public class MainActivity extends FragmentActivity {
    public final static String LVLUP_MESSAGE = "com.example.thomas.rpgclicker.LVLUP";

    private FragmentMain fmain;

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
        dataList.add(new DrawerItem("Stats", R.drawable.stats));
        dataList.add(new DrawerItem("Weapons", R.drawable.weapons));
        dataList.add(new DrawerItem("Armor", R.drawable.armor));
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

    public void meeleAttack(View v){
        FragmentMain.panel.meeleAttack();
    }

    public void spellAttack(View v){
        FragmentMain.panel.spellAttack();
    }

    public void spellHeal(View v){
        FragmentMain.panel.spellHeal();
    }

    public void openLevelUp(){
       /* Intent myIntent = new Intent(MainActivity.this, ItemListActivity.class);
        //myIntent.putExtra("key", value); //Optional parameters
        MainActivity.this.startActivity(myIntent); */
    }

    public void SelectItem(int possition) {

        Fragment fragment = null;
        Bundle args = new Bundle();
        switch (possition) {
            case 0:
                if (fmain == null)
                    fmain = new FragmentMain();
                args.putString(FragmentMain.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentMain.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());

                fmain.setArguments(args);
                FragmentManager frgManager = getFragmentManager();
                frgManager.beginTransaction().replace(R.id.content_frame, fmain)
                        .commit();

                mDrawerList.setItemChecked(possition, true);
                setTitle(dataList.get(possition).getItemName());
                mDrawerLayout.closeDrawer(mDrawerList);

                return;
            case 1:
                fragment = new FragmentWeapons();
                args.putString(FragmentWeapons.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentWeapons.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 2:
                fragment = new FragmentWeapons();
                args.putString(FragmentWeapons.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentWeapons.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 3:
                fragment = new FragmentStats();
                args.putString(FragmentStats.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentStats.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            case 4:
                fragment = new FragmentMain();
                args.putString(FragmentMain.ITEM_NAME, dataList.get(possition)
                        .getItemName());
                args.putInt(FragmentMain.IMAGE_RESOURCE_ID, dataList.get(possition)
                        .getImgResID());
                break;
            default:
                break;
        }

        fragment.setArguments(args);
        FragmentManager frgManager = getFragmentManager();
        frgManager.beginTransaction().replace(R.id.content_frame, fragment)
                .commit();

        mDrawerList.setItemChecked(possition, true);
        setTitle(dataList.get(possition).getItemName());
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
