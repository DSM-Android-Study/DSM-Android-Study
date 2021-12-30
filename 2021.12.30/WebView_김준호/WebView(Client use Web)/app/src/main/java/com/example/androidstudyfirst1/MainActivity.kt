package com.example.androidstudyfirst1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView:WebView = findViewById(R.id.web_view)
        val button: Button = findViewById(R.id.button)
        val edittext:EditText = findViewById(R.id.edittext)

        webView.settings.javaScriptEnabled = true
        webView.addJavascriptInterface(WebViewJavascriptBridge(),"BlackJin")
        webView.loadUrl("file:///android_asset/exam.html")
        button.setOnClickListener {
            webView.loadUrl("javascript:exam_script.plus_num(" + edittext.text + ")")
        }


    }
    inner class WebViewJavascriptBridge{
        @JavascriptInterface
        fun getDoubleNum(num:Int){
            val textView:TextView = findViewById(R.id.textview)
            textView.text = num.toString()
        }
    }
}