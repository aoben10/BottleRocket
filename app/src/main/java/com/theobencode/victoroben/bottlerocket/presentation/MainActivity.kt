package com.theobencode.victoroben.bottlerocket.presentation

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.theobencode.victoroben.bottlerocket.R
import com.theobencode.victoroben.bottlerocket.data.repository.Response
import com.theobencode.victoroben.bottlerocket.data.repository.Status
import com.theobencode.victoroben.bottlerocket.databinding.ActivityMainBinding
import com.theobencode.victoroben.bottlerocket.presentation.extensions.startRefreshing
import com.theobencode.victoroben.bottlerocket.presentation.extensions.stopRefreshing
import com.theobencode.victoroben.bottlerocket.presentation.model.StoreEntity
import com.theobencode.victoroben.bottlerocket.presentation.storeslist.StoresListAdapter
import javax.inject.Inject

class MainActivity : BaseActivity(), StoresListAdapter.StoreItemClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding
    private var snackBar: Snackbar? = null
    private lateinit var adapter: StoresListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[StoreViewModel::class.java]
        adapter = StoresListAdapter(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent))
        binding.swipeRefreshLayout.setOnRefreshListener { viewModel.fetchStore() }
        binding.storesRecyclerView.adapter = adapter
        viewModel.store.observe(this, Observer { updateStore(it) })
        viewModel.fetchStore()
    }

    private fun updateStore(response: Response<StoreEntity?>?) {
        response?.let {
            when (it.status) {
                Status.LOADING -> {
                    binding.swipeRefreshLayout.startRefreshing()
                }
                Status.SUCCESS -> {
                    binding.swipeRefreshLayout.stopRefreshing()
                }
                Status.ERROR -> {
                    binding.swipeRefreshLayout.stopRefreshing()
                }
            }
            it.data?.let {
                adapter.setStores(it.storeList ?: emptyList())
            }
            it.message?.let { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
        }
    }

    override fun onStoreClick() {
        Toast.makeText(this, "Store item", Toast.LENGTH_LONG).show()
    }

    override fun onConnectionLost() {
        super.onConnectionLost()
        if (snackBar == null) {
            snackBar = Snackbar.make(binding.root, R.string.no_internet, Snackbar.LENGTH_LONG)
                    .setDuration(Snackbar.LENGTH_INDEFINITE)
        }
        snackBar?.show()
    }

    override fun onConnectionRestored() {
        super.onConnectionRestored()
        snackBar?.dismiss()
    }
}