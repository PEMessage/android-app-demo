package org.example.application;

import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;
import android.widget.ScrollView;
import android.view.Window;

public class MainActivity extends Activity {
    private String args = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Example1:
        //   ( gcd ; ./gradlew :adb_args:installDebug ; adb shell am start 'org.example.application/.MainActivity' ;)
        // Example2:
        //   HAVE TO ADD -n !!!!
        //   ( gcd ; ./gradlew :adb_args:installDebug ; adb shell am start -n 'org.example.application/.MainActivity' -e "args" "123" ; )

        this.args = getIntent().getStringExtra("args"); // get null if nothing
        // We are creating the UI programmatically, so no XML layout is needed.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Use a ScrollView to ensure all content is viewable
        ScrollView scrollView = new ScrollView(this);
        TextView textView = new TextView(this);
        textView.setPadding(16, 16, 16, 16);
        textView.setTextIsSelectable(true); // Allow user to copy text

        scrollView.addView(textView);

        // Set the collected text to the TextView
        textView.setText("Your adb command: " + this.args);

        // Set the ScrollView as the main content view for this activity
        setContentView(scrollView);
    }

}
