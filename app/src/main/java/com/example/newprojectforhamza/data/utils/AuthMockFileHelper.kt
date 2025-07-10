package com.example.newprojectforhamza.data.utils

import android.content.Context
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.data.remote.models.AuthFileDTO

object AuthMockFileHelper {
    private const val FILE_NAME = "auth_mock.json"
    private val gson = Gson()


    suspend fun read(context: Context): AuthFileDTO = withContext(Dispatchers.IO) {
        val file = File(context.filesDir, FILE_NAME)
        gson.fromJson(file.readText(), AuthFileDTO::class.java)
    }
    private const val VERSION = 2
    private const val PREF_NAME = "auth_mock"
    private const val PREF_KEY = "file_version"

    suspend fun ensureWritable(context: Context) = withContext(Dispatchers.IO) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val dst = File(context.filesDir, FILE_NAME)

        if (prefs.getInt(PREF_KEY, 0) < VERSION) {
            dst.delete()
            context.resources.openRawResource(R.raw.auth_mock).use { inp ->
                dst.outputStream().use { out -> inp.copyTo(out) }
            }
            prefs.edit().putInt(PREF_KEY, VERSION).apply()
        }
    }

    suspend fun write(context: Context, dto: AuthFileDTO) = withContext(Dispatchers.IO) {
        val file = File(context.filesDir, FILE_NAME)
        val json = gson.toJson(dto)
        file.writeText(json)
    }



}
