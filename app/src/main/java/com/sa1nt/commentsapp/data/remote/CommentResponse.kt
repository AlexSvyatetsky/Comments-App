package com.sa1nt.commentsapp.data.remote

import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String
)