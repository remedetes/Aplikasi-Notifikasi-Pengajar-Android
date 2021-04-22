package com.example.appsanri.activity.jadprib

import com.example.appsanri.apiservice.NetworkClientHttp
import com.example.appsanri.apiservice.Routes
import com.example.appsanri.model.jadwalpibadi.ResponseJadPrip
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Written by Muhammad Joe Fachrizal
 */
class JadPribPresenter(private val view: JadwalFragment) : JadPribContract.Presenter{

    override fun getData(id: String) {
        val client = NetworkClientHttp.client.create(Routes::class.java)
        val call = client.APIJADPRI(id)
        call.enqueue(object : Callback<ResponseJadPrip> {
            override fun onResponse(call: Call<ResponseJadPrip>, response: Response<ResponseJadPrip>) {
                if (response.isSuccessful) {
                    val data = response.body()?.data
                    val status = response.body()?.status
                    if (data != null) {
                        view.onDataSuccess(data)
                    }
                    view.onDataStatus(status)
                }
            }

            override fun onFailure(call: Call<ResponseJadPrip>, t: Throwable) {
                t.message?.let { view.onDataFailed(it) }
            }
        })
    }
}
