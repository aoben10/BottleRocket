package com.theobencode.victoroben.bottlerocket.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.theobencode.victoroben.bottlerocket.R
import com.theobencode.victoroben.bottlerocket.data.repository.Response
import com.theobencode.victoroben.bottlerocket.data.repository.Status
import com.theobencode.victoroben.bottlerocket.presentation.model.StoreEntity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[StoreViewModel::class.java]
        viewModel.store.observe(this, Observer { updateStore(it) })
        viewModel.fetchStore()
    }

    private fun updateStore(response: Response<StoreEntity?>?) {
        response?.let {
            when (it.status) {
                Status.LOADING -> {
                }
                Status.SUCCESS -> {
                }
                Status.ERROR -> {
                }
            }
            it.data?.let { /*adapter.addItems*/ }
            it.message?.let {
                //                Snackbar.make(container, R.string.error, Snackbar.LENGTH_LONG)
//                        .setAction(getString(R.string.retry), { viewModel.getComments(true) })
//                        .setDuration(Snackbar.LENGTH_INDEFINITE)
//                        .show()
            }
        }
    }
}