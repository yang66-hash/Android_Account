package com.example.asexperiment_end.adapter.fragments;

import androidx.fragment.app.Fragment;

import com.example.asexperiment_end.adapter.CategoryRecyclerAdapter;

public abstract class MyFragment extends Fragment {

    private CategoryRecyclerAdapter categoryRecyclerAdapter;

    public CategoryRecyclerAdapter getCategoryRecyclerAdapter() {
        return categoryRecyclerAdapter;
    }

    public void setCategoryRecyclerAdapter(CategoryRecyclerAdapter categoryRecyclerAdapter) {
        this.categoryRecyclerAdapter = categoryRecyclerAdapter;
    }
}
