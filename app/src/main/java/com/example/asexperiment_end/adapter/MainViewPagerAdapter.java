package com.example.asexperiment_end.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.asexperiment_end.Utils.DateUtil;
import com.example.asexperiment_end.Utils.GlobalUtil;
import com.example.asexperiment_end.adapter.fragments.MainFragment;

import java.util.LinkedList;
import java.util.List;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    LinkedList<MainFragment> fragments = new LinkedList<>();
    LinkedList<String> dates = new LinkedList<>();

    public MainViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        dates = GlobalUtil.getInstance().dataBaseHelper.getAvaliableDate();
        if (!dates.contains(DateUtil.getFormattedDate())){
            dates.addLast(DateUtil.getFormattedDate());
        }
        for(String date:dates){
            MainFragment fragment = new MainFragment(date);
            fragments.add(fragment);
        }
    }

    public void reload(){
        for (MainFragment fragment:
        fragments){
            fragment.reload();
        }
    }

    public String getDate(int currentPage){
        return dates.get(currentPage);
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public int getLastIndex(){
        return fragments.size()-1;
    }
}
