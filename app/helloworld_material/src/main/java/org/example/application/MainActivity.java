package org.example.application;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.view.Window;
import android.view.Gravity;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textview.MaterialTextView;
import com.google.android.material.button.MaterialButton;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

// See: https://m2.material.io/develop/android/docs/getting-started
public class MainActivity extends AppCompatActivity {

    // List of theme pairs (theme resource ID and theme name)
    private static final List<Map.Entry<Integer, String>> THEMES = Arrays.asList(
        new AbstractMap.SimpleEntry<>(com.google.android.material.R.style.Theme_MaterialComponents_Light,
            "Theme_MaterialComponents_Light"),
        new AbstractMap.SimpleEntry<>(com.google.android.material.R.style.Theme_MaterialComponents_DayNight,
            "Theme_MaterialComponents_DayNight")
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

        // Part 1. layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Part 2. textView
        MaterialTextView textView = new MaterialTextView(this);
        String message = "Hello world\n"
                        + "Selected Theme: " + selectedTheme.getValue();
        textView.setText(message);

        // Part 3. Button
        MaterialButton button = new MaterialButton(this);
        button.setText("recreate()");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
            }
        });

        // Part 4. SetView
        layout.addView(textView);
        layout.addView(button);
        setContentView(layout);
    }
}
