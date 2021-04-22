package com.example.appsanri.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appsanri.R
import com.example.appsanri.activity.jadprib.JadwalFragment
import com.example.appsanri.helper.ActivityHelpers
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MenuMainContent : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var fragment: Fragment
    private lateinit var jadwalFragment: JadwalFragment
    private lateinit var kalenderFragment: KalenderFragment
    private lateinit var profileFragment: ProfileFragment
    private var select = 0
    private var id = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        mAuth = FirebaseAuth.getInstance()
        jadwalFragment = JadwalFragment.newInstance()
        kalenderFragment = KalenderFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()
        val navigation = findViewById<BottomNavigationView>(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        this.fragment = jadwalFragment
        showFragment()
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                id = 0
                if (select != id) {
                    select = 0
                    this.fragment = jadwalFragment
                    showFragment()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                id = 1
                if (select != id) {
                    select = 1
                    this.fragment = kalenderFragment
                    showFragment()
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                id = 2
                if (select != id) {
                    select = 2
                    this.fragment = profileFragment
                    showFragment()
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun showFragment() {
        ActivityHelpers.showFragmentMenu(supportFragmentManager, this.fragment, R.id.frame_content)
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
                .setTitle("Peringatan!")
                .setMessage("Yakin ingin menutup aplikasi?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes) { _, _ ->
                    super@MenuMainContent.onBackPressed()
                    finishAffinity()
                }
                .create().show()
    }
}
