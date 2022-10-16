package com.example.banksampah.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.banksampah.database.DatabaseClient.Companion.getInstance
import com.example.banksampah.database.dao.DatabaseDao
import com.example.banksampah.model.ModelDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers

class InputDataViewModel(application: Application) : AndroidViewModel(application) {

    var databaseDao: DatabaseDao?

    fun addDataSampah(
        nama_pengguna: String,
        jenis_sampah: String,
        berat: Int,
        harga: Int,
        tanggal: String,
        alamat: String,
        catatan: String
    ) {
        Completable.fromAction {
            val modelDatabase = ModelDatabase(
                namaPengguna = nama_pengguna,
                jenisSampah = jenis_sampah,
                berat = berat,
                harga = harga,
                tanggal = tanggal,
                alamat = alamat,
                catatan = catatan
            )
            databaseDao?.insertData(modelDatabase)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }


    init {
        databaseDao = getInstance(application)?.appDatabase?.databaseDao()
    }

}
