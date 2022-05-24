package com.tarwej.modon.repo.homefragment

import com.tarwej.modon.entities.cities.CitiesModel
import com.tarwej.modon.entities.cities.Data
import com.tarwej.modon.entities.trips.TripModel
import com.tarwej.modon.entities.trips.TripTo


interface DataSource {

    suspend fun getCities():  CitiesModel
    suspend fun getTrip(trip_to: Int, trip_from: Int,trip_date:String):  TripModel


}