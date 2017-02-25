package com.andrey.nby.ui.mainScreen;


import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.andrey.nby.App;
import com.andrey.nby.R;
import com.andrey.nby.data.DataManager;
import com.andrey.nby.di.component.ApplicationComponent;
import com.andrey.nby.ui.fragments.CurrenciesFragment;


import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreenView {

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

        public MyPagerAdapter(FragmentManager fm) {
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
}
