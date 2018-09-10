package com.dingyl.xiaominote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.dingyl.xiaominote.R;
import com.dingyl.xiaominote.data.BaseData;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private ArrayList<BaseData> arrayList;
    private Context context;
    private LayoutInflater inflater;

    public GridAdapter(Context context, ArrayList<BaseData> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            view = inflater.inflate(R.layout.grid_list_item,null,false);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.title);
            holder.title = view.findViewById(R.id.title);
            holder.contentSummary = view.findViewById(R.id.contentSummary);
            holder.dateTime = view.findViewById(R.id.date_time);
            holder.checkBox = view.findViewById(R.id.is_checked);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(arrayList.get(i).getTitle());
        if(!arrayList.get(i).getContentSummary().equals("")){
            holder.contentSummary.setVisibility(View.VISIBLE);
            holder.contentSummary.setText(arrayList.get(i).getContentSummary());
        }else {
            holder.contentSummary.setVisibility(View.GONE);
        }
        holder.dateTime.setText(arrayList.get(i).getDate());
        return view;
    }

    class ViewHolder{
        TextView title;
        TextView contentSummary;
        TextView dateTime;
        CheckBox checkBox;
    }
}
