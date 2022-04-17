package com.example.asexperiment_end.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asexperiment_end.Bean.CategoryResBean;
import com.example.asexperiment_end.Bean.RecordBean;
import com.example.asexperiment_end.R;
import com.example.asexperiment_end.Utils.DateUtil;
import com.example.asexperiment_end.Utils.GlobalUtil;

import org.w3c.dom.Text;

import java.util.LinkedList;

public class ListViewAdapter extends BaseAdapter {

    private LinkedList<RecordBean> records = new LinkedList<>();
    private LayoutInflater layoutInflater;
    private Context context;

    public ListViewAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(this.context);
    }

    public void setData(LinkedList<RecordBean> records){
        this.records = records;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int i) {
        return records.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecordBean record = (RecordBean) getItem(position);
        View itemView;
        ViewHolder viewHolder;
        if (convertView==null){
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.remarkTV = itemView.findViewById(R.id.textView_remark);
            viewHolder.amountTV = itemView.findViewById(R.id.textView_amount);
            viewHolder.timeTV = itemView.findViewById(R.id.textView_time);
            viewHolder.categoryIcon = itemView.findViewById(R.id.imageView_category);
            itemView.setTag(viewHolder);
        }else {
            itemView = convertView;
            viewHolder = (ViewHolder) itemView.getTag();
        }
        viewHolder.remarkTV.setText(record.getRemark());

        if (record.getType() == 1){
            viewHolder.amountTV.setText("- "+record.getMoney());
        } else {
            viewHolder.amountTV.setText("+ "+record.getMoney());
        }

        viewHolder.timeTV.setText(DateUtil.getFormattedTime(record.getTimesstamp()));
        viewHolder.categoryIcon.setImageResource(GlobalUtil.getInstance().getResourceIcon(record.getCategory()));
        return itemView;
    }
}

class ViewHolder{
    TextView remarkTV;
    TextView amountTV;
    TextView timeTV;
    ImageView categoryIcon;

}
