package jorgereina1986.c4q.nyc.djlagarto.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import jorgereina1986.c4q.nyc.djlagarto.R;
import jorgereina1986.c4q.nyc.djlagarto.adapters.ViewPagerAdapter;
import jorgereina1986.c4q.nyc.djlagarto.fragments.PlayerCommunicator;
import jorgereina1986.c4q.nyc.djlagarto.fragments.PlayerFragment;

public class Main2Activity extends AppCompatActivity implements PlayerCommunicator{

    private static final int NUM_PAGES = 2;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bindViews();

    }

    private void bindViews() {

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
    }

    @Override
    public void updatePlayer(String title, String albumCover, String trackUrl) {

        android.app.FragmentManager fragmentManager = getFragmentManager();
        PlayerFragment playerFragment = (PlayerFragment) fragmentManager.findFragmentById(R.id.fragment_player);
        playerFragment.updatePlayer(title, albumCover, trackUrl);

    }
}
