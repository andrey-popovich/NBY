package com.andrey.nby.ui.mainScreen;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.andrey.nby.App;
import com.andrey.nby.R;
import com.andrey.nby.data.DataManager;
import com.andrey.nby.di.component.ApplicationComponent;
import com.andrey.nby.ui.fragments.CurrenciesFragment;
import com.andrey.nby.ui.settings.SettingsActivity;


import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    DataManager mDataManager;

    FragmentPagerAdapter adapterViewPager;
    ViewPager vpPager;
    FloatingActionButton fab;
    Animation loadingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager = (ViewPager) findViewById(R.id.vpPager);
        vpPager.setAdapter(adapterViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(vpPager);
        fab = (FloatingActionButton) findViewById(R.id.fab);

        getCurrencyList();
    }

    public void getCurrencyList() {
        mDataManager.updateDatabase(this, fab);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {

        private static int NUM_ITEMS = 2;

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return CurrenciesFragment.newInstance(0, "All");
                case 1:
                    return CurrenciesFragment.newInstance(1, "Favorites");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Весь список";
                case 1:
                    return "Обране";
            }
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent settingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(settingsActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
