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

import org.example.Library;


public class MainActivity extends Activity {
    private static final String TAG = "DEMO-" + MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TextView textView = new TextView(this);
        // textView.setText("Hello world");
        // setContentView(textView);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        List<Class<?>> classes = ClassScanner.findClasses(this, "org.example.application");
        List<Method> methods = ClassScanner.findMethods(this, "org.example.application");

        // NOTE: due to this error, using forloop way to do this
        // Error: Call requires API level 24 (current min is 15): java.util.stream.Stream#collect [NewApi]
        List<Class<?>> cmdClasses = classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Cmd.class))
                .collect(Collectors.toList());

        List<Method> cmdMethods = methods.stream()
                .filter(method -> method.isAnnotationPresent(Cmd.class))
                .collect(Collectors.toList());

        Library demoLib = new Library();
        demoLib.someLibraryMethod();

        List<String> allNames = new ArrayList<>();
        for (Class<?> c : cmdClasses) {
            allNames.add(c.getName());
        }

        for (Method c : cmdMethods) {
            allNames.add(c.getDeclaringClass().getName() + "." + c.getName());
        }

        ListView listView = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allNames);
        listView.setAdapter(adapter);
        setContentView(listView);

        // listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        //     @Override
        //     public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
        //         Class<?> clazz = cmdClasses.get(position);
        //         try {
        //             Object obj = clazz.newInstance();
        //             Method action = clazz.getMethod("action");
        //             action.invoke(obj);
        //         } catch (Exception e) {
        //             e.printStackTrace();
        //         }
        //     }
        // });
    }
}
