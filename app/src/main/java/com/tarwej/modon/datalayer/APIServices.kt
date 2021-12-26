package com.tarwej.modon.datalayer

import com.tarwej.modon.entities.cities.CitiesModel
import retrofit2.http.GET

interface APIServices {



    @GET("cities.json")
    suspend fun getMainData(): CitiesModel

}