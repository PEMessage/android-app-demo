// NativeWrapper.java
package org.example.application;

public class NativeWrapper {
    static {
        System.loadLibrary("native-lib");
    }

    public static native String getMessageFromNative();
    public static native int addNumbers(int a, int b);
}
