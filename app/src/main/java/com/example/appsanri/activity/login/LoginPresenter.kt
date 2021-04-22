package com.example.appsanri.activity.login

import com.example.appsanri.apiservice.NetworkClientHttp
import com.example.appsanri.apiservice.Routes
import com.example.appsanri.model.login.ResponseLogin

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginPresenter(private val view: LoginContract.View) : LoginContract.Presenter {
    val statusOk = "success"
    val statusFail = "failed"
    override fun getData(email: String, pass: String) {
        val client = NetworkClientHttp.client.create(Routes::class.java)
        val call = client.APILOGIN(email, pass)
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if (response.isSuccessful){
                    val data = response.body()?.data
                    val status = response.body()?.status
                    if (statusOk.equals(status, ignoreCase = true)){
                        view.onDataSuccess(data)
                    } else if (statusFail.equals(status, ignoreCase = true)){
                        view.onDataStatus(status)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                view.onDataFailed(t.message)
            }

        })
    }
}