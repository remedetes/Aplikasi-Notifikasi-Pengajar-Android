package com.example.appsanri.activity.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appsanri.R
import com.example.appsanri.activity.MenuMainContent
import com.example.appsanri.model.RefData.DATA_EMAIL
import com.example.appsanri.model.RefData.DATA_ID
import com.example.appsanri.model.RefData.DATA_NAME
import com.example.appsanri.model.RefData.DATA_NIP
import com.example.appsanri.model.RefData.PREFS_NAME
import com.example.appsanri.model.login.DataLogin
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.login.*
import java.security.NoSuchAlgorithmException

class LoginAct : AppCompatActivity(), LoginContract.View, View.OnClickListener {

    private lateinit var presenter: LoginContract.Presenter
    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        presenter = LoginPresenter(this)
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val email = inputEmail.text.toString().trim()
        val pass = inputPass.text.toString().trim()
        val passMD5: String = md5(pass)
        var emptyInput = false
        when {
            email.isEmpty() -> {
                emptyInput = true
                inputEmail.error = "Email Kosong"
            }
            pass.isEmpty() -> {
                emptyInput = true
                inputPass.error = "Password Kosong"
            }
        }
        if (view.id == R.id.btnLogin) {
            if (!emptyInput) {
                presenter.getData(email, passMD5)
            }
        }
    }

    override fun onDataSuccess(data: DataLogin?) {
        editor = sharedPref.edit()
        editor.putString(DATA_ID, data?.idPengajar)
        editor.putString(DATA_NAME, data?.namaPengajar)
        editor.putString(DATA_NIP, data?.nipPengajar)
        editor.putString(DATA_EMAIL, data?.emailPengajar)
        editor.apply()

        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, data?.idPengajar)
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, data?.namaPengajar)
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)

        val intent = Intent(this@LoginAct, MenuMainContent::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun onDataStatus(status: String?) {
        Toast.makeText(this, status, Toast.LENGTH_SHORT).show()
    }

    override fun onDataFailed(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun md5(passMd5: String): String {
        val md5 = "md5"
        try {
            val digest = java.security.MessageDigest.getInstance(md5)
            digest.update(passMd5.toByteArray())
            val messageDigest = digest.digest()

            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0$h"
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

}
