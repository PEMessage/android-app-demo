package org.example.application;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private ProgressDialog progressDialog;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create main layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // Create text view
        TextView textView = new TextView(this);
        textView.setText("Hello world");

        // Create button - using lambda for onClickListener
        Button button = new Button(this);
        button.setText("Run Heavy Task");
        button.setOnClickListener(v -> runHeavyTask());

        // Add views to layout
        layout.addView(textView);
        layout.addView(button);
        setContentView(layout);

        mainHandler = new Handler(Looper.getMainLooper());
    }

    private void runHeavyTask() {
        // Show progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Processing...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Create and start new thread for heavy task - using lambda for Runnable
        new Thread(() -> {
            // Simulate heavy task (sleep for 3 seconds)
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Dismiss progress dialog on main thread - using lambda for Runnable
            mainHandler.post(() -> {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });
        }).start();
    }
}
