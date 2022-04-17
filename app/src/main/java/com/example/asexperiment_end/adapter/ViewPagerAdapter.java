package com.example.asexperiment_end.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> addFragmentList;
    String titlearr[];

    public ViewPagerAdapter(@NonNull FragmentManager fm,List<Fragment> list, String[] title ) {
        super(fm);
        addFragmentList = list;
        titlearr = title;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return addFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return addFragmentList != null ? addFragmentList.size() : 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titlearr[position];
    }
}