package com.sample.mvvmrxsample.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.mvvmrxsample.data.network.UsersRepository
import com.sample.mvvmrxsample.viewmodel.MainViewModel

class ViewModelFactory() : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(_repository = UsersRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}