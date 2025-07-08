package com.example.newprojectforhamza.objects

import com.example.newprojectforhamza.FakeEncrypter
import com.example.newprojectforhamza.data.local.crypto.Encrypter
import com.example.newprojectforhamza.data.local.di.CryptoModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CryptoModule::class]
)
object FakeCryptoModule {

    @Provides
    @Singleton
    fun provideFakeEncrypter(): Encrypter = FakeEncrypter()
}
