package com.tarwej.modon.helper

import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.tarwej.modon.R
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("app:imageResource")
fun setImageResource(imageView: AppCompatImageView, resource: String?) {
    val requestOptions = RequestOptions()
    requestOptions.error(R.drawable.test)
    Glide.with(imageView.context)
        .load((Constants.IMAGE_URL + resource))
        .transition(DrawableTransitionOptions.withCrossFade())
        .apply(requestOptions)
        .into(imageView)
}
@BindingAdapter("app:datetext")
fun setDatetext(text: TextView, resource: String?) {

    val myFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")

    val dateObj: Date = myFormat.parse(resource)
    val timestamp = dateObj.time.toString()//  //Example -> in ms
    val fromServer = SimpleDateFormat("yyyy-MM-dd", Locale("en"))
    val dateString = fromServer.format(Date(Long.parseLong(timestamp)))


    text.text = dateString

}
@BindingAdapter("app:timeText")
fun setTimeText(text: TextView, resource: String?) {

    val myFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")

    val dateObj: Date = myFormat.parse(resource)
    val timestamp = dateObj.time.toString()//  //Example -> in ms
    val fromServer = SimpleDateFormat("HH:mm a", Locale("en"))
    val dateString = fromServer.format(Date(Long.parseLong(timestamp)))


    text.text = dateString

}

@BindingAdapter("app:dayText")
fun setDayText(text: TextView, resource: String?) {
    val myFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
    val dateObj: Date = myFormat.parse(resource)
    val timestamp = dateObj.time.toString()//  //Example -> in ms
    val fromServer = SimpleDateFormat("EEEE", Locale("ar"))
    val dateString = fromServer.format(Date(Long.parseLong(timestamp)))


    text.text = dateString

}