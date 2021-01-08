package com.sa1nt.commentsapp.data.remote

import com.sa1nt.commentsapp.utils.LIMIT
import io.reactivex.Single
import retrofit2.Response

class DataManager(
    private val api: Api
) {

    fun getComments(
        startId: Int, endId: Int,
        page: Int
    ): Single<Response<List<CommentResponse>>> {
        return api.getComments(startId, endId, page, LIMIT)
    }

}