package com.sample.mvvmrxsample.data.network

import com.sample.mvvmrxsample.data.Users
import com.sample.mvvmrxsample.utils.IApiConfig
import com.sample.mvvmrxsample.utils.RetrofitClient
import io.reactivex.Single
import retrofit2.Response

class UsersRepository {
    private val apiService: IApiConfig = RetrofitClient.instance.api

    fun getUsers(perPage: Int, since: Int): Single<Response<Users>> {
        return apiService.getUsers(perPage, since)
    }
}