package com.example.appsanri.model.jadwalpibadi

import com.google.gson.annotations.SerializedName

class DataItem(
        @SerializedName("jam_mengajar")
        var jamMengajar: String?,
        @SerializedName("jam_selesai_mengajar")
        var jamSelesaiMengajar: String?,
        @SerializedName("nama_pengajar")
        var namaPengajar: String?,
        @SerializedName("id_pengajar")
        var idPengajar: String?,
        @SerializedName("id_jadwal_pengajar")
        var idJadwalPengajar: String?,
        @SerializedName("materi_diklat")
        var materiDiklat: String?,
        @SerializedName("tanggal")
        var tanggal: String?,
        @SerializedName("nama_diklat")
        var namaDiklat: String?,
        @SerializedName("nip_pengajar")
        var nipPengajar: String?,
        @SerializedName("email_pengajar")
        var emailPengajar: String?

)

