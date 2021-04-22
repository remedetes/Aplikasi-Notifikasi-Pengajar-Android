package com.example.appsanri

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

import com.example.appsanri.activity.WelcomeAct

class SplashScreen : AppCompatActivity() {
    private val time = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splashscreen)
        Handler().postDelayed({

            val i = Intent(this@SplashScreen, WelcomeAct::class.java)
            startActivity(i)

            finish()
        }, time.toLong())
    }

}


