package com.example.asexperiment_end.adapter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asexperiment_end.Bean.CategoryResBean;
import com.example.asexperiment_end.R;
import com.example.asexperiment_end.Utils.GlobalUtil;
import com.example.asexperiment_end.adapter.CategoryRecyclerAdapter;

import java.util.LinkedList;

public class IncomeFragment extends MyFragment {
    private View view;//fragment的layout填充
    private RecyclerView recyclerView;
//    private CategoryRecyclerAdapter adapter;
    private LinkedList<CategoryResBean> earnBeans = GlobalUtil.getInstance().getEarnRes();
    private Context context;




    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.income_layout,
                container, false);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView(){
        recyclerView = view.findViewById(R.id.income_recyclerView);
        setCategoryRecyclerAdapter(new CategoryRecyclerAdapter(getActivity(),earnBeans));
        recyclerView.setAdapter(getCategoryRecyclerAdapter());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),4);
        recyclerView.setLayoutManager(gridLayoutManager);
        getCategoryRecyclerAdapter().notifyDataSetChanged();

    }

}
