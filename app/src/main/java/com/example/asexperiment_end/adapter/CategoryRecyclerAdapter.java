package com.example.asexperiment_end.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asexperiment_end.Bean.CategoryResBean;
import com.example.asexperiment_end.R;


import java.util.LinkedList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private static final String TAG ="CategoryRecyclerAdapter";

    private LayoutInflater layoutInflater;
    private Context context;
    private LinkedList<CategoryResBean> celllist;
    private String selected = "";

    public String getSelected(){
        return selected;
    }

    private OnCategoryClickListener onCategoryClickListener;

    public void setOnCategoryClickListener(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }

    public CategoryRecyclerAdapter(Context context, LinkedList<CategoryResBean> celllist){
        Log.d("日志信息","新建CategoryRecyclerAdapter");
        this.context = context;
        this.celllist = celllist;
        selected = celllist.get(0).getTitle();
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("日志信息","onCreateViewHolder执行");

        View view = layoutInflater.inflate(R.layout.cell_category,parent,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final CategoryResBean resBean = celllist.get(position);
        holder.imageView.setImageResource(resBean.getResWhite());
        holder.textView.setText(resBean.getTitle());
        //放置每个巢穴的监听事件
        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected = resBean.getTitle();
                Log.d("日志信息","坚挺到点击事件");
                notifyDataSetChanged();

                if (onCategoryClickListener!=null){
                    onCategoryClickListener.onClikCategory(resBean.getTitle());

                }
            }
        });
        if (holder.textView.getText().toString().equals(selected)){
            holder.background.setBackgroundResource(R.drawable.bg_edit_text);
        }else {
            holder.background.setBackgroundResource(R.color.teal_200);
        }
    }

    @Override
    public int getItemCount() {
        return celllist.size();
    }


    public interface OnCategoryClickListener{
        void onClikCategory(String category);
    }

}
class CategoryViewHolder extends RecyclerView.ViewHolder{
    RelativeLayout background;
    ImageView imageView;
    TextView textView;
    public CategoryViewHolder(View view){
        super(view);
        background = view.findViewById(R.id.cell_background);
        imageView = itemView.findViewById(R.id.imageView_category);
        textView = itemView.findViewById(R.id.textView_category);
    }
}