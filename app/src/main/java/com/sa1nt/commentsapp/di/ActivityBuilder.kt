package com.sa1nt.commentsapp.di

import com.sa1nt.commentsapp.ui.comments.CommentsActivity
import com.sa1nt.commentsapp.ui.comments.CommentsActivityModule
import com.sa1nt.commentsapp.ui.main.MainActivity
import com.sa1nt.commentsapp.ui.main.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(CommentsActivityModule::class)])
    internal abstract fun bindCommentsActivity(): CommentsActivity

}