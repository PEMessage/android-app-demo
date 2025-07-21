package org.example.application;


import android.app.Activity;
import android.os.Bundle;

import android.provider.Settings;
import android.content.ContentResolver;

import android.widget.TextView;
import android.widget.ScrollView;
import android.view.Window;

import android.database.Cursor;
import android.net.Uri;
import android.graphics.Typeface;

import android.text.SpannableStringBuilder;
import android.text.Spannable;
import android.text.style.StyleSpan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // We are creating the UI programmatically, so no XML layout is needed.
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // Use a ScrollView to ensure all content is viewable
        ScrollView scrollView = new ScrollView(this);
        TextView textView = new TextView(this);
        textView.setPadding(16, 16, 16, 16);
        textView.setTextIsSelectable(true); // Allow user to copy text

        scrollView.addView(textView);

        // Use SpannableStringBuilder to allow for bolding headers
        SpannableStringBuilder builder = new SpannableStringBuilder();

        // Get the content resolver which will be used to query the settings
        ContentResolver contentResolver = getContentResolver();

        // Append settings from each category
        appendSettings(builder, "System Settings", contentResolver, Settings.System.CONTENT_URI);
        appendSettings(builder, "Secure Settings", contentResolver, Settings.Secure.CONTENT_URI);
        appendSettings(builder, "Global Settings", contentResolver, Settings.Global.CONTENT_URI);

        // Set the collected text to the TextView
        textView.setText(builder);

        // Set the ScrollView as the main content view for this activity
        setContentView(scrollView);
    }

    /**
     * Queries the content provider for a specific settings category and appends the
     * key-value pairs to a SpannableStringBuilder.
     *
     * @param builder The SpannableStringBuilder to append the results to.
     * @param title The title for this section of settings (e.g., "Secure Settings").
     * @param resolver The ContentResolver to use for the query.
     * @param uri The content URI for the settings category (e.g., Settings.System.CONTENT_URI).
     */
    private void appendSettings(SpannableStringBuilder builder, String title, ContentResolver resolver, Uri uri) {
        // Append a bolded title for the settings category
        int start = builder.length();
        builder.append(title).append("\n");
        builder.setSpan(new StyleSpan(Typeface.BOLD), start, builder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        builder.append("--------------------\n");

        Cursor cursor = null;
        try {
            // Query the settings provider. The columns are typically "name" and "value".
            cursor = resolver.query(uri, new String[]{"name", "value"}, null, null, null);

            if (cursor == null) {
                builder.append("Could not query settings. Cursor is null.\n\n");
                return;
            }
            
            // To make the output readable, we'll read all settings into a list and sort them alphabetically by key.
            ArrayList<String[]> settingsList = new ArrayList<>();
            int nameIndex = cursor.getColumnIndex("name");
            int valueIndex = cursor.getColumnIndex("value");

            while (cursor.moveToNext()) {
                String name = cursor.getString(nameIndex);
                String value = cursor.getString(valueIndex);
                settingsList.add(new String[]{name, value});
            }

            // Sort the list by the setting name (key)
            Collections.sort(settingsList, new Comparator<String[]>() {
                @Override
                public int compare(String[] o1, String[] o2) {
                    if (o1[0] == null) return -1;
                    if (o2[0] == null) return 1;
                    return o1[0].compareTo(o2[0]);
                }
            });

            // Append the sorted list to the builder
            if (settingsList.isEmpty()) {
                 builder.append("No settings found or not accessible.\n");
            } else {
                for (String[] setting : settingsList) {
                    builder.append(setting[0]).append(" = ").append(setting[1]).append("\n");
                }
            }

        } catch (SecurityException e) {
            // This exception is expected for Secure and Global settings on non-system apps.
            builder.append("Failed to read settings due to a SecurityException.\n")
                   .append("Your application needs special permissions (e.g., android.permission.WRITE_SETTINGS) to read these values.\n")
                   .append("This permission is not granted to normal apps.\n");
        } catch (Exception e) {
            // Catch other potential exceptions
            builder.append("An error occurred: ").append(e.getMessage()).append("\n");
        } finally {
            // Always ensure the cursor is closed to prevent memory leaks.
            if (cursor != null) {
                cursor.close();
            }
            // Add some spacing before the next section
            builder.append("\n");
        }
    }
}
