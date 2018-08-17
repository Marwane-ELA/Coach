package com.marwane.coach.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.marwane.coach.view.Chrono;
import com.marwane.coach.view.Counter;

/**
 * Bronnen:
 * https://www.youtube.com/watch?v=zcnT-3F-9JA
 * https://developer.android.com/training/implementing-navigation/lateral
 * https://developer.android.com/guide/components/fragments
 *
 */
public class CounterChronoAdapter extends FragmentStatePagerAdapter{

    int numberOfTabs;

    public CounterChronoAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Counter counter = new Counter();
                return counter;
            case 1:
                Chrono chrono = new Chrono();
                return chrono;
            default:
                return null;
        }


    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
