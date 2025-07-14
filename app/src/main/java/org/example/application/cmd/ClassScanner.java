package org.example.application.cmd;

import java.util.Enumeration;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;

import dalvik.system.DexFile;

import android.content.Context;
import android.util.Log;

public class ClassScanner {
    private static final String TAG = "DEMO-ClassScanner";

    // See: https://stackoverflow.com/questions/7627742/is-it-possible-to-scan-the-android-classpath-for-annotations
    // See: https://stackoverflow.com/a/15892032
    public static String[] findClassesName(Context context, String packageName) {
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

        return classes.toArray(new String[classes.size()]);
    }

    public static List<Class<?>> findClasses(Context context, String packageName) {
        Log.d(TAG,"Enter findCmdClasses");

        final String[] classes = ClassScanner.findClassesName(context, packageName);
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

}
