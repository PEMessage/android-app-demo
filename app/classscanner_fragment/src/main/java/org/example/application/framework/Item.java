package org.example.application.framework;



public class Item extends AbstractItem {
    public Item(String name) {
        super(name);
    }

    public String toString() {
        return super.toString() + " [B]";
    }
}
