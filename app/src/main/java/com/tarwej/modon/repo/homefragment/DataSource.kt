package com.tarwej.modon.repo.homefragment

import com.tarwej.modon.entities.cities.CitiesModel
import com.tarwej.modon.entities.cities.Data


interface DataSource {

    suspend fun getCities():  CitiesModel

}