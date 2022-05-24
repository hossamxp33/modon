package com.tarwej.modon.di


import com.tarwej.modon.datalayer.APIServices
import com.tarwej.modon.repo.homefragment.DataSource


import com.tarwej.modon.repo.homefragment.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {


    @Singleton
    @Provides
    fun provideTasksRemoteDataSource(apiService: APIServices): DataSource {
        return RemoteDataSource(apiService)
    }





//    @Provides
//    fun providePreferenceHelper(context: Context): PreferenceHelper {
//        return PreferenceHelper(context)
//    }

}
