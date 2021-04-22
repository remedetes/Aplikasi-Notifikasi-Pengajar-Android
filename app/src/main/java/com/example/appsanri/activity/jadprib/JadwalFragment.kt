package com.example.appsanri.activity.jadprib

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.appsanri.R
import com.example.appsanri.adepter.JadwalPribadiAdapter
import com.example.appsanri.model.RefData.DATA_ID
import com.example.appsanri.model.RefData.PREFS_NAME
import com.example.appsanri.model.jadwalpibadi.DataItem
import kotlinx.android.synthetic.main.fragment_jadwal.*


class JadwalFragment : Fragment(), JadPribContract.View {

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: JadwalPribadiAdapter
    private lateinit var presenter: JadPribContract.Presenter
    private var listJadwal: ArrayList<DataItem> = arrayListOf()
    private lateinit var id: String
    private lateinit var sharedPref: SharedPreferences

    companion object {
        fun newInstance(): JadwalFragment {
            val fragment = JadwalFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_jadwal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = JadPribPresenter(this)
        sharedPref = context!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        id = sharedPref.getString(DATA_ID, "").toString()

        swipeRefreshLayout = swipe_refresh_layout
        recyclerView = list_jadwal

        initData()

        swipeRefreshLayout.setOnRefreshListener { presenter.getData(id) }
        swipeRefreshLayout.post {
            swipeRefreshLayout.isRefreshing = true
            presenter.getData(id)
        }
    }

    override fun onResume() {
        super.onResume()
        if (isVisible) {
            presenter.getData(id)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){
            swipeRefreshLayout.isEnabled = true
            if (adapter != null){
                adapter.refreshData(listJadwal)
            }
        }else{
            swipeRefreshLayout.isEnabled = false
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            presenter.getData(id)
        }
    }

    private fun initData() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = JadwalPribadiAdapter(listJadwal)
        recyclerView.adapter = adapter
    }

    override fun onDataSuccess(datas: List<DataItem>) {
        adapter.setJadwalData(datas)
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onDataFailed(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        swipeRefreshLayout.isRefreshing = false
    }

    override fun onDataStatus(status: String?) {
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show()
        swipeRefreshLayout.isRefreshing = false
    }


}
