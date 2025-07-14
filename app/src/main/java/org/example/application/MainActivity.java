package org.example.application;


import android.app.Activity;
import android.os.Bundle;

import android.widget.TextView;
import android.view.Window;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.content.Context;
import android.util.Log;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Method;


import org.example.application.cmd.Cmd;


public class MainActivity extends Activity {
    private static final String TAG = "DEMO-MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TextView textView = new TextView(this);
        // textView.setText("Hello world");
        // setContentView(textView);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        List<Class<?>> cmdClasses = findCmdClasses(this);
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

    private List<Class<?>> findCmdClasses(Context context) {

        Log.d(TAG, "findCmdClasses");

        List<Class<?>> list = new ArrayList<>();
        try {
            Class<?> demoClass = Class.forName("org.example.application.cmd.Demo");
            if (demoClass.isAnnotationPresent(Cmd.class)) {
                list.add(demoClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
