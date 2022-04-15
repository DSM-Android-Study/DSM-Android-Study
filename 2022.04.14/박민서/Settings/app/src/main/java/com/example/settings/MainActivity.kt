package com.example.settings

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import android.widget.Button
import android.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService

class MainActivity : AppCompatActivity() {
    private lateinit var btn_vib: Button
    private lateinit var btn_music: Button
    private lateinit var btn_stop: Button
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var btn_share: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_vib = findViewById(R.id.btn)
        btn_music = findViewById(R.id.btn_music)
        btn_stop = findViewById(R.id.btn_stop)
        btn_share = findViewById(R.id.btn_share)
        btn_vib.setOnClickListener { Vibration(1000L) }
        btn_share.setOnClickListener {
            var intent = Intent()
            intent.setAction(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_SUBJECT, title)
            startActivity(Intent.createChooser(intent, "공유"))
        }

        btn_music.setOnClickListener {
            mediaPlayer = MediaPlayer.create(this, R.raw.music)
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener { (mediaPlayer.release()) }
            btn_stop.setOnClickListener {
                mediaPlayer.stop()
                mediaPlayer.setOnCompletionListener { (mediaPlayer.release()) }
            }
        }

    }

    fun Vibration(len: Long) {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(len)
    }


}


