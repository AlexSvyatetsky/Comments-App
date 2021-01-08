package com.sa1nt.commentsapp.ui.comments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sa1nt.commentsapp.R
import com.sa1nt.commentsapp.data.remote.CommentResponse
import com.sa1nt.commentsapp.utils.ITEM_LIST
import com.sa1nt.commentsapp.utils.ITEM_LOADING

class CommentListAdapter(var presenter: CommentsPresenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var commentList: ArrayList<CommentResponse> = ArrayList()
    private var maybeMore: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_LOADING) {
            ItemLoadingViewHolder.create(parent)
        } else {
            CommentListViewHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            ITEM_LIST -> {
                val commentListViewHolder: CommentListViewHolder = holder as CommentListViewHolder
                commentListViewHolder.tvCommentId.text = commentList[position].id.toString()
                commentListViewHolder.tvCommentName.text = commentList[position].name
                commentListViewHolder.tvCommentEmail.text = commentList[position].email
                commentListViewHolder.tvCommentBody.text = commentList[position].body
            }
            ITEM_LOADING -> presenter.loadNextPageComments(commentList.size)
        }

    }

    fun setRepoList(repoList: List<CommentResponse>, maybeMore: Boolean) {
        this.maybeMore = maybeMore
        this.commentList.addAll(repoList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = commentList.size + if (maybeMore) 1 else 0

    override fun getItemViewType(position: Int): Int {
        return if (position == commentList.size) {
            ITEM_LOADING
        } else ITEM_LIST
    }

    class CommentListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            fun create(parent: ViewGroup): CommentListViewHolder {
                return CommentListViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(
                            R.layout.comment_item,
                            parent,
                            false
                        )
                )
            }
        }

        val tvCommentId: TextView = itemView.findViewById(R.id.tvCommentIdVal)
        val tvCommentName: TextView = itemView.findViewById(R.id.tvCommentNameVal)
        val tvCommentEmail: TextView = itemView.findViewById(R.id.tvCommentEmailVal)
        val tvCommentBody: TextView = itemView.findViewById(R.id.tvCommentBodyVal)

    }

    class ItemLoadingViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        companion object {
            fun create(parent: ViewGroup): ItemLoadingViewHolder {
                return ItemLoadingViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.loading_item, parent, false)
                )
            }
        }
    }


}