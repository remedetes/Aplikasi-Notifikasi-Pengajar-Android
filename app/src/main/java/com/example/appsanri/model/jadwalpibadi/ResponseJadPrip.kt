package com.example.appsanri.model.jadwalpibadi

import com.google.gson.annotations.SerializedName

class ResponseJadPrip {

    @SerializedName("data")
    var data: List<DataItem>? = null

    @SerializedName("status")
    var status: String? = null
}