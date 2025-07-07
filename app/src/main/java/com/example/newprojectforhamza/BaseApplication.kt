package com.example.newprojectforhamza

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import java.io.File

@HiltAndroidApp
class BaseApplication : Application(){
    companion object{
        private var cacheDir: File? = null
        private lateinit var applicationClass: BaseApplication

        fun instanceOf(): BaseApplication{
            return applicationClass
        }

        init {
            System.loadLibrary("native-lib")
        }
    }
}