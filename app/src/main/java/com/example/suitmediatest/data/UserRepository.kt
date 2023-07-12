package com.example.suitmediatest.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.suitmediatest.network.ApiConfig
import com.example.suitmediatest.network.ApiService

class UserRepository(application: Application, private val apiService: ApiService) {

    fun getUser(): LiveData<PagingData<UserResponseItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserPagingDataSource(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(application: Application): UserRepository? {
            return instance ?: synchronized(UserRepository::class.java) {
                if (instance == null) {
                    val apiService = ApiConfig.getApiService()

                    instance = UserRepository(application, apiService)
                }
                return instance
            }
        }
    }
}