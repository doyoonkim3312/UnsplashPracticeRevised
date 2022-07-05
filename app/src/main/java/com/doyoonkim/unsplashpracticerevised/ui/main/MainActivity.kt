package com.doyoonkim.unsplashpracticerevised.ui.main

import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.doyoonkim.unsplashpracticerevised.R
import com.doyoonkim.unsplashpracticerevised.adapter.ResultListAdapter
import com.doyoonkim.unsplashpracticerevised.data.SearchResult
import com.doyoonkim.unsplashpracticerevised.databinding.ActivityMainBinding
import com.doyoonkim.unsplashpracticerevised.ui.base.BaseActivity
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    // Permission
    val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        isGranted ->
        if (isGranted) {
            Toast.makeText(this, "Internet Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Internet Permission is not Granted", Toast.LENGTH_SHORT).show()
        }
    }

    // Kotlin Recommended way.
    override val layoutRes: Int
        get() = R.layout.activity_main

    // ViewModel
    override val viewModel: MainViewModel = get()

    // Recycler View Adapter
    private val recyclerViewAdapter: ResultListAdapter by inject()

    override fun initView() {
        // Recycler View Settings
        binding.itemRecylerView.layoutManager = LinearLayoutManager(this)
        binding.itemRecylerView.adapter = recyclerViewAdapter
    }

    override fun initDataBinding() {
        viewModel.imageSearchResultLiveData.observe(this) {
            recyclerViewAdapter.data = it.results.flatMap { result ->
                listOf(result)      // Quite Unnecessary here.
            }
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun initAfterBinding() {
        // Permissions
        permissionLauncher.launch(android.Manifest.permission.INTERNET)

        // onClickListener.
        binding.searchBtn.setOnClickListener {
            val query: String = binding.searchTextField.text.toString()
            viewModel.loadImageService(query, 1, 10, "en")
        }
    }

}