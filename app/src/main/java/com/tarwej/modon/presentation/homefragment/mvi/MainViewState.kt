package com.tarwej.modon.presentation.homefragment.mvi

import com.tarwej.modon.entities.cities.CitiesModel


data class MainViewState(
    var homepagedata: CitiesModel ? =null,
    val progress:  Boolean? = null,
    var error:UserError?=null

)