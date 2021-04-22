package com.example.appsanri.activity.jadprib

import com.example.appsanri.model.jadwalpibadi.DataItem

/**
 * Written by Muhammad Joe Fachrizal
 */
interface JadPribContract {
    interface View {
        fun onDataSuccess(datas: List<DataItem>)
        fun onDataStatus (status : String?)
        fun onDataFailed(message: String)
    }

    interface Presenter {
        fun getData(id: String)
    }
}
