package org.example.application;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.Window;

import java.util.Properties;
import java.util.Enumeration;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Get all system properties
        Properties systemProperties = System.getProperties();
        StringBuilder propertiesText = new StringBuilder();

        // Iterate through all properties and build a string
        Enumeration<?> propertyNames = systemProperties.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String key = (String) propertyNames.nextElement();
            String value = systemProperties.getProperty(key);
            propertiesText.append(key).append(" = ").append(value).append("\n\n");
        }

        TextView textView = new TextView(this);
        textView.setText(propertiesText.toString());
        
        setContentView(textView);
    }
}
