package org.example.application;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Window;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Get results from native code
        String message = NativeWrapper.getMessageFromNative();
        int sum = NativeWrapper.addNumbers(5, 3);

        TextView textView = new TextView(this);
        textView.setText(message + "\nSum of 5 + 3 = " + sum);
        setContentView(textView);
    }
}
