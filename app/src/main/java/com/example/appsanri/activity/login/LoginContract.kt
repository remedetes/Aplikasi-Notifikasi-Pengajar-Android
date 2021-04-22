package com.example.appsanri.activity.login

import com.example.appsanri.model.login.DataLogin

interface LoginContract {
    interface View {
        fun onDataSuccess(data: DataLogin?)
        fun onDataStatus(status: String?)
        fun onDataFailed(message: String?)
    }

    interface Presenter {
        fun getData(email: String, pass: String)
    }
}