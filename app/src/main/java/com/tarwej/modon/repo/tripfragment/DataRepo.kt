package com.tarwej.modon.repo.tripfragment


import com.tarwej.modon.di.IoDispatcher
import com.tarwej.modon.entities.cities.CitiesModel
import com.tarwej.modon.entities.trips.TripModel
import com.tarwej.modon.repo.homefragment.DataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject

class DataRepo @Inject constructor(
    private val Datasources: DataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
)
{


    fun getTrip(trip_to: Int, trip_from: Int, trip_date: String): Flow<Result<TripModel>> =

        flow {
            emit(Datasources.getTrip(trip_to,trip_from,trip_date))
        }
            .map { Result.success(it) }
            .retry(4){ t -> (t is IOException).also { if (it) {
                delay(1000)
            }}}
            .catch { throwable -> emit(Result.failure(throwable)) }
            .flowOn(ioDispatcher)





}