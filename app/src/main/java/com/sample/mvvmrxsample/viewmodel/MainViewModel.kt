package com.sample.mvvmrxsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.mvvmrxsample.data.Users
import com.sample.mvvmrxsample.data.network.UsersRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(_repository: UsersRepository) : ViewModel() {
    private val repository: UsersRepository = _repository
    private val compositeDisposable = CompositeDisposable()
    private val users = MutableLiveData<Users>()

    fun fetchUsers(perPage: Int, since: Int) {
        compositeDisposable.add(
            repository.getUsers(perPage, since)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ mUsers ->
                    if (mUsers.isSuccessful) {
                        users.postValue(mUsers.body())
                    } else {
                        users.postValue(null)
                    }
                }, {
                    users.postValue(null)
                })
        )
    }

    fun getUsers(): LiveData<Users> {
        return users
    }
}