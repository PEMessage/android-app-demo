package org.example.application;

import android.os.Bundle;
import android.widget.TextView;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // List of theme pairs (theme resource ID and theme name)
    // Map: A set of Key-Value Pair
    // Map.Entry: A Single KeyValue Pair
    private static final List<Map.Entry<Integer, String>> THEMES = Arrays.asList(
        new AbstractMap.SimpleEntry<>(androidx.appcompat.R.style.Theme_AppCompat_Light,
            "Theme_AppCompat_Light"),
        new AbstractMap.SimpleEntry<>( androidx.appcompat.R.style.Theme_AppCompat_DayNight,
            "Theme_AppCompat_DayNight"),
        new AbstractMap.SimpleEntry<>(androidx.appcompat.R.style.Theme_AppCompat_DayNight_DarkActionBar,
            "Theme_AppCompat_DarkActionBar"),
        new AbstractMap.SimpleEntry<>(androidx.appcompat.R.style.Theme_AppCompat_DayNight_NoActionBar,
            "Theme_AppCompat_NoActionBar")
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Randomly select a theme
        Random random = new Random();
        int selectedIndex = random.nextInt(THEMES.size());
        Map.Entry<Integer, String> selectedTheme = THEMES.get(selectedIndex);
        setTheme(selectedTheme.getKey()); // Setup Theme, This must before setContentView


        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        TextView textView = new TextView(this);
        String message = "Hello world\n"
                        + "Selected Theme: " + selectedTheme.getValue();
        textView.setText(message);
        setContentView(textView);
    }
}
