package org.example.application;


import java.util.ArrayList;
import java.util.Arrays;
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

import org.example.application.framework.Item;
import org.example.application.framework.ClassItem;

import org.example.Library;


public class MainActivity extends Activity {
    private static final String TAG = "DEMO-" + MainActivity.class.getSimpleName();

    Item mRootItem = new Item("root");

    private void initRootItem() {
        List<Class<?>> classes = ClassScanner.findClasses(this, "org.example.application");

        List<Class<?>> cmdClasses = classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Cmd.class))
                .collect(Collectors.toList());
        cmdClasses.forEach(clazz -> {
            String[] name = clazz.getName().split("\\.");
            String[] packageName = Arrays.copyOf(name, name.length - 1);
            mRootItem.addChild(new ClassItem(clazz), packageName);

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TextView textView = new TextView(this);
        // textView.setText("Hello world");
        // setContentView(textView);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        initRootItem();

        List<String> allNames = new ArrayList<>();
        allNames.add(mRootItem.toStringAll());


        ListView listView = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allNames);
        listView.setAdapter(adapter);
        setContentView(listView);

    }
}
