package com.tarwej.modon.repo.homefragment


import com.tarwej.modon.datalayer.APIServices
import com.tarwej.modon.entities.cities.CitiesModel
import com.tarwej.modon.entities.cities.Data
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
private val ApiService: APIServices
) : DataSource {



    override suspend fun getCities(): CitiesModel {
        return  ApiService.getMainData()
    }


}