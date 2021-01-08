package com.sa1nt.commentsapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sa1nt.commentsapp.R
import com.sa1nt.commentsapp.ui.comments.CommentsActivity
import com.sa1nt.commentsapp.utils.EXTRA_BOUND_1
import com.sa1nt.commentsapp.utils.EXTRA_BOUND_2
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    internal lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.takeView(this)

        btnSubmit.setOnClickListener {
            if (etField1.text.isNotEmpty() && etField2.text.isNotEmpty()) {
                if (etField1.text.toString().toInt() >= etField2.text.toString().toInt()) {
                    Toast.makeText(
                        this,
                        getString(R.string.the_lower_bound_should_be_less),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    startActivity(
                        Intent(this, CommentsActivity::class.java).putExtra(
                            EXTRA_BOUND_1,
                            etField1.text.toString().toInt()
                        ).putExtra(EXTRA_BOUND_2, etField2.text.toString().toInt())
                    )
                }
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.please_enter_the_boundaries),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }
}