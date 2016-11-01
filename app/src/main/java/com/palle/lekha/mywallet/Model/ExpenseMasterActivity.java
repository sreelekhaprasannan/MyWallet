package com.palle.lekha.mywallet.Model;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.palle.lekha.mywallet.Data.MyWalletDatabase;
import com.palle.lekha.mywallet.R;

public class ExpenseMasterActivity extends AppCompatActivity {
    MyWalletDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_master);

        initializeScreen();
        database=new MyWalletDatabase(ExpenseMasterActivity.this);
    }

    private void initializeScreen() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        //setSupportActionBar(toolbar);

        SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(4);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    private class SectionPagerAdapter extends FragmentStatePagerAdapter {
        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return new ViewExpenseFragment();
                }

                case 1:
                    return new AddExpenseFragment();
                default:
                    return new ViewExpenseFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "View Expense";
                case 1:
                    return "Add Expense";
                default:
                    return "View Expense";
            }
        }


        @Override
        public int getCount() {
            return 2;
        }
    }
}
