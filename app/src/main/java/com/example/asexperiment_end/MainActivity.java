package com.example.asexperiment_end;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.example.asexperiment_end.DB.DataBaseHelper;
import com.example.asexperiment_end.Utils.DateUtil;
import com.example.asexperiment_end.Utils.GlobalUtil;
import com.example.asexperiment_end.adapter.MainViewPagerAdapter;
import com.example.asexperiment_end.adapter.fragments.MainFragment;
import com.robinhood.ticker.TickerUtils;
import com.robinhood.ticker.TickerView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    public static final int FLAG = 100;
    private ViewPager viewPager;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private TickerView outcomeTicker;
    private TickerView incomeTicker;
    private TextView week;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //每月月初清空数据
        isFirstDayOfMonth();

        viewPager = findViewById(R.id.view_pager);
        week = findViewById(R.id.date_text);

        GlobalUtil.getInstance().setContext(getApplicationContext());
        GlobalUtil.getInstance().setMainActivity(this);
        outcomeTicker = findViewById(R.id.account_out);
        outcomeTicker.setCharacterLists(TickerUtils.provideNumberList());
        outcomeTicker.setAnimationDuration(1000);//设置动画的持续时间
        outcomeTicker.setAnimationInterpolator(new OvershootInterpolator());
        incomeTicker = findViewById(R.id.account_in);
        incomeTicker.setCharacterLists(TickerUtils.provideNumberList());
        incomeTicker.setAnimationDuration(1000);//设置动画的持续时间
        incomeTicker.setAnimationInterpolator(new OvershootInterpolator());

        viewPager = findViewById(R.id.view_pager);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(),MainActivity.this);
        mainViewPagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(mainViewPagerAdapter);
        viewPager.setOnPageChangeListener(this);
        viewPager.setCurrentItem(mainViewPagerAdapter.getLastIndex());
        viewPager.setOffscreenPageLimit(mainViewPagerAdapter.getCount());

        setHeader();

        findViewById(R.id.addbill).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddRecordActivity.class);
                startActivityForResult(intent,FLAG);
            }
        });
    }

    public void isFirstDayOfMonth(){
        String datetime = DateUtil.getFormattedDate();
        String[] temp =  datetime.split("-");
        if (temp[2].equals("1")){
            GlobalUtil.getInstance().setContext(this);
            GlobalUtil.getInstance().dataBaseHelper.deleteAllInRecord();
        }

    }



    public void setHeader(){
        String date = mainViewPagerAdapter.getDate(currentPage);
        int outcome = GlobalUtil.getInstance().dataBaseHelper.getSumByType(1,date);
        int income = GlobalUtil.getInstance().dataBaseHelper.getSumByType(2,date);
        if (outcome!=-1){
            outcomeTicker.setText(String.valueOf(outcome));
        }
        if (income!=-1){
            incomeTicker.setText(String.valueOf(income));
        }
        week.setText(DateUtil.getWeekDay(date));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mainViewPagerAdapter.reload();
        setHeader();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        setHeader();
    }

    @Override
    public void onPageSelected(int position) {
        currentPage = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}