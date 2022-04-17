package com.example.asexperiment_end.adapter.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asexperiment_end.Bean.CategoryResBean;
import com.example.asexperiment_end.R;
import com.example.asexperiment_end.Utils.GlobalUtil;
import com.example.asexperiment_end.adapter.CategoryRecyclerAdapter;

import java.util.LinkedList;

public class OutcomeFragment extends MyFragment {
    private View layoutInflater;//用于表示其填充的layout
    private RecyclerView out_recyclerView;
    private LinkedList<CategoryResBean> resBeans = GlobalUtil.getInstance().getCostRes();


//    private CategoryRecyclerAdapter categoryRecyclerAdapter;

//    public CategoryRecyclerAdapter getCategoryRecyclerAdapter() {
//        return categoryRecyclerAdapter;
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutInflater = inflater.inflate(R.layout.outcome_layout,
                container, false);
        Log.d("日志信息","Outcome新建");

        initRecyclerView();
        return layoutInflater;
    }

    private void initRecyclerView(){
        Log.d("日志信息","==============");

        out_recyclerView = layoutInflater.findViewById(R.id.outcome_recyclerView);
        setCategoryRecyclerAdapter(new CategoryRecyclerAdapter(getActivity(),resBeans));
        out_recyclerView.setAdapter(getCategoryRecyclerAdapter());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        out_recyclerView.setLayoutManager(gridLayoutManager);
        getCategoryRecyclerAdapter().notifyDataSetChanged();

    }
}
