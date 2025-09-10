package org.example.application.framework;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.graph.Traverser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Item {
    public String name;
    public Item parent = null;
    public SortedMap<String, Item> children = new TreeMap<String, Item>();

    // Extra state
    public int depth = 0;
    public int leaf = 1;

    public Item(String name) {
        this.name = name;
    }

    public void addChild(Item child) {
        Preconditions.checkArgument(!children.containsKey(child.name),
            "Item with name '%s' already exists", child.name);
        child.parent = this;

        // Before Put
        // Extra state1: depth
        Traverser.<Item>forTree(item -> item.children.values())
            .breadthFirst(child)
            .forEach(item -> {
                item.depth += this.depth + 1;
            });

        children.put(child.name, child);

        // After Put
        // Extra state2: leaf
        for (Item ancestor: this.parentChain()) {
            int prev = ancestor.leaf;
            ancestor.leaf = 0;
            for (Item c : this.children.values()) {
                ancestor.leaf += c.leaf;
            }
            // Fast path, skip spread up
            if (prev == ancestor.leaf) {
                break;
            }
        }
    }

    public Iterable<Item> parentChain() {
        return Traverser.<Item>forTree(item -> {
            if (item.parent != null) {
                return ImmutableList.of(item.parent);
            }
            return ImmutableList.of();
        }).breadthFirst(this);
    }

    public List<Item> getPath() {
        List<Item> path = new ArrayList<>();
        parentChain().forEach(v -> path.add(0,v));
        return path;
    }

    public List<String> getPathString() {
        return getPath().stream()
                   .map(x -> x.name)
                   .collect(Collectors.toList());

    }

    public String toStringAll() {
        return toStringAll(0);
    }

    public String toStringAll(int indent) {
        StringBuilder sb = new StringBuilder();

        // Use Traverser for breadth-first traversal
        Traverser.<Item>forTree(item -> item.children.values())
            .breadthFirst(this)
            .forEach(item -> {
                // Calculate indentation based on depth
                int depth = item.depth - this.depth;
                int currentIndent = indent + (depth * 2);

                // Add indentation
                for (int i = 0; i < currentIndent; i++) {
                    sb.append(" ");
                }

                // Add item representation
                sb.append(item.toString());
                sb.append("\n");
            });

        return sb.toString();
    }

    public String toString() {
        return "Item{name='" + name + "'}: " + String.join(".", getPathString()) 
        + " @depth=" + this.depth 
        + " @leaf=" + this.leaf;
    }
}
