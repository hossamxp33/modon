package com.tarwej.modon.presentation.flightlistfragment.mvi

import com.tarwej.modon.entities.cities.CitiesModel
import com.tarwej.modon.entities.trips.TripModel
import com.tarwej.modon.presentation.homefragment.mvi.UserError


data class TripViewState(
    var data: TripModel ? =null,

    var progress:  Boolean? = null,

    var error: UserError?=null

)