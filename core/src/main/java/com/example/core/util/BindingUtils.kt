package com.example.core.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.core.R

@BindingAdapter("urlToImage")
fun ImageView.setUrlToImage(url: String?) {
    url.let {
        Glide.with(this)
                .load(it)
                .centerCrop()
                .placeholder(R.drawable.ic_image_place_holder)
                .error(R.drawable.ic_broken_image)
                .fallback(R.drawable.ic_image_place_holder)
                .into(this)
    }
}