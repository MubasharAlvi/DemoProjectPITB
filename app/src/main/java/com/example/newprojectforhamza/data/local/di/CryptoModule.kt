package com.example.newprojectforhamza.data.local.di

import com.example.newprojectforhamza.data.local.crypto.AesGcmEncrypter
import com.example.newprojectforhamza.data.local.crypto.Encrypter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CryptoModule {

    @Provides
    @Singleton
    fun provideEncrypter(
        impl: AesGcmEncrypter
    ): Encrypter = impl
}
