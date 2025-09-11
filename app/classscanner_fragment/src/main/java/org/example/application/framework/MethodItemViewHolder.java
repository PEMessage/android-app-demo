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
            this.item.state = State.RUNNING;
            bind();
            try {

                Class clazz = ((ClassItem)(this.item.parent)).mClass;
                Object obj = clazz.newInstance(); // TODO: do not using depercate API
                this.item.mMethod.invoke(obj);

                this.item.state = State.SUCCESS;
            } catch (Exception e) {
                this.item.state = State.FAIL;
                e.printStackTrace();
            }
            bind();

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
        container.setBackgroundColor(getColor(item.state));
    }


    private static int getColor(State st) {
        switch (st) {
            case NOT_RUN: return 0x00000000;
            case RUNNING: return 0x00000000;
            case SUCCESS: return 0XBFACFF71;   // Green
            case FAIL:    return 0XBFF65866;   // Red
            default: return 0x00000000;
        }
    }

}


