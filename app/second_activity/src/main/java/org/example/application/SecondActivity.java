package org.example.application;


import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import android.content.Intent;

public class SecondActivity extends Activity {
    static int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        Intent intent = getIntent();
        String data =  intent.getStringExtra("extra_data");

        TextView textview_2_1 = (TextView)findViewById(R.id.textview_2_1);
        String origin = textview_2_1.getText().toString();
        textview_2_1.setText(
            origin + "\n"
            + "    intent.getStringExtra(\"extra_data\"): " + data
        );

        Button button_2_1 = (Button)findViewById(R.id.button_2_1);
        button_2_1.setText("finish");
        button_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // close this Activity
            }
        });

        Button button_2_2 = (Button)findViewById(R.id.button_2_2);
        button_2_2.setText("finish with setResult(...) putExtra(...," + flag + ")");
        button_2_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a empty intent, not link to any context or class(explicit)
                // only used for putExtra to pass data(bundle)
                Intent intent = new Intent(); 
                flag = (flag + 1) % 3;
                intent.putExtra("data_return", "Hello MainActivity " + flag);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
