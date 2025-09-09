package org.example.application.cmd;

import android.util.Log;

@Cmd
public class DemoNeo {
    public void action() {
        Log.i("Demo", "Hello from DemoNeo!");
    }

    @Cmd
    public void exampleNeo1() {
        Log.i("Demo", "Hello from exampleNeo1!");
    }

    @Cmd
    public void exampleNeo2() {
        Log.i("Demo", "Hello from exampleNeo2!");
    }
}


