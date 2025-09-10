package org.example.application.cmd;

import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java.lang.reflect.Method;
import java.io.IOException;

import dalvik.system.DexFile;

import android.content.Context;
import android.util.Log;

import static org.example.application.Config.TAG;

public class ClassScanner {

    // See: https://stackoverflow.com/questions/7627742/is-it-possible-to-scan-the-android-classpath-for-annotations
    // See: https://mindtherobot.com/blog/737/android-hacks-scan-android-classpath/
    // See: https://stackoverflow.com/a/15892032

    // TOOD: using anotation processor to do this
    @SuppressWarnings("deprecation")
    public static List<String> findClassesName(Context context, String packageName) {
        ArrayList<String> classes = new ArrayList<String>();
        try {
            DexFile df = new DexFile(context.getPackageCodePath());
            for (Enumeration<String> iter = df.entries(); iter.hasMoreElements();) {
                String className = iter.nextElement();
                if (className.contains(packageName)) {
                    classes.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    public static List<Class<?>> findClasses(Context context, String packageName) {
        Log.d(TAG,"Enter findCmdClasses");

        final List<String> classes = ClassScanner.findClassesName(context, packageName);
        for(String clz: classes) {
            Log.d(TAG,"Founded: " + clz);
        }

        List<Class<?>> list = new ArrayList<>();
        for(String clz: classes) {
            try {
                Class<?> cls = Class.forName(clz);
                list.add(cls);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.d(TAG,"Exit findCmdClasses");
        return list;
    }

    public static List<Method> findMethods(Context context, String packageName) {
        Log.d(TAG, "Enter findMethods");

        // Using Stream API to find all methods in classes within the package
        List<Method> methods = findClasses(context, packageName).stream()
            .flatMap(cls -> {
                try {
                    return Arrays.stream(cls.getDeclaredMethods());
                } catch (Exception e) {
                    Log.w(TAG, "Failed to get methods from class: " + cls.getName(), e);
                    return Stream.empty();
                }
            })
        .collect(Collectors.toList());

        Log.d(TAG, "Exit findMethods - Found " + methods.size() + " methods");
        return methods;
    }


}
