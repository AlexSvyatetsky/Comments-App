package com.sa1nt.commentsapp.ui.main

import com.sa1nt.commentsapp.data.remote.DataManager
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {

    @Provides
    internal fun provideMainPresenter(dataManager: DataManager): MainPresenter {
        return MainPresenter(dataManager)
    }

}