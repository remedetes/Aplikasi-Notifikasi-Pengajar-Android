package com.example.appsanri.model.login

import com.google.gson.annotations.SerializedName

class ResponseLogin(
        @SerializedName("data")
        var data: DataLogin? = null,
        @SerializedName("status")
        var status: String? = null
)