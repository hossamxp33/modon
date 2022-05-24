package com.tarwej.modon.datalayer

import com.tarwej.modon.entities.cities.CitiesModel
import com.tarwej.modon.entities.trips.TripModel
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIServices {



    @GET("cities.json")
    suspend fun getMainData(): CitiesModel

    @FormUrlEncoded
    @POST("trips/findTrip.json")
    suspend fun searchForTrip(
        @Field("trip_to") trip_to: Int,
        @Field("trip_from") trip_from: Int,
        @Field("trip_date") trip_date: String
    ): TripModel




}