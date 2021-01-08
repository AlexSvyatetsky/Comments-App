package com.sa1nt.commentsapp.di

import com.sa1nt.commentsapp.data.remote.Api
import com.sa1nt.commentsapp.data.remote.DataManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {
    @Provides
    @AppScope
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Provides
    @AppScope
    fun provideDataManager(
        api: Api
    ): DataManager {
        return DataManager(api)
    }
}