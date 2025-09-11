package org.example.application.framework;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

import org.example.application.R;
import static org.example.application.Config.TAG;
import org.example.application.framework.MethodItem.State;

public class MethodItemViewHolder extends AbstractItemViewHolder {
    LinearLayout container;
    TextView name;
    CheckBox selected;

    MethodItem item;

    public MethodItemViewHolder(Context context, ViewGroup parent, MethodItem item) {
        View view = LayoutInflater.from(context).inflate(R.layout.method_item_view, parent, false);
        
        // Get references to the views
        this.container = (LinearLayout) view;
        this.name = view.findViewById(R.id.miv_name);
        this.selected = view.findViewById(R.id.miv_selected);

        this.item = item;

        selected.setOnCheckedChangeListener((buttonView, isChecked) -> {
            this.item.selected = isChecked;
        });

        container.setOnClickListener(v -> {
            try  {
                ClassItem classItem = (ClassItem)(item.parent);
                Object obj = classItem.mClass.newInstance(); // TODO: do not using depercate API
                item.mMethod.invoke(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public View getView() {
        return this.container;
    }

    @Override
    public void bind() {
        name.setText(item.name);
        selected.setChecked(item.selected);
    }
}


