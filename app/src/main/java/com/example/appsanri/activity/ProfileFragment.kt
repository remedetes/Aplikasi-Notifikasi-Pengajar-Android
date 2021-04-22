package com.example.appsanri.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appsanri.R
import com.example.appsanri.activity.login.LoginAct
import com.example.appsanri.model.RefData.DATA_EMAIL
import com.example.appsanri.model.RefData.DATA_NAME
import com.example.appsanri.model.RefData.DATA_NIP
import com.example.appsanri.model.RefData.PREFS_NAME
import kotlinx.android.synthetic.main.fragment_settings.*

class ProfileFragment : Fragment() {

    private lateinit var namaPengajar: TextView
    private lateinit var nipPengajar: TextView
    private lateinit var emailPengajar: TextView

    private lateinit var nama: String
    private lateinit var nip: String
    private lateinit var email: String
    private lateinit var sharedPref: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    companion object {
        fun newInstance(): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = context!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        nama = sharedPref.getString(DATA_NAME, "").toString()
        nip = sharedPref.getString(DATA_NIP, "").toString()
        email = sharedPref.getString(DATA_EMAIL, "").toString()

        namaPengajar = view.findViewById(R.id.kolomNama)
        nipPengajar = view.findViewById(R.id.kolompNip)
        emailPengajar = view.findViewById(R.id.kolomEmail)

        namaPengajar.text = nama
        nipPengajar.text = nip
        emailPengajar.text = email

        btn_setting.setOnClickListener {
            val intent = Intent(context, SettingActivity::class.java)
            startActivity(intent)
        }

        btn_logout.setOnClickListener {
            editor = sharedPref.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(context, LoginAct::class.java)
            startActivity(intent)
            activity!!.finish()
        }
    }

}

