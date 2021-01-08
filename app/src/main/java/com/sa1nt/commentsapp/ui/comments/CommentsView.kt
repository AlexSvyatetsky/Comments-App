package com.sa1nt.commentsapp.ui.comments

import com.sa1nt.commentsapp.data.remote.CommentResponse


interface CommentsView {
    fun setCommentsList(comments: List<CommentResponse>, maybeMore: Boolean)
    fun showError(errorMessage: String?)
}