package com.example.asexperiment_end.Utils;

import android.content.Context;

import com.example.asexperiment_end.Bean.CategoryResBean;
import com.example.asexperiment_end.DB.DataBaseHelper;
import com.example.asexperiment_end.MainActivity;
import com.example.asexperiment_end.R;

import java.util.LinkedList;

public class GlobalUtil {
    private static final String TAG = "GlobalUtil";

    private static GlobalUtil instance;

    private Context context;
    public DataBaseHelper dataBaseHelper;
    private MainActivity mainActivity;


    private boolean iscostload = false;
    private boolean isearnload = false;

    //存放支出图标
    private LinkedList<CategoryResBean> costRes = new LinkedList<>();
    //存放进账图标
    private LinkedList<CategoryResBean> earnRes = new LinkedList<>();

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setContext(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context,DataBaseHelper.DB_NAME,null,1);
            getEarnRes();
            getCostRes();
    }

    private static int [] costIconRes = {
            R.drawable.icon_general_white, R.drawable.icon_food_white,
            R.drawable.icon_drinking_white,R.drawable.icon_groceries_white,
            R.drawable.icon_shopping_white,R.drawable.icon_personal_white,
            R.drawable.icon_entertain_white,R.drawable.icon_movie_white,
            R.drawable.icon_social_white, R.drawable.icon_transport_white,
            R.drawable.icon_appstore_white,R.drawable.icon_mobile_white,
            R.drawable.icon_computer_white,R.drawable.icon_gift_white,
            R.drawable.icon_house_white, R.drawable.icon_travel_white,
            R.drawable.icon_ticket_white,  R.drawable.icon_book_white,
            R.drawable.icon_medical_white,R.drawable.icon_transfer_white
    };
    private static String[] costTitle = { "总体","食物", "饮料","杂货", "购物", "个人","娱乐","电影", "社交", "交通",
            "应用","手机","电脑","礼物", "家居", "旅游","门票","书", "医疗","车票"};


    private static int[] earnIconRes = {
            R.drawable.icon_general_white,R.drawable.icon_reimburse_white,
            R.drawable.icon_salary_white,R.drawable.icon_redpocket_white,
            R.drawable.icon_parttime_white,R.drawable.icon_bonus_white,
            R.drawable.icon_investment_white};

    private static String[] earnTitle = {"总体", "报销", "工资","红包","兼职", "奖金","投资"};

    //只是提供一个单实例
    public static GlobalUtil getInstance(){
        if (instance==null){
            instance = new GlobalUtil();
        }
        return instance;
    }

     public LinkedList<CategoryResBean> getCostRes() {
        if(!iscostload){
            for(int i=0;i<costTitle.length;i++){
                CategoryResBean categoryResBean = new CategoryResBean();
                categoryResBean.setTitle(costTitle[i]);
                categoryResBean.setResWhite(costIconRes[i]);
                costRes.add(categoryResBean);
            }
        }
        this.iscostload = true;
        return costRes;
    }

    public LinkedList<CategoryResBean> getEarnRes() {
        if (!isearnload){
            for(int i=0;i<earnTitle.length;i++){
                CategoryResBean categoryResBean = new CategoryResBean();
                categoryResBean.setTitle(earnTitle[i]);
                categoryResBean.setResWhite(earnIconRes[i]);
                earnRes.add(categoryResBean);
            }
        }
        this.isearnload = true;
        return earnRes;
    }

    public int getResourceIcon(String category){
        for (CategoryResBean res :
                costRes) {
            if (res.getTitle().equals(category)){
                return res.getResWhite();
            }
        }

        for (CategoryResBean res :
                earnRes) {
            if (res.getTitle().equals(category)){
                return res.getResWhite();
            }
        }

        return costRes.get(0).getResWhite();
    }
}
