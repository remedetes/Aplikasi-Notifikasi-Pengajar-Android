package com.example.appsanri.apiservice

import com.example.appsanri.model.jadwalpibadi.ResponseJadPrip
import com.example.appsanri.model.login.ResponseLogin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Routes {

    @GET("jsonjadwalpribadi.php")
    fun APIJADPRI(@Query("id_pengajar") id: String):
            Call<ResponseJadPrip>

    @GET("jsonlogin.php")
    fun APILOGIN(@Query("email") email: String,
                 @Query("password") pass: String):
            Call<ResponseLogin>
}
