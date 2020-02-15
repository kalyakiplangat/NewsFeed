package com.example.newsfeed.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.newsfeed.R;
import com.example.newsfeed.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class SourceActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_source);

        tabLayout = findViewById(R.id.tab_layout);

        viewPager = findViewById(R.id.viewpager);
        final ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //some code here
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //some code here
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
