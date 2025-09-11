package org.example.application.framework;


import com.google.common.collect.FluentIterable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.example.application.R;

public class ItemViewHolder extends AbstractItemViewHolder {
    LinearLayout container;
    TextView name;
    TextView success_count;
    TextView total;
    CheckBox selected;

    Item item;

    public ItemViewHolder(Context context, ViewGroup parent, Item item) {
        // Inflate the XML layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        
        // Get references to the views
        this.container = (LinearLayout) view;
        this.name = view.findViewById(R.id.iv_name);
        this.success_count = view.findViewById(R.id.iv_total);
        this.total = view.findViewById(R.id.iv_total);
        this.selected = view.findViewById(R.id.iv_selected);

        this.item = item;

        selected.setOnCheckedChangeListener((buttonView, isChecked) -> {
            this.item.allLeaf().forEach(i -> {
                MethodItem casted = (MethodItem)i;
                casted.selected = isChecked;
            });
        });
        
    }

    @Override
    public View getView() {
        return this.container;
    }

    @Override
    public void bind() {
        name.setText(item.name);
        total.setText("/" + (item.endIndex - item.startIndex));
        selected.setChecked(
            FluentIterable.from(item.allLeaf())
            .allMatch(i -> {
                MethodItem casted = (MethodItem)i;
                return casted.selected;
            })
        );
    }
}


