package com.example.doomnotronwebsiteapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}