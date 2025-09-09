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
}

