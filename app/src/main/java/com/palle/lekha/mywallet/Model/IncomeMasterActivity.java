package com.palle.lekha.mywallet.Model;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.palle.lekha.mywallet.R;

public class IncomeMasterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_master);

        initializeScreen();
    }
    private void initializeScreen() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        //setSupportActionBar(toolbar);

        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    private class SectionPagerAdapter extends FragmentStatePagerAdapter {
        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:{
                    return new ViewIncomeFragment();

                }
                case 1: return new AddIncomeFragment();
                default: return new ViewIncomeFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "View Income";
                case 1: return "Add Income";
                default: return "View Income";
            }
        }

        @Override
        public int getCount() {
            return 2;
        }


    }
}
