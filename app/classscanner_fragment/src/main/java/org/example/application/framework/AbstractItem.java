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

public abstract class AbstractItem {
    public String name;
    public AbstractItem parent = null;
    public SortedMap<String, AbstractItem> children = new TreeMap<String, AbstractItem>();

    // Extra state
    public int depth = 0;
    public int leaf = 1;

    public AbstractItem(String name) {
        this.name = name;
    }

    public void addChild(AbstractItem child) {
        Preconditions.checkArgument(!children.containsKey(child.name),
            "AbstractItem with name '%s' already exists", child.name);
        child.parent = this;

        // Before Put
        // Extra state1: depth
        Traverser.<AbstractItem>forTree(item -> item.children.values())
            .breadthFirst(child)
            .forEach(item -> {
                item.depth += this.depth + 1;
            });

        children.put(child.name, child);

        // After Put
        // Extra state2: leaf
        for (AbstractItem ancestor: this.parentChain()) {
            int prev = ancestor.leaf;
            ancestor.leaf = 0;
            for (AbstractItem c : this.children.values()) {
                ancestor.leaf += c.leaf;
            }
            // Fast path, skip spread up
            if (prev == ancestor.leaf) {
                break;
            }
        }
    }


    public Iterable<AbstractItem> parentChain() {
        return Traverser.<AbstractItem>forTree(item -> {
            if (item.parent != null) {
                return ImmutableList.of(item.parent);
            }
            return ImmutableList.of();
        }).breadthFirst(this);
    }

    public List<AbstractItem> getPath() {
        List<AbstractItem> path = new ArrayList<>();
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
        Traverser.<AbstractItem>forTree(item -> item.children.values())
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
        return "AbstractItem{name='" + name + "'}: " + String.join(".", getPathString()) 
        + " @depth=" + this.depth 
        + " @leaf=" + this.leaf;
    }
}
