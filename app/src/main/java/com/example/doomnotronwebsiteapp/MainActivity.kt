package com.example.doomnotronwebsiteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        mWebView.webViewClient = WebViewClient()

        // Enable responsive layout
        mWebView.getSettings().setUseWideViewPort(true)
        // Zoom out if the content width is greater than the width of the viewport
        mWebView.getSettings().setLoadWithOverviewMode(true)

        // enabled zoom in controls
        mWebView.getSettings().setSupportZoom(true)
        mWebView.getSettings().setBuiltInZoomControls(true) // allow pinch to zoom
        mWebView.getSettings().setDisplayZoomControls(false) // disable the default zoom controls on the page

        // saved instance state


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