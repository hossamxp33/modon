package com.tarwej.modon.di

import android.content.Context
import com.example.dagger.di.module.ActivityBuildersModule
import com.example.satafood.presentaion.mainactivity.di.MainModule
import com.tarwej.modon.MainActivity

import com.tarwej.modon.datalayer.APIServices
import com.tarwej.modon.helper.Constants.Companion.BASE_URL
import com.tarwej.modon.helper.FragmentFactoryModule
import com.tarwej.modon.helper.ViewModelBuilderModule
import com.tarwej.modon.presentation.homefragment.HomeFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 *
 * @author juancho.
 */
@Singleton
@Component(
    modules =
    [   DispatcherModule::class,
        AndroidInjectionModule::class,
        APIModule::class,
        AppModule::class,
        FragmentFactoryModule::class,
        MainModule::class,
        ActivityBuildersModule::class,
        FragmentFactoryModule::class,
        ViewModelBuilderModule::class,

    ]
)



interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }


    fun inject(app: MainActivity)
    fun inject(app: HomeFragment)

}


@Module
class APIModule constructor() {

    @Singleton
    @Provides
    fun provideHttpClient(context: Context): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain: Interceptor.Chain ->
                val originalRequest = chain.request()
            //     var Pref = PreferenceHelper(context)
                val builder = originalRequest.newBuilder()
             //   builder.addHeader("Accept", "application/json")
             //   builder.addHeader("Content-Type", "application/json")
               builder.addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImV4cCI6MTY0NjQ4MDY1MX0.UreCRAhFIZL7enQRKBRwYAhdkTPGHvVWWctA6LyaJSI")
//                Log.d("token",Pref.token!!)
                val newRequest = builder.build()
                chain.proceed(newRequest)
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun ApiServices(retrofit: Retrofit): APIServices = retrofit.create(APIServices::class.java)


}
