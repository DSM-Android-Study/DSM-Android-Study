package com.example.buttonfunction

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var a = 0;
        var b = 0
        val btn1 : Button = findViewById(R.id.button)
        val btn2 : Button = findViewById(R.id.button2)
        val btn3 : Button = findViewById(R.id.button3)
        val btn4 : Button = findViewById(R.id.button4)
        val btn5 : Button = findViewById(R.id.button5)
        val image1 : ImageButton = findViewById(R.id.imageButton)
        val image2 : ImageButton = findViewById(R.id.imageButton2)
        val image3 : ImageButton = findViewById(R.id.imageButton3)
        val image4 : ImageButton = findViewById(R.id.imageButton4)
        val image5 : ImageButton = findViewById(R.id.imageButton5)
        val tV : TextView = findViewById(R.id.textView)
        val tV2 : TextView = findViewById(R.id.textView2)

        btn1.setOnClickListener {
            a++
            tV.text = a.toString()
        }
        btn2.setOnClickListener {
            a++
            tV.text = a.toString()
        }
        btn3.setOnClickListener {
            a++
            tV.text = a.toString()
        }
        btn4.setOnClickListener {
            a++
            tV.text = a.toString()
        }
        btn5.setOnClickListener {
            a++
            tV.text = a.toString()
        }

        image1.setOnClickListener {
            b++
            tV2.text = b.toString()
        }
        image2.setOnClickListener {
            b++
            tV2.text = b.toString()
        }
        image3.setOnClickListener {
            b++
            tV2.text = b.toString()
        }
        image4.setOnClickListener {
            b++
            tV2.text = b.toString()
        }
        image5.setOnClickListener {
            b++
            tV2.text = b.toString()
        }

    }
}