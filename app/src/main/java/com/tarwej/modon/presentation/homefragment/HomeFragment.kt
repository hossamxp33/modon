package com.tarwej.modon.presentation.homefragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.tarwej.modon.ClickHandler
import com.tarwej.modon.MainActivity
import com.tarwej.modon.R
import com.tarwej.modon.databinding.HomeFragmentBinding
import com.tarwej.modon.helper.BaseApplication
import com.tarwej.modon.helper.MakeToast
import com.tarwej.modon.presentation.flightlistfragment.FlightListFragment
import com.tarwej.modon.presentation.homefragment.mvi.HomeViewModel
import com.tarwej.modon.presentation.homefragment.mvi.MainIntent
import com.tarwej.modon.presentation.homefragment.mvi.UserError
import kotlinx.android.synthetic.main.dropdown_item.*
import kotlinx.android.synthetic.main.flight_list_fragment.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject


class HomeFragment @Inject constructor(): Fragment(), DatePickerDialog.OnDateSetListener {
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0
    var startCityName : String ? = null
    var arrivalCityName : String ? = null
    var selectionDate: String ? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel by viewModels<HomeViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            BaseApplication.appComponent.inject(this)
        }
    }

    lateinit var view: HomeFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment, container, false
        )

        view.listener = ClickHandler()
        view.context = context as MainActivity

        viewModel.intents.trySend(MainIntent.Initialize(viewModel.state.value!!))

        getAllData()

        view.datePickerLayout.setOnClickListener {
            val calendar: Calendar = Calendar.getInstance()
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)

            val datePickerDialog =
                DatePickerDialog(requireContext(),AlertDialog.THEME_DEVICE_DEFAULT_DARK,  this, year, month, day)
            datePickerDialog.show()

        }
        return view.root
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
                        viewModel.intents.send(MainIntent.ErrorDisplayed(it))
                    } else {
                        if (it.progress == true) {
                            viewModel.intents.send(MainIntent.Initialize(it))
                        } else {
                        val arrayList = it.homepagedata?.data?.map { it.name }
                            val arrayAdapter = ArrayAdapter(
                                requireContext(),
                                R.layout.dropdown_item,
                                arrayList!!
                               )

                            val startCityTextView = view.autoCompleteTextView

                            startCityTextView.setAdapter(arrayAdapter)

                            val arrivalCityTextView = view.arrivalCityTextView

                            arrivalCityTextView.setAdapter(arrayAdapter)

                            view.searchButton.setOnClickListener {
                               startCityName = startCityTextView.text.toString()
                                arrivalCityName = arrivalCityTextView.text.toString()
                                flightListFragment()

                            }



                        }
                    }
                }

            }
        }
    }

    private fun flightListFragment(){
        if (!(startCityName == null || startCityName == "")){
            if (!(arrivalCityName == null || arrivalCityName == "")) {
                if (!(selectionDate == null || selectionDate == "")) {


                    view.cityName = "  $startCityName"

                    val fragment = FlightListFragment()
                    val bundle = Bundle()
                    bundle.putString("startCityName", startCityName)
                    bundle.putString("arrivalCityName", arrivalCityName)
                    bundle.putString("cityName", startCityName)
                    bundle.putString("selectionDate", selectionDate)

                    fragment.arguments = bundle
                    ClickHandler().switchBetweenFragments(requireContext(), fragment)
                }else{
                    MakeToast().Warning_MotionToast("الرجاء اختيار الوقت",requireActivity())

                }

            } else {

                MakeToast().Warning_MotionToast("الرجاء اختيار مدينة الوصول ",requireActivity())

            }

    } else {

            MakeToast().Warning_MotionToast(" الرجاء اختيار مدينة الانطلاق",requireActivity())

        }
    }


    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        myDay = day
        myYear = year
        myMonth = month
        view.selectionDate.isVisible = true

        view.selectionDate.text =
            "$myYear-$myMonth-$myDay "

        selectionDate = view.selectionDate.text.toString()

    }


}





