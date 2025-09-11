package org.example.application.framework;


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
    TextView total;
    TextView success_count;
    CheckBox selected;

    Item item;

    public ItemViewHolder(Context context, ViewGroup parent, Item item) {
        // Inflate the XML layout
        View view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false);
        
        // Get references to the views
        this.container = (LinearLayout) view;
        this.name = view.findViewById(R.id.iv_name);
        this.total = view.findViewById(R.id.iv_total);
        this.success_count = view.findViewById(R.id.iv_total);
        this.selected = view.findViewById(R.id.iv_selected);
        
        // Additional configuration that might not be in XML
        // selected.setClickable(true);
        
        this.item = item;
    }

    public View getView() {
        return this.container;
    }

    public void bind() {
        name.setText(item.name);
        // You can also bind checkbox state if needed, e.g.:
        // checkBox.setChecked(item.isChecked);
    }
}


