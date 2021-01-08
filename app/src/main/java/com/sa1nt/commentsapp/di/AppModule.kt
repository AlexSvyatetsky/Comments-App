package com.sa1nt.commentsapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    @AppScope
    internal fun provideContext(application: Application): Context {
        return application
    }
}