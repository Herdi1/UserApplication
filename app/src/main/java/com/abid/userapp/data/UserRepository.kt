package com.abid.userapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.abid.userapp.data.response.DataItem
import com.abid.userapp.data.retrofit.ApiService

class UserRepository(private val apiService: ApiService) {
    fun getUsers(): LiveData<PagingData<DataItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UsersPagingSource(apiService)
            }
        ).liveData
    }
}