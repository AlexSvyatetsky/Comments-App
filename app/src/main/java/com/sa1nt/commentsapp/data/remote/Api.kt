package com.sa1nt.commentsapp.data.remote

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/comments")
    fun getComments(
        @Query(
            "id_gte"
        ) startId: Int, @Query(
            "id_lte"
        ) endId: Int,
        @Query(
            "_page"
        ) page: Int,
        @Query(
            "_limit"
        ) limit: Int
    ): Single<Response<List<CommentResponse>>>
}