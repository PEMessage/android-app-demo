package org.example.application.framework;
import java.util.Map;


public class ItemUtils {
    static public Item flattenSingleChildChains(Item item) {
        // If item only has one child, descend until we find an item that doesn't have exactly one child
        Item current = item;

        while (current.children != null && current.children.size() == 1) {
            String firstKey = current.children.firstKey();
            current = current.children.get(firstKey);
        }
        return current;
    }

    static public void addChildChain(Item root, String[] rpath, Item child) {
        Item current = root;

        for (String segment : rpath) {
            if (!current.children.containsKey(segment)) {
                Item newItem = new Item(segment);
                current.addChild(newItem);
            }
            current = current.children.get(segment);
        }
        current.addChild(child);
    }
}

