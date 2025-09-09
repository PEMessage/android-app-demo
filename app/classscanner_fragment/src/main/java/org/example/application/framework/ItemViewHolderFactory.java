package org.example.application.framework;

import android.content.Context;
import android.view.ViewGroup;

public class ItemViewHolderFactory {
    static public AbstractItemViewHolder createViewHolder(Context context,  ViewGroup parent, Item item) {
        if (false) {
            // TODO: different case for derive class of
            return null;
        } else {
            return new ItemViewHolder(context, parent, item);
        }
    }
}
