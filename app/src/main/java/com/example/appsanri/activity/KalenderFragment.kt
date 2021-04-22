package com.example.appsanri.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.appsanri.R


class KalenderFragment : Fragment() {
    private lateinit var calendarView: CalendarView
    private lateinit var dateView: TextView

    companion object {
        fun newInstance(): KalenderFragment {
            val fragment = KalenderFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        calendarView = view.findViewById(R.id.calendarView)
        dateView = view.findViewById(R.id.myDate)

        calendarView.setOnDateChangeListener { _, i, i1, i2 ->
            val date = (i1 + 1).toString() + "/" + i2 + "/" + i
            dateView.text = date
        }

    }


}



