package com.example.appsanri.adepter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appsanri.R
import com.example.appsanri.model.jadwalpibadi.DataItem

/**
Written by Muhammad Joe Fachrizal
 **/
class JadwalPribadiAdapter(private var jadwalData: List<DataItem>) : RecyclerView.Adapter<JadwalPribadiAdapter.JadwalViewHolder>() {

    fun setJadwalData(jadwalData: List<DataItem>) {
        this.jadwalData = jadwalData
        notifyDataSetChanged()
    }

    fun refreshData(datas: List<DataItem>) {
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_jadwal_pribadi, parent, false)
        return JadwalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, i: Int) {
        val jadwal = jadwalData[i]
        holder.namaPengajar.text = jadwal.namaPengajar
        holder.namaDiklat.text = jadwal.namaDiklat
        holder.materiDiklat.text = jadwal.materiDiklat
        holder.jamMengajar.text = jadwal.jamMengajar
        holder.jamSelesai.text = jadwal.jamSelesaiMengajar
        holder.tanggal.text = jadwal.tanggal
    }

    class JadwalViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var namaPengajar: TextView = v.findViewById(R.id.namaPengajar)
        var namaDiklat: TextView = v.findViewById(R.id.namaDiklat)
        var materiDiklat: TextView = v.findViewById(R.id.materiDiklat)
        var jamMengajar: TextView = v.findViewById(R.id.jamMengajar)
        var jamSelesai: TextView = v.findViewById(R.id.jam_selesai)
        var tanggal: TextView = v.findViewById(R.id.tanggal)
    }

    override fun getItemCount(): Int {
        return jadwalData.size
    }

}
