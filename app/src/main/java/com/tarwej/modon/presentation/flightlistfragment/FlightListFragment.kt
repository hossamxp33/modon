package com.tarwej.modon.presentation.flightlistfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tarwej.modon.MainActivity
import com.tarwej.modon.R
import com.tarwej.modon.databinding.FlightListFragmentBinding
import com.tarwej.modon.helper.BaseApplication
import com.tarwej.modon.helper.Permissions
import com.tarwej.modon.presentation.flightlistfragment.adapter.FlightAdapter
import com.tarwej.modon.presentation.flightlistfragment.adapter.SliderAdapter
import com.tarwej.modon.presentation.flightlistfragment.mvi.TripIntent
import com.tarwej.modon.presentation.flightlistfragment.mvi.TripViewModel
import com.tarwej.modon.presentation.homefragment.mvi.UserError
import kotlinx.android.synthetic.main.flight_list_fragment.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


class FlightListFragment @Inject constructor(): Fragment() {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel by viewModels<TripViewModel> { viewModelFactory }

    lateinit   var tripAdapter : FlightAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            BaseApplication.appComponent.inject(this)

        }
    }

    lateinit var view: FlightListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = DataBindingUtil.inflate(
            inflater,
            R.layout.flight_list_fragment, container, false
        )
        view.shimmerViewContainer.startShimmerAnimation()

//        view.listener = ClickHandler()
      // view.context = context as MainActivity
//

        view.cityName = requireArguments().getString("cityName")

        getAllData()
        tripsRecycleView()

        return view.root
    }

    fun stopLoadingShimmer() {
        shimmer_view_container?.visibility = View.GONE
        shimmer_view_container?.stopShimmerAnimation()
    }
    fun getAllData() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { it ->
                if (it != null) {

                    if (it.error != null) {
                        it.error?.let {
                            when (it) {
                                is UserError.InvalidId -> "Invalid id"
                                is UserError.NetworkError -> it.throwable.message
                                UserError.ServerError -> "Server error"
                                UserError.Unexpected -> "Unexpected error"
                                is UserError.UserNotFound -> "User not found"
                                UserError.ValidationFailed -> "Validation failed"
                            }
                        }
                        viewModel.intents.send(TripIntent.ErrorDisplayed(it))
                    } else {
                        if (it.progress == true) {
                            viewModel.intents.send(TripIntent.GetData(it,1,2,"2021-12-27"))
                        } else {

                            tripAdapter.submitList(it.data?.data)
                            view.pager!!.adapter = it?.let { it1 -> SliderAdapter(activity!!, it.data?.data!!) }


                            Permissions().init(3, context as MainActivity, view)

                            view.indicator.setViewPager(view.pager)

                            stopLoadingShimmer()


                        }
                    }
                }

            }
        }
    }
    fun tripsRecycleView() {
        tripAdapter = FlightAdapter(requireContext())
        view.tripRecycleView.apply {
            layoutManager = LinearLayoutManager(context) // default orientation is vertical
            adapter = tripAdapter;
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

}





