package org.example.application.framework;
import java.lang.reflect.Method;


public class MethodItem extends Item {

    Method mMethod;
    // Property
    boolean selected = false;
    State state = State.NOT_RUN;

    public MethodItem(Method method) {
        super(method.getName());
        mMethod = method;
    }

    public String toString() {
        return super.toString() + " [M]"
        + " @sel=" + selected
        + " @st=" + state;
    }

    public enum State {
        NOT_RUN,
        RUNNING,
        PASS,
        FAIL;
    }
}
