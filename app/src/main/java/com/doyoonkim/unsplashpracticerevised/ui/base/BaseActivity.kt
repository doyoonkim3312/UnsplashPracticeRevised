package com.doyoonkim.unsplashpracticerevised.ui.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

// Base what all other activity will inherit from.

abstract class BaseActivity<T: ViewDataBinding, R: BaseViewModel>: AppCompatActivity() {

    // Data Binding
    lateinit var binding: T

    // Layout Resource ID required for setContentView() function.
    abstract val layoutRes: Int

    // ViewModel
    abstract val viewModel: R


    /**
     * Called right after the layout is inflated.
     * Purpose: Initializing view or activity properties.
     * ex) recyclerView, ToolBar, DrawerView.
     */
    abstract fun initView()

    /**
     * Called after initView() above is called.
     * Configure DataBinding and RxJava.
     * ex) RxJava Observe, DataBinding Observe.
     */
    abstract fun initDataBinding()

    /**
     * Called after initDataBinding is called.
     * All necessary tasks have to be done after data binding.
     * ex) clickListener.
     */
    abstract fun initAfterBinding()


    private var isSetBackButtonValid = false;   // ???

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data Binding
        binding = DataBindingUtil.setContentView(this, layoutRes)

        initView()          // 1
        initDataBinding()   // 2
        initAfterBinding()  // 3
    }

    // Lifecycle Related Functions (For Studying)
    override fun onResume() {
        super.onResume()
        Log.d("BaseActivity/", "onResume Status")
    }

    override fun onPause() {
        super.onPause()
        Log.d("BaseActivity/", "onPause Status")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("BaseActivity/", "onDestroy Status")
    }

}