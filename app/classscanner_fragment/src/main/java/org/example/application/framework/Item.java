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


    // Segment Tree relate
    public static List<Item> leafs = new ArrayList<>(); // singleinstance of underlay index
    public int startIndex = -1;
    public int endIndex = -1;
    public boolean addLock = false;

    public Item(String name) {
        this.name = name;
    }

    public void addChild(Item child) {
        Preconditions.checkArgument(!children.containsKey(child.name),
            "Item with name '%s' already exists", child.name);
        assert addLock == false;
        child.parent = this;

        // Before Put
        // Extra state1: depth
        Traverser.<Item>forTree(item -> item.children.values())
            .breadthFirst(child)
            .forEach(item -> {
                item.depth += this.depth + 1;
            });

        children.put(child.name, child);

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
            .depthFirstPreOrder(this) // DFS instead of BFS(breadthFirst)
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
        + " @s=" + this.startIndex
        + " @e=" + this.endIndex;
    }


    public boolean isLeaf() {
        return startIndex == endIndex;
    }
    public boolean isSegmentInited() {
        return startIndex != -1 && endIndex != -1;
    }
    public void asLeaf() {
        assert !isSegmentInited();
        assert children.size() == 0;
        assert addLock == false;
        int index = leafs.size();
        startIndex = index;
        endIndex = index;
        leafs.add(this);
        addLock = true;
    }
    // After this call, 
    public void initSegment() {
        if (isSegmentInited()) {
            return;
        }
        int start = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;
        for (Item child : children.values()) {
            child.initSegment();
            assert child.isSegmentInited();
            start = Math.min(start, child.startIndex);
            end = Math.max(end, child.endIndex);
        }
        startIndex = start;
        endIndex = end;
        addLock = true;
    }

}
