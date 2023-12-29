package com.abid.userapp.di

import android.content.Context
import com.abid.userapp.data.UserRepository
import com.abid.userapp.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        return UserRepository(apiService)
    }
}