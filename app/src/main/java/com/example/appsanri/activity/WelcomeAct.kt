package com.example.appsanri.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.appsanri.R

import com.example.appsanri.activity.login.LoginAct
import com.example.appsanri.model.RefData
import kotlinx.android.synthetic.main.splash2.*

class WelcomeAct : AppCompatActivity() {
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash2)

        sharedPref = getSharedPreferences(RefData.PREFS_NAME, Context.MODE_PRIVATE)
        val id = sharedPref.getString(RefData.DATA_ID, "").toString()

        if (id.isNotEmpty()) {
            startActivity(Intent(this, MenuMainContent::class.java))
            finishAffinity()
        } else {
            btn_login.setOnClickListener {
                startActivity(Intent(this, LoginAct::class.java))
            }
        }


    }

}

