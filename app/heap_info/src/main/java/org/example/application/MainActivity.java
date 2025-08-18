package org.example.application;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText(getHeapInfo());
        setContentView(textView);
    }

    private String getHeapInfo() {
        // Java heap info
        Runtime runtime = Runtime.getRuntime();
        long javaMax = runtime.maxMemory();
        long javaTotal = runtime.totalMemory();
        long javaFree = runtime.freeMemory();
        long javaUsed = javaTotal - javaFree;

        // Native heap info
        Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
        Debug.getMemoryInfo(memoryInfo);
        long nativeHeapSize = memoryInfo.getTotalPss() * 1024L;
        long nativeHeapAllocated = memoryInfo.dalvikPss * 1024L;

        return "Java Heap:\n" +
               "Max: " + formatSize(javaMax) + "\n" +
               "Total: " + formatSize(javaTotal) + "\n" +
               "Used: " + formatSize(javaUsed) + "\n" +
               "Free: " + formatSize(javaFree) + "\n\n" +
               "Native Heap:\n" +
               "Size: " + formatSize(nativeHeapSize) + "\n" +
               "Allocated: " + formatSize(nativeHeapAllocated);
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int)(Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp-1) + "B";
        return String.format("%.1f %s", bytes / Math.pow(1024, exp), pre);
    }
}
