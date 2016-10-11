package jorgereina1986.c4q.nyc.djlagarto.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.adapters.ViewPagerAdapter;

public class Main2Activity extends AppCompatActivity {

    private static final int NUM_PAGES = 2;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;

    //TODO add logic to Tabs
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bindVews();

    }

    private void bindVews() {

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("News"));
        tabLayout.addTab(tabLayout.newTab().setText("Mixes"));
        tabLayout.setupWithViewPager(viewPager);

    }
}
