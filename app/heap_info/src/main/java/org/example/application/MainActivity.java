package org.example.application;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.stream.IntStream;

public class MainActivity extends Activity {

    private TextView heapInfoText;
    private Button refreshButton;
    private Button refreshButtonX100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create main layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 32, 32, 32);

        // Create Refresh button
        refreshButton = new Button(this);
        refreshButton.setText("Refresh");
        refreshButton.setOnClickListener(
            v -> updateHeapInfo()
        );

        refreshButtonX100 = new Button(this);
        refreshButtonX100.setText("Refresh * 100");
        refreshButtonX100.setOnClickListener(
            v -> IntStream.range(0, 100).forEach(i -> updateHeapInfo())
        );

        // Create TextView for heap info
        heapInfoText = new TextView(this);
        heapInfoText.setTextSize(16f);
        heapInfoText.setPadding(0, 32, 0, 0);

        // Add views to layout
        layout.addView(refreshButton);
        layout.addView(refreshButtonX100);
        layout.addView(heapInfoText);

        // Set content view
        setContentView(layout);

        // Initial update
        updateHeapInfo();
    }

    // NOTICE: it seem doesn't match the output of `dumpsys meminfo <PID>`
    // `ps -A | grep org.example.application | awk '{print $2}' | xargs dumpsys meminfo`
    private void updateHeapInfo() {
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

        String info = "Java Heap:\n" +
                "Max: " + formatSize(javaMax) + "\n" +
                "Total: " + formatSize(javaTotal) + "\n" +
                "Used: " + formatSize(javaUsed) + "\n" +
                "Free: " + formatSize(javaFree) + "\n\n" +
                "Native Heap:\n" +
                "Size: " + formatSize(nativeHeapSize) + "\n" +
                "Allocated: " + formatSize(nativeHeapAllocated);

        heapInfoText.setText(info);
    }

    private String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int)(Math.log(bytes) / Math.log(1024));
        String pre = "KMGTPE".charAt(exp-1) + "B";
        return String.format("%.1f %s", bytes / Math.pow(1024, exp), pre);
    }
}
