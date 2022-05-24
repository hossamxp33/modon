package com.tarwej.modon.helper

import android.app.Activity
import android.content.Context
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.tarwej.modon.R
import www.sanju.motiontoast.MotionToast
import www.sanju.motiontoast.MotionToastStyle
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*


class MakeToast {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun ERROR_MotionToast_main(massage: String, context: Activity) {
        MotionToast.createColorToast(
            context,
            "Error ☹️",
            massage,
            MotionToastStyle.ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.cocon_next_arabic)
        )
    }

    fun SUCCESS_MotionToast(massage: String, context: Context) {
        MotionToast.createColorToast(
            context as Activity,
            "success 😍",
            massage,
            MotionToastStyle.SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.cocon_next_arabic)
        )
    }

    fun Warning_MotionToast(massage: String, context: Activity) {
        MotionToast.createColorToast(
            context,
            "Warning!",
            massage,
            MotionToastStyle.WARNING,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.helvetica_regular)
        )
    }


}


