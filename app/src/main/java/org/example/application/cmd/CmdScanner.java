package org.example.application.cmd;

import java.util.Enumeration;
import java.util.ArrayList;
import java.io.IOException;

import dalvik.system.DexFile;

import android.content.Context;

public class CmdScanner {

    // See: https://stackoverflow.com/questions/7627742/is-it-possible-to-scan-the-android-classpath-for-annotations
    // See: https://stackoverflow.com/a/15892032
    public static String[] findClasses(Context context, String packageName) throws Exception {
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
}
