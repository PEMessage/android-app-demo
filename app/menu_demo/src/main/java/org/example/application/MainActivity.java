package org.example.application;


import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.Toast;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout); // Activity(View) - setContentView -> R.layout.XXX

        var button_1 = (Button)findViewById(R.id.button_1); // If cast to error type, crash at runtime
        button_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You clicked Button 1", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // android.view.Menu Part
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.first_menu, menu); // Menu - inflate(...) -> R.menu.XXX 
        return true; // set to false allow you to disable menu
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // switch (item.getItemId()) {
        //     case R.id.add_item:
        //     Toast.makeText(MainActivity.this, "You clicked R.id.add_item", Toast.LENGTH_SHORT).show();
        //     break;
        //
        //     case R.id.remove_item:
        //     Toast.makeText(MainActivity.this, "You clicked R.id.remove_item", Toast.LENGTH_SHORT).show();
        //     break;
        // }
        // error: constant expression required
        //       case R.id.add_item:
        // See: https://stackoverflow.com/questions/76430646/constant-expression-required-when-trying-to-create-a-switch-case-block
        // Starting with Android Gradle Plugin 8.0.0, by default, your resources (e.g. R.id. ...) are no longer declared final
        // for optimized build speed

        int itemId = item.getItemId();
        if (false) { // dummy first condition for align
        } else if (itemId == R.id.add_item) {
            Toast.makeText(MainActivity.this, "You clicked R.id.add_item", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.remove_item) {
            Toast.makeText(MainActivity.this, "You clicked R.id.remove_item", Toast.LENGTH_SHORT).show();
        } else {
            // pass
        }
        return false; // not know the meaning of return true or false
    }

}
