package org.example.application.framework;

import android.widget.TextView;

import android.view.View;
import android.view.ViewGroup;

import android.content.Context;

public class ItemViewHolder extends AbstractItemViewHolder {
    TextView textView;
    Item item;

    public ItemViewHolder(Context context, ViewGroup parent, Item item) {
        this.textView = new TextView(context);
        this.item = item;
    }

    public View getView() {
        return this.textView;
    }


    public void bind() {
        textView.setText(item.name);
    }
}
