package com.tarwej.modon.presentation.homefragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
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
import com.tarwej.modon.presentation.homefragment.mvi.HomeViewModel
import com.tarwej.modon.presentation.homefragment.mvi.MainIntent
import com.tarwej.modon.presentation.homefragment.mvi.UserError
import kotlinx.android.synthetic.main.flight_list_fragment.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.flow.collect
import java.util.*
import javax.inject.Inject


class HomeFragment : Fragment(), DatePickerDialog.OnDateSetListener
   {
    var day = 0
    var month: Int = 0
    var year: Int = 0
    var myDay = 0
    var myMonth: Int = 0
    var myYear: Int = 0

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
        savedInstanceState: Bundle?): View? { view = DataBindingUtil.inflate(inflater,
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
                DatePickerDialog(requireContext(), this, year, month, day)
            datePickerDialog.show()
        }
        return view.root
    }

    fun getAllData() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
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
                            //        view.progress.visibility = View.GONE

                            //                   if (it.homepagedata?.data.isNullOrEmpty())
//
//                            ///newOffersAdapter
//                            myOrdersAdapter.submitList(it.data)
//
//                            else
//                                view.noOrderFoundLayout.isVisible = it.noOrderYet!!


                        }
                    }
                }

            }
        }
    }

    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        myDay = day
        myYear = year
        myMonth = month

        view.selectionDate.isVisible = true

        view.selectionDate.text =
            "السنة:  $myYear   الشهر: $myMonth  اليوم: $myDay "
    }


}





