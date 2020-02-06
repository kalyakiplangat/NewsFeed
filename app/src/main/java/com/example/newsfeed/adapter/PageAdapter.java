package com.example.newsfeed.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.newsfeed.fragments.AllArticlesFragment;
import com.example.newsfeed.fragments.SourcesFragment;

public class PageAdapter extends FragmentStatePagerAdapter {
    private int mNumberOfTabs;

    public PageAdapter(@NonNull FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.mNumberOfTabs = numberOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AllArticlesFragment();
            case 1:
                return new SourcesFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return mNumberOfTabs;
    }
}
