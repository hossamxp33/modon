package com.tarwej.modon.presentation.flightlistfragment.mvi


sealed class TripIntent(
    open val viewState: TripViewState? = null,
    open val trip_to: Int? = null,
    open val trip_from: Int? = null,
    open val trip_date: String? = null
) {

    data class GetData(
        override val viewState: TripViewState,
        override val trip_to: Int?, override val trip_from: Int?, override val trip_date: String?
    ) : TripIntent()

    data class ErrorDisplayed(override val viewState: TripViewState) : TripIntent()

}
