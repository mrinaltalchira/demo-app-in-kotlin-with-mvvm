package com.kotlinapp.demoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kotlinapp.demoapp.databinding.ActivityMainBinding
import com.kotlinapp.demoapp.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    lateinit var binding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.img.animate().setDuration(1500).alpha(1f).withEndAction {

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}