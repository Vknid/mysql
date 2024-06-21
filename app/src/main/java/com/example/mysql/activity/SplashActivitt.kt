package com.example.mysql.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysql.MainActivity
import com.example.mysql.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivitt : AppCompatActivity() {

    private val splashTime: Long = 2000


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activitt)

        CoroutineScope(Dispatchers.Main).launch {
            delay(splashTime)

            val intent = Intent(this@SplashActivitt, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}