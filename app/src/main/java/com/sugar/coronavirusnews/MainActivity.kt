package com.sugar.coronavirusnews

import android.annotation.TargetApi
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val url = "https://corona.kompa.ai"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        setupWebView()
        loadData()
    }

    private fun loadData() {
        webView.loadUrl(url)
    }

    private fun setupWebView() {
        webView.settings.javaScriptEnabled = true // enable javascript
        webView.settings.useWideViewPort = true
        webView.settings.loadWithOverviewMode = true
        val activity = this

        webView.webViewClient = object : WebViewClient() {
            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
            }

            @TargetApi(Build.VERSION_CODES.M)
            override fun onReceivedError(
                view: WebView,
                req: WebResourceRequest,
                rerr: WebResourceError
            ) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(
                    view,
                    rerr.errorCode,
                    rerr.description.toString(),
                    req.url.toString()
                )
            }
        }
    }
}
