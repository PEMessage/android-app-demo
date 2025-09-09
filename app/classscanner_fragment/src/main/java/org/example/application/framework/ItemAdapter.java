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
    
    public ItemAdapter(Context context, Item item) {
        this.context = context;
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
        AbstractItemViewHolder holder;
        Item item = getItem(position);
        
        if (convertView == null) {
            holder = ItemViewHolderFactory.createViewHolder(context, parent, item);
            convertView = holder.getView();
            convertView.setTag(holder);
        } else {
            holder = (ItemViewHolder) convertView.getTag();
        }
        
        holder.bind();
        return convertView;
    }
}
