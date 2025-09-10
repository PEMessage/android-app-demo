package org.example.application.cmd;

import android.util.Log;
import static org.example.application.Config.TAG;

@Cmd
public class Demo {
    public void action() {
        Log.i(TAG, "Hello from Demo!");
    }

    @Cmd
    public void example1() {
        Log.i(TAG, "Hello from example1!");
    }

    @Cmd
    public void example2() {
        Log.i(TAG, "Hello from example2!");
    }
}


