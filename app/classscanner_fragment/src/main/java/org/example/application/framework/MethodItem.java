package org.example.application.framework;
import java.lang.reflect.Method;


public class MethodItem extends CommonItem {
    Method mMethod;

    public MethodItem(Method method) {
        super(method.getName());
        mMethod = method;
    }

    public String toString() {
        return super.toString() + " [M]";
    }
}
