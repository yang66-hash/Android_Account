package com.example.asexperiment_end;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.asexperiment_end.Bean.RecordBean;
import com.example.asexperiment_end.Utils.GlobalUtil;
import com.example.asexperiment_end.adapter.CategoryRecyclerAdapter;
import com.example.asexperiment_end.adapter.ViewPagerAdapter;
import com.example.asexperiment_end.adapter.fragments.IncomeFragment;
import com.example.asexperiment_end.adapter.fragments.MyFragment;
import com.example.asexperiment_end.adapter.fragments.OutcomeFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddRecordActivity extends AppCompatActivity implements View.OnClickListener,CategoryRecyclerAdapter.OnCategoryClickListener {

    private static String ATG = "AddRecordActivity";
    private StringBuffer buffer=new StringBuffer();

    private TabLayout tabLayout;
    private ViewPager viewPager;
    String[] titleArray = new String[]{"支出","收入"};
    private List<Fragment> fragments = new ArrayList<>();
    private EditText editText;
    private boolean isedit = false;

    private TextView amountText;
    private String category = "General";
    private String remark = category;
    private RecordBean.RecordType type = RecordBean.RecordType.RECORD_TYPE_EXPENSE;

    private CategoryRecyclerAdapter categoryRecyclerAdapter;

    private RecordBean recordBean = new RecordBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //软键盘会直接覆盖屏幕
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_add_record);
        tabLayout = findViewById(R.id.in_out_title);
        viewPager = findViewById(R.id.in_out_viewpager);
        fragments.add(new OutcomeFragment());
        fragments.add(new IncomeFragment());
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),fragments,titleArray);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        amountText = findViewById(R.id.textView_amount);
        editText = findViewById(R.id.editremark);

        findViewById(R.id.keyboard_one).setOnClickListener(this);
        findViewById(R.id.keyboard_two).setOnClickListener(this);
        findViewById(R.id.keyboard_three).setOnClickListener(this);
        findViewById(R.id.keyboard_four).setOnClickListener(this);
        findViewById(R.id.keyboard_five).setOnClickListener(this);
        findViewById(R.id.keyboard_six).setOnClickListener(this);
        findViewById(R.id.keyboard_seven).setOnClickListener(this);
        findViewById(R.id.keyboard_eight).setOnClickListener(this);
        findViewById(R.id.keyboard_nine).setOnClickListener(this);
        findViewById(R.id.keyboard_zero).setOnClickListener(this);
        findViewById(R.id.keyboard_point).setOnClickListener(this);
        findViewById(R.id.keyboard_back).setOnClickListener(this);
        findViewById(R.id.keyboard_done).setOnClickListener(this);
        findViewById(R.id.keyboard_clean).setOnClickListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                categoryRecyclerAdapter = ((MyFragment)fragments.get(position)).getCategoryRecyclerAdapter();
                categoryRecyclerAdapter.setOnCategoryClickListener(AddRecordActivity.this);
                categoryRecyclerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPageSelected(int position) {
                categoryRecyclerAdapter = ((MyFragment)fragments.get(position)).getCategoryRecyclerAdapter();
                if (position==0)
                    type = RecordBean.RecordType.RECORD_TYPE_EXPENSE;
                else
                    type = RecordBean.RecordType.RECORD_TYPE_INCOME;
                categoryRecyclerAdapter.setOnCategoryClickListener(AddRecordActivity.this);
                categoryRecyclerAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        RecordBean temp = (RecordBean) getIntent().getSerializableExtra("record");
        if (temp!=null){
            isedit =true;
            this.recordBean = temp;
        }



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.keyboard_point:
                Log.d("调试日志","case执行");
                handlePoint(view);
                break;
            case R.id.keyboard_back:
                handleBack(view);
                break;
            case R.id.keyboard_clean:
                handleClean(view);
                break;
            case R.id.keyboard_done:
                handleDone(view);
                break;
            case R.id.keyboard_zero:
                //开头只能是一个0
            case R.id.keyboard_one:
            case R.id.keyboard_two:
            case R.id.keyboard_three:
            case R.id.keyboard_four:
            case R.id.keyboard_five:
            case R.id.keyboard_six:
            case R.id.keyboard_seven:
            case R.id.keyboard_eight:
            case R.id.keyboard_nine:
                if(buffer.length()==1&&buffer.charAt(0)=='0')
                    Toast.makeText(this,"金额格式不符规范，请清空重试",Toast.LENGTH_SHORT).show();
                else handleNum(view);
                break;
            default:
                break;
        }
    }

    private void handleNum(View view){
        Button button = (Button) view;
        String input =button.getText().toString();
        String result = buffer.toString();
        Log.d("调试日志",buffer.toString());
        if(result.contains(".")){
            String[] temp = buffer.toString().split("\\.");
            Log.d("调试日志", Arrays.toString(temp));
            if (result.split("\\.").length == 1 || result.split("\\.")[1].length() < 2) {
                buffer.append(input);
            }
        }
        else {
            buffer.append(input);
        }
        Log.d("调试日志",buffer.toString());
        refreshText();
    }

    private void handlePoint(View view) {
        if(buffer.length()==0){
            buffer.append("0.");
            Log.d("调试日志",buffer.toString());
        }
        else {
            if(!buffer.toString().contains(".")){
                buffer.append(".");
            }
        }
        refreshText();
    }

    private void handleBack(View view) {
        finish();
    }

    private void handleDone(View view) {
        editText = findViewById(R.id.editremark);
        if (editText.getText().toString().equals("")){
            remark = category;
        }
        else
            remark = editText.getText().toString();
        Log.d("调试日志记录的备注",remark);
        Log.d("调试日志记录的category",category);
        Log.d("调试日志记录的type",type.toString());
        Log.d("日志信息",editText.getText().toString());

        if (buffer.length()!=0){
            this.recordBean.setMoney(Double.valueOf(buffer.toString()));
            this.recordBean.setType(type);
            this.recordBean.setRemark(remark);
            this.recordBean.setCategory(category);
            GlobalUtil.getInstance().setContext(this);
            if (isedit){
                GlobalUtil.getInstance().dataBaseHelper.editRecord(recordBean.getUuid(),recordBean);
            }else
                GlobalUtil.getInstance().dataBaseHelper.addRecord(recordBean);

            finish();
        }else {
            Toast.makeText(getApplicationContext(), "金额忘记了呀", Toast.LENGTH_SHORT).show();
        }
    }


    private void handleClean(View view) {
        if(buffer.length()!=0)
            buffer.deleteCharAt(buffer.length()-1);
        refreshText();
    }

    private void refreshText(){
        String showtext = buffer.toString();
        if (showtext.contains(".")){
            if(showtext.split("\\.").length==1){
                amountText.setText(showtext+"00");
            }else if(showtext.split("\\.")[1].length()==1){
                amountText.setText(showtext+"0");
            }else{
                amountText.setText(showtext);
            }
        }
        else {
            if (showtext.equals("")) {
                amountText.setText("0.00");

            } else {
                amountText.setText(showtext + ".00");

            }
        }
    }

    @Override
    public void onClikCategory(String category) {
        this.category = category;
    }


}
