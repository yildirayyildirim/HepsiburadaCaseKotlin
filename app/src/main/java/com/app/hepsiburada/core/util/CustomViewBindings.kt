package com.app.hepsiburada.core.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class CustomViewBindings {
    companion object {

        @BindingAdapter("app:imagePath")
        @JvmStatic
        fun imagePath(view: ImageView, imageUrl: String?) {
            var requestOption = RequestOptions()
            requestOption = requestOption.transforms(RoundedCorners(15))
            //do something funny
            imageUrl?.apply {
                Glide.with(view.context).load(this).apply(requestOption).into(view)
            }
        }


        @BindingAdapter("app:dateFormat")
        @JvmStatic
        fun dateFormat(view: TextView, imageUrl: String?) {
            imageUrl?.apply {
                val outputFormat: DateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                val inputFormat: DateFormat =
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
                val date = inputFormat.parse(imageUrl)
                val outputText: String = outputFormat.format(date)

                view.text = "Date:  $outputText"
            }
        }
    }
}