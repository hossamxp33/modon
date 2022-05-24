package com.tarwej.modon.presentation.flightlistfragment.mvi


import com.tarwej.modon.entities.cities.CitiesModel
import com.tarwej.modon.entities.trips.TripModel
import com.tarwej.modon.presentation.homefragment.mvi.UserError
import com.tarwej.modon.repo.tripfragment.DataRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first


/**
 * this is the model function in MVI, it's responsibility is to convert intents into views states
 */
suspend fun mapIntentToViewState(
    intent: TripIntent,
    dataRepo: DataRepo,
    loadMainData: suspend () -> Flow<Result<TripModel>> = { dataRepo.getTrip(intent.trip_to!!,intent.trip_from!!,intent.trip_date!!) },
) = when (intent) {
    is TripIntent.GetData -> proceedWithInitialize(loadMainData, intent)
    is TripIntent.ErrorDisplayed -> intent.viewState.copy(error = null)


}


private suspend fun proceedWithInitialize(
    loadCart: suspend () -> Flow<Result<TripModel>>,
    intent: TripIntent
): TripViewState {

    val response = loadCart()


    return runCatching {
        intent.viewState!!.copy(
            data = response.first().getOrThrow(),
            error = null,
            progress = false
        )
    }
        .getOrElse {
            intent.viewState!!.copy(error = UserError.NetworkError(it))
        }
}




