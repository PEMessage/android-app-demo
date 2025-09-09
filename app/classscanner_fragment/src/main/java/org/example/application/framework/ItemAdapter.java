package org.example.application.framework;


import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class ItemAdapter extends BaseAdapter {
    
    protected Context context;
    protected Item item;
    protected LayoutInflater inflater;
    
    public ItemAdapter(Context context, Item item) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.item = item;
    }
    
    
    @Override
    public int getCount() {
        return item.children.size();
    }
    
    @Override
    public Item getItem(int position) {
        return item.children.values().stream()
        .skip(position)
        .findFirst()
        .orElse(null);
    }
    
    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Item item = getItem(position);
        
        if (convertView == null) {
            convertView = new TextView(context);
            holder = createViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        bindViewHolder(holder, item);
        return convertView;
    }
    
    protected ViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }
    
    protected void bindViewHolder(ViewHolder holder, Item item) {
        TextView textView = holder.textView;

        textView.setText(item.name);
        // textView.setOnClickListener(v -> {
        //     Log.i("Click", item.toStringAll());
        //     Toast.makeText(context, item.toString(), Toast.LENGTH_SHORT).show();
        // });
    }
    
    // Base ViewHolder class
    public static class ViewHolder {
        public TextView textView;
        
        public ViewHolder(View view) {
            textView = (TextView)view;
        }
    }
    
}
