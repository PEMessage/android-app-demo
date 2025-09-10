package org.example.application.framework;



public class CommonItem extends Item {
    public CommonItem(String name) {
        super(name);
    }

    public String toString() {
        return super.toString() + " [B]";
    }
}
