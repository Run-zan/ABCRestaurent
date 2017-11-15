package app.ittraing.com.ittrainingdemoapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.ittraing.com.ittrainingdemoapp.fragments.GallaryFragment;
import app.ittraing.com.ittrainingdemoapp.fragments.MainFragment;
import app.ittraing.com.ittrainingdemoapp.fragments.ProfileFragment;

/**
 * Created by ranja_000 on 6/14/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int numberOfTab;

    //constructor defined with 2 parameters
    public ViewPagerAdapter(FragmentManager fm, int numberOfTab) {
        super(fm);
        this.numberOfTab = numberOfTab;
    }

    @Override
    public Fragment getItem(int position) {
        //use of switch case to call different framgments
        switch (position) {
            case 0:
                MainFragment mainFragment = new MainFragment();
                return mainFragment;

            case 1:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;

            case 2:
                GallaryFragment fragment3 = new GallaryFragment();
                return fragment3;

            default:
            return null;
        }
    }
    @Override
    public int getCount() {
        return numberOfTab;
    }
}
