package com.sa1nt.commentsapp.ui.main

import com.sa1nt.commentsapp.data.remote.DataManager

class MainPresenter(private val dataManager: DataManager) {

    private var view: MainView? = null

    fun takeView(view: MainView?) {
        this.view = view
    }

    fun dropView() {
        if (view != null) {
            view = null
        }
    }

}