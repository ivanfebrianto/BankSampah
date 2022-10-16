package com.example.banksampah.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.banksampah.database.DatabaseClient.Companion.getInstance
import com.example.banksampah.database.dao.DatabaseDao
import com.example.banksampah.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class RiwayatViewModel(application: Application) : AndroidViewModel(application) {

    var totalSaldo: LiveData<Int>
    var dataBank: LiveData<List<ModelDatabase>>
    var databaseDao: DatabaseDao?

    fun deleteDataById(uid: Int) {
        Completable.fromAction {
            databaseDao?.deleteSingleData(uid)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    init {
        databaseDao = getInstance(application)?.appDatabase?.databaseDao()
        dataBank = databaseDao!!.getAll()
        totalSaldo = databaseDao!!.getSaldo()
    }

}