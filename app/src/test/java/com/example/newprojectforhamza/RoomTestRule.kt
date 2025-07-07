@file:Suppress("unused")

package com.example.newprojectforhamza

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.newprojectforhamza.data.local.movieDB.AppDatabase
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * JUnit TestRule that spins an in‑memory Room DB and closes it automatically.
 */
class RoomTestRule : TestWatcher() {

    lateinit var db: AppDatabase
        private set

    override fun starting(description: Description) {
        super.starting(description)
        val ctx = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(ctx, AppDatabase::class.java)
            .allowMainThreadQueries() // fine for unit tests
            .build()
    }

    override fun finished(description: Description) {
        super.finished(description)
        db.close()
    }
}
