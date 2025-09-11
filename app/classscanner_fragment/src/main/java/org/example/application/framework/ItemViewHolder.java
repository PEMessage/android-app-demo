package org.example.application.framework;


import com.google.common.collect.FluentIterable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.stream.StreamSupport;

import org.example.application.R;
import org.example.application.framework.MethodItem.State;

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
        this.success_count = view.findViewById(R.id.iv_success_count);
        this.total = view.findViewById(R.id.iv_total);
        this.selected = view.findViewById(R.id.iv_selected);

        this.item = item;

        selected.setOnCheckedChangeListener((buttonView, isChecked) -> {
            FluentIterable.from(this.item.allLeaf())
                .transform(MethodItem.class::cast)
                .forEach(i -> i.selected = isChecked);
        });
    }

    @Override
    public View getView() {
        return this.container;
    }

    @Override
    public void bind() {
        name.setText(item.name);
        success_count.setText(String.valueOf(
                StreamSupport.stream(item.allLeaf().spliterator(), false)
                    .map(MethodItem.class::cast)
                    .mapToInt(i -> i.state == State.SUCCESS ? 1 : 0)
                    .sum()
        ));
        total.setText("/" + (item.endIndex - item.startIndex));
        selected.setChecked(
            FluentIterable.from(item.allLeaf())
            .transform(MethodItem.class::cast)
            .allMatch(i -> i.selected)
        );
    }
}


