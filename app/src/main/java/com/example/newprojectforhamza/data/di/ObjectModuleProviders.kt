package com.example.newprojectforhamza.data.di

import com.example.newprojectforhamza.data.remote.secretKey.SecretProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecretModule {
    @Singleton
    @Provides
    fun provideSecretProvider(): SecretProvider = SecretProvider()
}

