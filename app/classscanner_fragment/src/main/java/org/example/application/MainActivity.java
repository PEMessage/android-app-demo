package org.example.application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.content.Context;
import android.util.Log;

import org.example.application.cmd.Cmd;
import org.example.application.cmd.ClassScanner;

import org.example.application.framework.Item;
import org.example.application.framework.ItemUtils;
import org.example.application.framework.MethodItem;
import org.example.application.framework.ClassItem;

import org.example.Library;

import static org.example.application.Config.TAG;


public class MainActivity extends Activity {

    Item mRootItem = new Item("root");
    FrameLayout mContainer;

    private void initRootItem() {
        List<Class<?>> classes = ClassScanner.findClasses(this, "org.example.application");

        // 1. Filter class that has @Cmd annotaion
        // List<Class<?>> cmdClasses = classes.stream().filter(clazz -> {
        //     return clazz.isAnnotationPresent(Cmd.class);
        // }).collect(Collectors.toList());

        // 2. Filter class that any method has @Cmd annotataion
        List<Class<?>> cmdClasses = classes.stream()
            .filter(clazz -> {
                return Arrays
                    .stream(clazz.getDeclaredMethods())
                    .anyMatch(method -> method.isAnnotationPresent(Cmd.class));
            }).collect(Collectors.toList());

        cmdClasses.forEach(clazz -> {
            String[] name = clazz.getName().split("\\.");
            String[] packageName = Arrays.copyOf(name, name.length - 1);
            ClassItem classItem = new ClassItem(clazz);
            // mRootItem.addChild(classItem, packageName);
            ItemUtils.addChildChain(mRootItem, packageName, classItem);

            // For each method that has @Cmd annotaion
            List<Method> cmdMethods = Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(Cmd.class))
                .collect(Collectors.toList());

            for (Method m : cmdMethods) {
                classItem.addChild(new MethodItem(m));
            }
        });
    }

    private void initContainer() {
        mContainer = new FrameLayout(this);
        mContainer.setId(View.generateViewId()); // Generate unique ID
        setContentView(mContainer);
    }


    @SuppressWarnings("deprecation")
    private void initFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        ItemFragment fragment = new ItemFragment(ItemUtils.flattenSingleChildChains(mRootItem));
        transaction.replace(mContainer.getId(), fragment);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        initContainer();
        initRootItem();
        initFragment();

    }
}
