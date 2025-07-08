package com.example.newprojectforhamza.data.remote.network

import com.example.newprojectforhamza.BuildConfig
import com.example.newprojectforhamza.data.remote.apiService.ApiService
import com.example.newprojectforhamza.data.remote.secretKey.SecretProvider
import com.example.newprojectforhamza.data.repository.MovieRepository
import com.example.newprojectforhamza.data.repository.MovieRepositoryImp
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
object ObjectModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() =  if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
        secretProvider: SecretProvider
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(secretProvider.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(
        movieRepositoryImp: MovieRepositoryImp
    ): MovieRepository {
        return movieRepositoryImp
    }
}