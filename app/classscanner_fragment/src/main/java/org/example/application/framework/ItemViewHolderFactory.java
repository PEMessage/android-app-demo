package org.example.application.framework;

import android.content.Context;
import android.view.ViewGroup;

public class ItemViewHolderFactory {
    static public AbstractItemViewHolder createViewHolder(Context context,  ViewGroup parent, Item item) {
        if (item instanceof MethodItem) {
            return new MethodItemViewHolder(context, parent, (MethodItem)item);
        } else {
            return new ItemViewHolder(context, parent, item);
        }
    }
}
