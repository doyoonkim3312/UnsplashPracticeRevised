package com.doyoonkim.unsplashpracticerevised.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.doyoonkim.unsplashpracticerevised.R

object BindingAdapter {

    // Recycler View Image Load
    @BindingAdapter("loadImage")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(imageView)
    }

}