package com.tarwej.modon.repo.homefragment


import com.tarwej.modon.datalayer.APIServices
import com.tarwej.modon.entities.cities.CitiesModel
import com.tarwej.modon.entities.cities.Data
import com.tarwej.modon.entities.trips.TripModel
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
private val ApiService: APIServices
) : DataSource {



    override suspend fun getCities(): CitiesModel {
        return  ApiService.getMainData()
    }
    override suspend fun getTrip(trip_to : Int,trip_from : Int,trip_date : String,): TripModel {
        return  ApiService.searchForTrip(trip_to,trip_from,trip_date)

    }

}