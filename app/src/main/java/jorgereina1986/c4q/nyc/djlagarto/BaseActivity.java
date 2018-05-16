package jorgereina1986.c4q.nyc.djlagarto;

import android.app.FragmentManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import jorgereina1986.c4q.nyc.djlagarto.player.PlayerListener;
import jorgereina1986.c4q.nyc.djlagarto.player.PlayerFragment;

public class BaseActivity extends AppCompatActivity implements PlayerListener {

    private ViewPager viewPager;
    private PagerAdapter adapter;
    private TabLayout tabLayout;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bindViews();

        if (!isNetworkAvailable(this)) {
            Toast.makeText(this, "Network error. Please make sure you are connected to the internet", Toast.LENGTH_LONG).show();
        }
    }

    private void bindViews() {

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
    }

    @Override
    public void updatePlayer(String title, String albumCover, String trackUrl, int duration) {
        FragmentManager fragmentManager = getFragmentManager();
        PlayerFragment playerFragment = (PlayerFragment) fragmentManager.findFragmentById(R.id.fragment_player);
        playerFragment.addDataToPlayer(title, albumCover, trackUrl, duration);

    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
