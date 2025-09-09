package org.example.application.cmd;

import android.util.Log;

@Cmd
public class Demo {
    public void action() {
        Log.i("Demo", "Hello from Demo!");
    }

    @Cmd
    public void example1() {
        Log.i("Demo", "Hello from example1!");
    }

    @Cmd
    public void example2() {
        Log.i("Demo", "Hello from example2!");
    }
}


