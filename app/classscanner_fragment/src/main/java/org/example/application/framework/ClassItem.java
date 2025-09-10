package org.example.application.framework;


public class ClassItem extends CommonItem {
    Class<?> mClass;

    public ClassItem(Class<?> clazz) {
        super(clazz.getSimpleName());
        mClass = clazz;
    }

    public String toString() {
        return super.toString() + " [C]";
    }
}
