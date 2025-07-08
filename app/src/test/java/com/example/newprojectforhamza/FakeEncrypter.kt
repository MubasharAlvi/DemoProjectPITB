package com.example.newprojectforhamza

import com.example.newprojectforhamza.data.local.crypto.Encrypter

class FakeEncrypter : Encrypter {
    override fun encrypt(plain: String): String = plain.reversed()
    override fun decrypt(cipher: String): String = cipher.reversed()
}
