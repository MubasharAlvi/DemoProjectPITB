package com.example.newprojectforhamza.data.local.crypto

interface Encrypter {
    fun encrypt(plain: String): String
    fun decrypt(cipher: String): String
}
