package org.example.application.framework;
import java.lang.reflect.Method;


public class MethodItem extends Item {
    Method mMethod;

    private MethodItem(Method method) {
        super(method.getName());
        mMethod = method;
    }

    public String toString() {
        return super.toString() + " [M]";
    }
}
