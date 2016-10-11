package jorgereina1986.c4q.nyc.djlagarto.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jorgereina1986.c4q.nyc.djlagarto.fragments.ChartsFragment;
import jorgereina1986.c4q.nyc.djlagarto.fragments.SoundcloudFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment pageFragment = null;
        switch (position){
            case 0:
                pageFragment = new ChartsFragment();
                break;
            case 1:
                pageFragment = new SoundcloudFragment();
                break;
            default:
                return null;

        }

        return pageFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "HOT";
            case 1:
                return "MIXES";
            default:
                return null;
        }
    }



    @Override
    public int getCount() {
        return 2;
    }

}
