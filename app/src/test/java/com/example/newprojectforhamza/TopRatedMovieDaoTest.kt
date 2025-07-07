package com.example.newprojectforhamza

import app.cash.turbine.test
import com.example.newprojectforhamza.data.local.entity.TopRatedMoviesEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TopRatedMovieDaoTest {

    @get:Rule val room = RoomTestRule()
    private val dao get() = room.db.topRatedMovieDao()

    private val dummy = TopRatedMoviesEntity(
        adult            = false,
        backdropPath     = "b.jpg",
        genreIds         = listOf(28),
        id               = 42,
        originalLanguage = "en",
        originalTitle    = "Hello",
        overview         = "World",
        popularity       = 99.0,
        posterPath       = "p.jpg",
        releaseDate      = "2025‑12‑31",
        title            = "Hello",
        video            = false,
        voteAverage      = 8.8,
        voteCount        = 9000
    )

    @Test
    fun insertAndObserveTopRated() = runTest {
        dao.insert(dummy)
        dao.observeTopRated().test {
            val list = awaitItem()
            assertEquals(1, list.size)
            assertEquals(dummy.title, list.first().title)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun clearDeletesEverything() = runTest {
        dao.insert(dummy)
        dao.clear()
        dao.observeTopRated().test {
            assertTrue(awaitItem().isEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
