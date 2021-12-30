package com.example.androidstudyfirst

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView:WebView = findViewById(R.id.web_view)
        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebAppInterface(this),"BlackJin")
        webView.loadUrl("file:///android_asset/sample.html")
    }

    class WebAppInterface(private val mContext: Context){
        @JavascriptInterface
        fun showToast(toast : String){
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }
}