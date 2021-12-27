package com.tarwej.modon.presentation.flightlistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.tarwej.modon.R
import com.tarwej.modon.databinding.FlightListFragmentBinding
import com.tarwej.modon.databinding.HomeFragmentBinding
import kotlinx.android.synthetic.main.flight_list_fragment.*


class FlightListFragment : Fragment() {

    lateinit var view: FlightListFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(
            inflater,
            R.layout.flight_list_fragment, container, false
        )
        view.shimmerViewContainer.startShimmerAnimation()

//        view.listener = ClickHandler()
      // view.context = context as MainActivity
//

        view.cityName = requireArguments().getString("cityName")


        // notes = view.note.text.toString()


        return view.root
    }


}





