package com.example.newprojectforhamza.data.di

import com.example.newprojectforhamza.data.remote.secretKey.SecretProvider
import com.example.newprojectforhamza.data.repository.AuthRepository
import com.example.newprojectforhamza.data.repository.AuthRepositoryImp
import com.example.newprojectforhamza.data.repository.MovieRepository
import com.example.newprojectforhamza.data.repository.MovieRepositoryImp
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

    @Singleton
    @Provides
    fun provideAuthRepository(
        authRepositoryImp: AuthRepositoryImp
    ): AuthRepository {
        return authRepositoryImp
    }

}

