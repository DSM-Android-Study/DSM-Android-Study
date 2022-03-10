package com.hackaton.extensionfuncexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.hackaton.extensionfuncexample.databinding.ActivityMainBinding
import com.hackaton.extensionfuncexample.extension.gone
import com.hackaton.extensionfuncexample.extension.invisible
import com.hackaton.extensionfuncexample.extension.loadFromUrl
import com.hackaton.extensionfuncexample.extension.visible

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.view.visibility = View.VISIBLE
        binding.view.visibility = View.INVISIBLE
        binding.view.visibility = View.GONE


        binding.view.gone()
        binding.view.invisible()
        binding.view.visible()

        binding.view.loadFromUrl("url")

        Glide.with(applicationContext)
            .load("url")
            .into(binding.view)


    }
}