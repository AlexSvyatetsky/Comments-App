package com.sa1nt.commentsapp.ui.comments

import com.sa1nt.commentsapp.data.remote.DataManager
import com.sa1nt.commentsapp.utils.HEADER_PAGE_COUNT
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CommentsPresenter(private val dataManager: DataManager) {

    private var view: CommentsView? = null
    private var compositeDisposable = CompositeDisposable()
    private var startId: Int = 1
    private var endId: Int = 1
    private var page: Int = 1
    private var totalCount: Int = 1

    fun takeView(view: CommentsView?) {
        this.view = view
    }

    fun getComments(startId: Int, endId: Int) {
        this.startId = startId
        this.endId = endId
        compositeDisposable.add(dataManager.getComments(startId, endId, page)
            .doOnSuccess { totalCount = it.headers()[HEADER_PAGE_COUNT]!!.toInt() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ body ->
                view?.setCommentsList(body.body()!!, totalCount > body.body()!!.size)
            }, { throwable ->
                view?.showError(throwable.message)
            })
        )
    }


    fun loadNextPageComments(listSize: Int) {
        page++
        compositeDisposable.add(
            dataManager.getComments(startId, endId, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ body ->
                    view?.setCommentsList(body.body()!!, totalCount > listSize + body.body()!!.size)
                }, { throwable ->
                    view?.showError(throwable.message)
                })
        )
    }


    fun dropView() {
        compositeDisposable.clear()
        if (view != null) {
            view = null
        }
    }

}