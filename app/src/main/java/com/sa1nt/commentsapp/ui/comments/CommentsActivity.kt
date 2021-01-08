package com.sa1nt.commentsapp.ui.comments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sa1nt.commentsapp.R
import com.sa1nt.commentsapp.data.remote.CommentResponse
import com.sa1nt.commentsapp.utils.EXTRA_BOUND_1
import com.sa1nt.commentsapp.utils.EXTRA_BOUND_2
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_comments.*
import javax.inject.Inject

class CommentsActivity : AppCompatActivity(), CommentsView {

    @Inject
    internal lateinit var presenter: CommentsPresenter
    lateinit var adapter: CommentListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter.takeView(this)

        adapter = CommentListAdapter(presenter)
        rvCommentList.adapter = adapter
        rvCommentList.layoutManager = LinearLayoutManager(this)


        presenter.getComments(
            intent.getIntExtra(EXTRA_BOUND_1, 0),
            intent.getIntExtra(EXTRA_BOUND_2, 0)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun setCommentsList(comments: List<CommentResponse>, maybeMore: Boolean) {
        rvCommentList.visibility = if (comments.isNotEmpty()) View.VISIBLE else View.GONE
        tvEmptyList.visibility = if (comments.isEmpty()) View.VISIBLE else View.GONE
        adapter.setRepoList(comments, maybeMore)
    }

    override fun showError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }
}