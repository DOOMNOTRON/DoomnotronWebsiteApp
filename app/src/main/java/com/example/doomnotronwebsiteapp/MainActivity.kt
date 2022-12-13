package com.example.doomnotronwebsiteapp

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.MotionEventCompat
import java.lang.Math.abs

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    lateinit var gestureDetector: GestureDetector


    // start location of gesture
    var x1:Float = 0.0f
    var x2:Float = 0.0f
    var y1:Float = 0.0f
    var y2:Float = 0.0f

    companion object {
        const val MIN_DISTANCE = 150
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gestureDetector = GestureDetector(this, this)

        // Loads the url for the webView
        val mWebView = findViewById<View>(R.id.WebView) as WebView
        mWebView.loadUrl("https://doomnotron.azurewebsites.net/")

        // Enable Javascript
        val webSettings = mWebView.settings
        webSettings.loadsImagesAutomatically = true
        webSettings.javaScriptEnabled = true
        // force links to open in the webview
        mWebView.webViewClient = WebViewClient()

        // Enable responsive layout
        mWebView.getSettings().setUseWideViewPort(true)
        // Zoom out if the content width is greater than the width of the viewport
        mWebView.getSettings().setLoadWithOverviewMode(true)

        // enabled zoom in controls
        mWebView.getSettings().setSupportZoom(true)
        mWebView.getSettings().setBuiltInZoomControls(true) // allow pinch to zoom
        mWebView.getSettings().setDisplayZoomControls(false) // disable the default zoom controls on the page

        // gets the motion events and creates log depending on the users actions
        mWebView.setOnTouchListener {v: View, m: MotionEvent ->
            val eventType = m.getActionMasked()

            when (eventType) {
                // one finger touches the screen
                MotionEvent.ACTION_DOWN -> Log.d(TAG, "TouchEvents: Action down")
                // one finger lifts from the screen
                MotionEvent.ACTION_UP -> Log.d(TAG, "TouchEvents: Action Up")
                // multiple fingers touches the screen
                MotionEvent.ACTION_POINTER_DOWN -> Log.d(TAG, "TouchEvents: Action Pointer Down")
                // multiple fingers lift from the screen
                MotionEvent.ACTION_POINTER_UP -> Log.d(TAG, "TouchEvents: Action Pointer Up")
                // user moves finger on the screen
                MotionEvent.ACTION_MOVE -> Log.d(TAG, "TouchEvents: Action Move")
            }
            false
        }



        // enable the go back button
        mWebView.canGoBack()
        mWebView.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
            if (i == KeyEvent.KEYCODE_BACK
                && keyEvent.action == MotionEvent.ACTION_UP
                && mWebView.canGoBack()) {
                mWebView.goBack()
                return@OnKeyListener true
            }
            false
        })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        gestureDetector.onTouchEvent(event)

        when (event?.action) {
            // swipe start
            0 -> {
                x1 = event.x
                y1 = event.y
            }
            // swipe end
            1 -> {
                x2 = event.x
                y2 = event.y

                val valueX:Float = x2 - x1
                val valueY:Float = y2 - y1

                if (abs(valueX) > MIN_DISTANCE) {
                    // detect left to right swipe
                    if (x2 > x1) {
                        Toast.makeText(this, "Right Swipe", Toast.LENGTH_SHORT).show()

                    }
                    // detect right to left swipe
                    if (x2 > x1) {
                        Toast.makeText(this, "Left Swipe", Toast.LENGTH_SHORT).show()
                    }
                }

                else if (abs(valueY) > MIN_DISTANCE){
                    // detect Top to bottom swipe
                    if (y2 > y1){
                        Toast.makeText(this, "Bottom swipe", Toast.LENGTH_SHORT).show()
                    }
                    // detect bottom to Top swipe
                    else {
                        Toast.makeText(this, "Top Swipe", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }


        return super.onTouchEvent(event)
    }

    // Not used, but it needs to be implemented
    override fun onDown(p0: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onShowPress(p0: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(p0: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onFling(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
        //TODO("Not yet implemented")
        return false
    }
}