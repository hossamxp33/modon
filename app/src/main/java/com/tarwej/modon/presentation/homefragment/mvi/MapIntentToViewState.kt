package com.tarwej.modon.presentation.homefragment.mvi


import com.tarwej.modon.entities.cities.CitiesModel
import com.tarwej.modon.repo.homefragment.DataRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first


/**
 * this is the model function in MVI, it's responsibility is to convert intents into views states
 */
suspend fun mapIntentToViewState(
    intent: MainIntent,
    Datarepo: DataRepo,
    loadMainData: suspend () -> Flow<Result<CitiesModel>> = { Datarepo.getCities },
) = when (intent) {
    is MainIntent.Initialize -> proceedWithInitialize(loadMainData,intent)
    is MainIntent.ErrorDisplayed -> intent.viewState.copy(error = null)


}


private suspend fun proceedWithInitialize(loadCart: suspend () -> Flow<Result<CitiesModel>>, intent:MainIntent): MainViewState {

    val response =   loadCart()


    return runCatching {
        intent.viewState!!.copy(homepagedata = response.first().getOrThrow(), error = null, progress = false)
    }
        .getOrElse {
            intent.viewState!!.copy(error = UserError.NetworkError(it))
        }
}




