package com.example.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import dalvik.system.BaseDexClassLoader;

public class MyCustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Planet> items;

    public MyCustomAdapter(Context context, ArrayList<Planet> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomView holder;

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.spinner, null);
            holder = new CustomView();
            holder.name = convertView.findViewById(R.id.name);
            holder.desc = convertView.findViewById(R.id.desc);
            holder.imageView = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (CustomView) convertView.getTag();
        }
        holder.name.setText(items.get(position).name);
        holder.desc.setText(String.valueOf(items.get(position).desc));
        holder.imageView.setImageResource(items.get(position).imageView);

        return convertView;
    }

    public static class CustomView {
        TextView name;
        TextView desc;
        ImageView imageView;
    }
}
