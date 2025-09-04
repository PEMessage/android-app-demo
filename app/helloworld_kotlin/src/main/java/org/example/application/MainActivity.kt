package org.example.application

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val textView = TextView(this)
        textView.text = "Hello world"
        setContentView(textView)
    }
}
