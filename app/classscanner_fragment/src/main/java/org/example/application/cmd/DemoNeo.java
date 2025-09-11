package org.example.application.cmd;

import android.util.Log;

import static org.example.application.Config.TAG;

@Cmd
public class DemoNeo {
    static int state = 0;
    public void action() {
        Log.i(TAG, "Hello from DemoNeo!");
    }

    @Cmd
    public void exampleNeo1() {
        Log.i(TAG, "Hello from exampleNeo1!");
    }

    @Cmd
    public void exampleNeo2() {
        Log.i(TAG, "Hello from exampleNeo2!");
    }

    @Cmd
    public void exampleNeo3() {
        state = (state + 1) % 2 ;
        Log.i(TAG, "Hello from exampleNeo3! state=" + state);

        if ( (state + 1) % 2 != 0 ) {
            assert false;
        }
    }
}


