package org.example.application.framework;
import java.util.Map;
import java.util.List;


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

    public static Item findItemByPath(Item item, List<String> rpath) {
        if (item == null || rpath == null || rpath.isEmpty()) {
            return item;
        }

        Item current = item;

        for (String pathPart : rpath) {
            if (current.children.containsKey(pathPart)) {
                current = current.children.get(pathPart);
            } else {
                return null;
            }
        }

        return current;
    }


    public static Item findItemByAbsPath(Item rootItem, List<String> abspath) {
        if (rootItem == null || abspath == null || abspath.isEmpty() || abspath.get(0) != "root") {
            return rootItem;
        }

        return findItemByPath(rootItem, abspath.subList(1, abspath.size()));
    }
}

