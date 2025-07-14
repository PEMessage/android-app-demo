package org.example.application;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;
import android.view.Window;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.content.Context;
import android.util.Log;

import org.example.application.cmd.Cmd;
import org.example.application.cmd.ClassScanner;



public class MainActivity extends Activity {
    private static final String TAG = "DEMO-MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TextView textView = new TextView(this);
        // textView.setText("Hello world");
        // setContentView(textView);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        List<Class<?>> classes = ClassScanner.findClasses(this, "org.example.application");

        // NOTE: due to this error, using forloop way to do this
        // Error: Call requires API level 24 (current min is 15): java.util.stream.Stream#collect [NewApi]
        // List<Class<?>> cmdClasses = classes.stream()
        //         .filter(clazz -> clazz.isAnnotationPresent(Cmd.class))
        //         .collect(Collectors.toList());
        //
        List<Class<?>> cmdClasses = new ArrayList<>();
        for (Class<?> cls : classes) {
            if (cls.isAnnotationPresent(Cmd.class)) {
                Log.d(TAG, "Using Cmd class: " + cls.toString());
                cmdClasses.add(cls);
            }
        }

        List<String> classNames = new ArrayList<>();
        for (Class<?> c : cmdClasses) {
            classNames.add(c.getSimpleName());
        }

        ListView listView = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classNames);
        listView.setAdapter(adapter);
        setContentView(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                Class<?> clazz = cmdClasses.get(position);
                try {
                    Object obj = clazz.newInstance();
                    Method action = clazz.getMethod("action");
                    action.invoke(obj);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
