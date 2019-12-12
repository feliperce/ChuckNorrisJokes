package com.example.chucknorrisjokes.ui.bindingadapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import org.jetbrains.anko.browse

@BindingAdapter("app:boolVisibility")
fun boolVisibility(view: View, isVisible: Boolean) {
    if (isVisible) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("app:picassoSrc")
fun picassoSrc(imageView: ImageView, url: String?) {
    url?.let {
        Picasso.get()
            .load(it)
            .into(imageView)
    }

}

@BindingAdapter("app:browseToUrlOnClick")
fun browseToUrlOnClick(button: Button, url: String?) {
    url?.let {
        button.setOnClickListener { v ->
            v.context.browse(it)
        }
    }
}