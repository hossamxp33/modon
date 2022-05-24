package com.tarwej.modon.presentation.flightlistfragment.mvi

import androidx.lifecycle.viewModelScope
import com.tarwej.modon.BaseViewModel
import com.tarwej.modon.repo.tripfragment.DataRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


class TripViewModel @Inject constructor(private val DateRepoCompnay: DataRepo)
    : BaseViewModel<TripViewState>() {

    val intents : Channel<TripIntent> = Channel<TripIntent>(Channel.UNLIMITED)

    protected val uiState : MutableStateFlow<TripViewState?> = MutableStateFlow(TripViewState())

    val state: MutableStateFlow<TripViewState?> get() = uiState

    private var job: Job? = null

     private val viewStateMapper: suspend (TripIntent)
     -> TripViewState = { mapIntentToViewState(it,DateRepoCompnay) }


    init {

    getIntent()

// لازم ابعت viewstate كامل واتحكم بكل option حسب المطلوب وارساله مرة اخري واستقباله هنا او في ال view

uiState.value = TripViewState().copy(progress = true)
   // launchNextViewStateJob(intents.value)
}

fun getIntent(){

    job = viewModelScope.launch() {

        intents.receiveAsFlow().collect {

            uiState.value = (viewStateMapper(it))

        }
    }
}





    override fun onCleared() {
        super.onCleared()
        job!!.cancel()
    }



}




