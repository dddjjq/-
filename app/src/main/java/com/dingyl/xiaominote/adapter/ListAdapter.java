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

public class ListAdapter extends BaseAdapter{

    private ArrayList<BaseData> arrayList;
    private Context context;
    private LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList<BaseData> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item,null,false);
            holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.contentSummary = convertView.findViewById(R.id.contentSummary);
            holder.dateTime = convertView.findViewById(R.id.date_time);
            holder.checkBox = convertView.findViewById(R.id.is_checked);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(arrayList.get(position).getTitle());
        if(!arrayList.get(position).getContentSummary().equals("")){
            holder.contentSummary.setVisibility(View.VISIBLE);
            holder.contentSummary.setText(arrayList.get(position).getContentSummary());
        }else {
            holder.contentSummary.setVisibility(View.GONE);
        }
        holder.dateTime.setText(arrayList.get(position).getDate());
        return convertView;
    }

    class ViewHolder{
        TextView title;
        TextView contentSummary;
        TextView dateTime;
        CheckBox checkBox;
    }
}
