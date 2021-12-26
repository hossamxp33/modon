package com.tarwej.modon.presentation.homefragment.mvi

import androidx.lifecycle.viewModelScope
import com.tarwej.modon.BaseViewModel

import com.tarwej.modon.repo.homefragment.DataRepo
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

import javax.inject.Inject


class HomeViewModel @Inject constructor(private val DateRepoCompnay: DataRepo)
    : BaseViewModel<MainViewState>() {

    val intents : Channel<MainIntent> = Channel<MainIntent>(Channel.UNLIMITED)

    protected val uiState : MutableStateFlow<MainViewState?> = MutableStateFlow(MainViewState())

    val state: MutableStateFlow<MainViewState?> get() = uiState

    private var job: Job? = null

     private val viewStateMapper: suspend (MainIntent)
     -> MainViewState = { mapIntentToViewState(it,DateRepoCompnay) }


    init {

    getIntent()

// لازم ابعت viewstate كامل واتحكم بكل option حسب المطلوب وارساله مرة اخري واستقباله هنا او في ال view

uiState.value = MainViewState().copy(progress = true)
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




