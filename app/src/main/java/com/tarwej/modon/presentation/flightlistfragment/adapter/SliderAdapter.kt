package com.tarwej.modon.presentation.flightlistfragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.tarwej.modon.MainActivity
import com.tarwej.modon.R
import com.tarwej.modon.entities.trips.Data
import com.tarwej.modon.helper.setImageResource
import kotlinx.android.synthetic.main.viewpagerslider.view.*

import javax.inject.Inject


class SliderAdapter @Inject constructor(val context: Context,val slidersData: List<Data>) :
    PagerAdapter() {

    override fun getCount(): Int {
        return 3
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1 //To change body of created functions use File | Settings | File Templates.
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            (context as MainActivity).getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.viewpagerslider, container, false)

        setImageResource(view.im_slider, null)

        container.addView(view)

    //    ShapeAppearanceModel(context, view.im_slider)


        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val view = `object` as View
        container.removeView(view)
    }


}
