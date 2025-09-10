package org.example.application.framework;
import java.lang.reflect.Method;


public class MethodItem extends Item {
    Method mMethod;
    boolean selected = false;

    public MethodItem(Method method, int index) {
        super(method.getName());
        mMethod = method;
        super.asLeaf(index);
    }

    public String toString() {
        return super.toString() + " [M]";
    }
}
