package com.theobencode.victoroben.bottlerocket.presentation.storedetails

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.theobencode.victoroben.bottlerocket.R
import com.theobencode.victoroben.bottlerocket.databinding.ActivityStoreDetailsBinding
import com.theobencode.victoroben.bottlerocket.presentation.BaseActivity
import com.theobencode.victoroben.bottlerocket.presentation.model.STORE_EXTRA
import com.theobencode.victoroben.bottlerocket.presentation.model.StoreItemEntity

class StoreDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val binding = DataBindingUtil.setContentView<ActivityStoreDetailsBinding>(this, R.layout.activity_store_details)
        binding.store = intent?.getSerializableExtra(STORE_EXTRA) as StoreItemEntity
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        fun newIntent(context: Context, store: StoreItemEntity): Intent {
            val intent = Intent(context, StoreDetailsActivity::class.java)
            intent.putExtra(STORE_EXTRA, store)
            return intent
        }
    }

}
