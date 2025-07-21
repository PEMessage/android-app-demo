package org.example.application;


import android.app.Activity;
import android.os.Bundle;

import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import android.content.SharedPreferences;

public class MainActivity extends Activity {
    private EditText editTextMessage;
    private Button buttonSave, buttonLoad, buttonFinish;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_MESSAGE = "saved_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout); // Activity(View) - setContentView -> R.layout.XXX

        // Initialize views
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSave = findViewById(R.id.buttonSave);
        buttonLoad = findViewById(R.id.buttonLoad);
        buttonFinish = findViewById(R.id.buttonFinish);
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Save button click listener
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMessage();
            }
        });

        // Load button click listener
        buttonLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMessage();
            }
        });

        // Finish button
        buttonFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveMessage() {
        String message = editTextMessage.getText().toString().trim();

        if (!message.isEmpty()) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_MESSAGE, message);
            editor.apply();
            Toast.makeText(this, "Message saved!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadMessage() {
        String savedMessage = sharedPreferences.getString(KEY_MESSAGE, "");

        if (!savedMessage.isEmpty()) {
            editTextMessage.setText(savedMessage);
            Toast.makeText(this, "Message loaded!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No message found", Toast.LENGTH_SHORT).show();
        }
    }

}
