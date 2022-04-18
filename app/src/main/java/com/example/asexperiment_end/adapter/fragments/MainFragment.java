package com.example.asexperiment_end.adapter.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.asexperiment_end.Bean.RecordBean;
import com.example.asexperiment_end.R;
import com.example.asexperiment_end.Utils.DateUtil;
import com.example.asexperiment_end.Utils.GlobalUtil;
import com.example.asexperiment_end.Utils.Utility;
import com.example.asexperiment_end.adapter.ListViewAdapter;

import java.util.LinkedList;

public class MainFragment extends Fragment implements AdapterView.OnItemLongClickListener {
    private View rootView;
    private TextView textView;
    private ListView listView;
    private ListViewAdapter listViewAdapter;

    private LinkedList<RecordBean> records = new LinkedList<>();

    private String date = "";

    public MainFragment(String date) {
        this.date = date;
        records = GlobalUtil.getInstance().dataBaseHelper.readRecords(date);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //rootView = inflater.inflate(R.layout.fragment_show,container,false);
        rootView = View.inflate(getContext(),R.layout.fragment_show,null);
        initView();
        return rootView;
    }

    private void initView() {

        textView = rootView.findViewById(R.id.day_inout);
        listView = rootView.findViewById(R.id.listView);
        listViewAdapter = new ListViewAdapter(getActivity());
        listViewAdapter.setData(records);
        listView.setAdapter(listViewAdapter);
        Utility.setListViewHeightBasedOnChildren(listView);

        if (listViewAdapter.getCount()>0){
            rootView.findViewById(R.id.no_record_layout).setVisibility(View.INVISIBLE);
        }
        textView.setText(DateUtil.getDateTitle(date));

        listView.setOnItemLongClickListener(this);
    }

    public void reload(){

        records = GlobalUtil.getInstance().dataBaseHelper.readRecords(date);
        if (listViewAdapter==null){
            listViewAdapter = new ListViewAdapter(getContext());
        }
        listViewAdapter.setData(records);
        listView.setAdapter(listViewAdapter);

        if (listViewAdapter.getCount()>0){
            rootView.findViewById(R.id.no_record_layout).setVisibility(View.INVISIBLE);

        }
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        getDialog(position);

        return false;
    }
    private void getDialog(int position){
        final String listItem[] = {"删除","修改"};
        AlertDialog.Builder listDialog  = new AlertDialog.Builder(getContext());
        listDialog.setTitle("请选择相应操作");
        listDialog.setItems(listItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i==0){
                    Toast.makeText(getContext(),listItem[0],Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),listItem[0],Toast.LENGTH_SHORT).show();
                }
            }
        });
        listDialog.create().show();
    }
}
