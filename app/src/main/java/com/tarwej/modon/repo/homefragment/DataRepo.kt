package com.tarwej.modon.repo.homefragment


import com.tarwej.modon.di.IoDispatcher
import com.tarwej.modon.entities.cities.CitiesModel
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


    ///getRestaurantsListResponse

    val getCities: Flow<Result<CitiesModel>> =
        flow {
            emit(Datasources.getCities())
             }
            .map { Result.success(it) }
            .retry(4){ t -> (t is IOException).also { if (it) {
                delay(1000)
            }}}
            .catch { throwable -> emit(Result.failure(throwable)) }
            .flowOn(ioDispatcher)





}