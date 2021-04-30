package com.app.hepsiburada.core.di

import com.app.hepsiburada.BuildConfig
import com.app.hepsiburada.core.base.Constants
import com.app.hepsiburada.core.network.ITunesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String): ITunesService =
        Retrofit.Builder()
            .client(OkHttpClient.Builder().also { client ->
                if (BuildConfig.DEBUG) {
                    val loggia = HttpLoggingInterceptor()
                    loggia.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(loggia)
                }

            }.build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ITunesService::class.java)

}