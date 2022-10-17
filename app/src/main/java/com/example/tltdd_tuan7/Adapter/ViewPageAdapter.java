package com.example.tltdd_tuan7.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


import com.example.tltdd_tuan7.MainFragment.HomeFragment;
import com.example.tltdd_tuan7.MainFragment.ListFragment;
import com.example.tltdd_tuan7.MainFragment.ProfileFragment;

public class ViewPageAdapter extends FragmentStatePagerAdapter {

    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new ListFragment();
            case 2:
                return new ProfileFragment();
            default: return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
