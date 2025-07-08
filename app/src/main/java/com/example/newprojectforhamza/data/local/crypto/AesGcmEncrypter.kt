package com.example.newprojectforhamza.data.local.crypto

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.security.keystore.KeyProperties.BLOCK_MODE_GCM
import android.security.keystore.KeyProperties.ENCRYPTION_PADDING_NONE
import android.security.keystore.KeyProperties.PURPOSE_DECRYPT
import android.security.keystore.KeyProperties.PURPOSE_ENCRYPT
import dagger.hilt.android.qualifiers.ApplicationContext
import java.security.KeyStore
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.inject.Inject
import javax.inject.Singleton
import android.util.Base64

@Singleton
class AesGcmEncrypter @Inject constructor(
    @ApplicationContext context: Context
) : Encrypter {

    companion object {
        private const val KEY_ALIAS = "MOVIE_AES_KEY"
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val IV_SIZE = 12
    }

    private val secretKey: SecretKey by lazy { createOrLoadKey() }

    override fun encrypt(plain: String): String {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        val iv = SecureRandom().generateSeed(IV_SIZE)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, GCMParameterSpec(128, iv))
        val bytes = cipher.doFinal(plain.toByteArray())
        return Base64.encodeToString(iv + bytes, Base64.NO_WRAP)
    }

    override fun decrypt(cipherText: String): String {
        val data = Base64.decode(cipherText, Base64.NO_WRAP)
        val iv = data.copyOfRange(0, IV_SIZE)
        val bytes = data.copyOfRange(IV_SIZE, data.size)
        val cipher = Cipher.getInstance(TRANSFORMATION)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, GCMParameterSpec(128, iv))
        return cipher.doFinal(bytes).decodeToString()
    }

    /** one‑time key generator */
    private fun createOrLoadKey(): SecretKey {
        val ks = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
        if (!ks.containsAlias(KEY_ALIAS)) {
            val spec = KeyGenParameterSpec.Builder(
                KEY_ALIAS,
                PURPOSE_ENCRYPT or PURPOSE_DECRYPT
            )
                .setBlockModes(BLOCK_MODE_GCM)
                .setEncryptionPaddings(ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .build()
            KeyGenerator
                .getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
                .apply { init(spec) }
                .generateKey()
        }
        return ks.getKey(KEY_ALIAS, null) as SecretKey
    }
}
