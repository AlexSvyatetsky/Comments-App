package com.sa1nt.commentsapp.ui.comments

import com.sa1nt.commentsapp.data.remote.DataManager
import dagger.Module
import dagger.Provides


@Module
class CommentsActivityModule {

    @Provides
    internal fun provideCommentsPresenter(dataManager: DataManager): CommentsPresenter {
        return CommentsPresenter(dataManager)
    }

}