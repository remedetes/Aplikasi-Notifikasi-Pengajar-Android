package com.example.appsanri.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appsanri.R;
import com.example.appsanri.helper.AlarmReceiver;
import com.example.appsanri.helper.DatePickerFragment;
import com.example.appsanri.helper.TimePickerFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {
    private TextView tvOnceDate;
    private TextView tvOnceTime;
    private EditText etPesan;
    private ImageButton btnOnceDate;
    private ImageButton btnOnceTime;
    private Button btnSimpan;

    private AlarmReceiver alarmReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        // Inisiasi view untuk one time alarm
        tvOnceDate = findViewById(R.id.tv_once_date);
        btnOnceDate = findViewById(R.id.btn_once_date);
        tvOnceTime = findViewById(R.id.tv_once_time);
        btnOnceTime = findViewById(R.id.btn_once_time);
        etPesan = findViewById(R.id.et_pesan);
        btnSimpan = findViewById(R.id.btn_simpan);

        // Listener one time alarm
        btnOnceDate.setOnClickListener(this);
        btnOnceTime.setOnClickListener(this);
        btnSimpan.setOnClickListener(this);

        alarmReceiver = new AlarmReceiver();
    }

    private final static String DATE_PICKER_TAG = "DatePicker";
    private final static String TIME_PICKER_ONCE_TAG = "TimePickerOnce";


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_once_date:
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), DATE_PICKER_TAG);
                break;
            case R.id.btn_once_time:
                TimePickerFragment timePickerFragmentOne = new TimePickerFragment();
                timePickerFragmentOne.show(getSupportFragmentManager(), TIME_PICKER_ONCE_TAG);
                break;
            case R.id.btn_simpan:
                String onceDate = tvOnceDate.getText().toString();
                String onceTime = tvOnceTime.getText().toString();
                String onceMessage = etPesan.getText().toString();

                alarmReceiver.setOneTimeAlarm(this, AlarmReceiver.TYPE_ONE_TIME,
                        onceDate,
                        onceTime,
                        onceMessage);
                break;

            default:
                break;
        }
    }

    @Override
    public void onDialogDateSet(String tag, int year, int month, int dayOfMonth) {

        // Siapkan date formatter-nya terlebih dahulu
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        // Set text dari textview once
        tvOnceDate.setText(dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onDialogTimeSet(String tag, int hourOfDay, int minute) {

        // Siapkan time formatter-nya terlebih dahulu
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());

        // Set text dari textview berdasarkan tag
        switch (tag) {
            case TIME_PICKER_ONCE_TAG:
                tvOnceTime.setText(dateFormat.format(calendar.getTime()));
                break;
            default:
                break;
        }
    }
}

//import android.content.Context
//import android.content.SharedPreferences
//import android.os.Bundle
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.appsanri.R
//import com.example.appsanri.helper.AlarmReceiveler
//import com.example.appsanri.helper.AlarmReceiver
//import com.example.appsanri.helper.DatePickerFragment
//import com.example.appsanri.helper.TimePickerFragment
//import com.example.appsanri.model.RefData.PREFS_NAME
//import com.example.appsanri.model.RefData.SET_ACTIVE
//import com.example.appsanri.model.RefData.SET_NOTIF
//import com.example.appsanri.model.RefData.SET_TIME
//import com.example.appsanri.model.RefData.TIME_PICKER_ONCE_TAG
//import kotlinx.android.synthetic.main.activity_setting.*
//import java.text.SimpleDateFormat
//import java.util.*
//
//class SettingActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener, DatePickerFragment.DialogDateListener {
//    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    private lateinit var alarmReceiveler: AlarmReceiver
//    private lateinit var sharedPref: SharedPreferences
//    private lateinit var editor: SharedPreferences.Editor
//    private var setActive: String = ""
//    private var setBlokA: String = ""
//    private var myNotif = ""
//    private var myTime = ""
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_setting)
//        sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
//        alarmReceiveler = AlarmReceiver()
//        myTime = sharedPref.getString(SET_TIME, "").toString()
//        if (myTime.isNotEmpty()) {
//            tv_once_time.text = myTime
//        }
//        setBlokA = sharedPref.getString(SET_ACTIVE, "").toString()
//        if (setBlokA.isNotEmpty()) {
//            if (setBlokA == "1") {
//                switch_alarm.isChecked = true
//                btn_once_time.setOnClickListener {
//                    val timePickerFragmentOne = TimePickerFragment()
//                    timePickerFragmentOne.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
//                    tv_once_time.text = myTime
//                }
//            } else {
//                et_pesan.isEnabled = false
//                btn_once_time.setBackgroundResource(R.drawable.bg_input_off)
//            }
//        }
//
//        setListener()
//    }
//
//    private fun setListener() {
//        switch_alarm.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                setActive = "1"
//                et_pesan.isEnabled = true
//                btn_once_time.setBackgroundResource(R.drawable.bg_input_dua)
//                tv_once_time.text = myTime
//                btn_once_time.setOnClickListener {
//                    val timePickerFragmentOne = TimePickerFragment()
//                    timePickerFragmentOne.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
//                }
//            } else {
//                setActive = "0"
//                et_pesan.isEnabled = false
//                btn_once_time.setBackgroundResource(R.drawable.bg_input_off)
//                btn_once_time.setOnClickListener {
//                    Toast.makeText(this, "...", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//        btn_simpan.setOnClickListener {
//            myTime = tv_once_time.text.toString().trim()
//            myNotif = et_pesan.text.toString().trim()
//            editor = sharedPref.edit()
//            editor.putString(SET_ACTIVE, setActive)
//            editor.putString(SET_TIME, myTime)
//            editor.putString(SET_NOTIF, myNotif)
//            editor.apply()
//            alarmSetting(myTime, myNotif)
//            finish()
//            Toast.makeText(this, "Data Disimpan", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun alarmSetting(myTime: String, myNotif: String) {
//        val time = myTime
//        val message = myNotif
//        alarmReceiver.setOneTimeAlarm(this, AlarmReceiveler.TYPE_ONE_TIME, time, message)
//    }
//
//    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {
//        // Siapkan time formatter-nya terlebih dahulu
//        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
//        calendar.set(Calendar.MINUTE, minute)
//
//        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
//
//        // Set text dari textview berdasarkan tag
//        when (tag) {
//            TIME_PICKER_ONCE_TAG -> tv_once_time.text = dateFormat.format(calendar.time)
//            else -> {
//            }
//        }
//    }
//}
