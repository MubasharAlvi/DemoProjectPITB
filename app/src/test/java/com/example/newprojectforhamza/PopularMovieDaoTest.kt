package com.example.newprojectforhamza

import app.cash.turbine.test
import com.example.newprojectforhamza.data.local.entity.PopularMoviesEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PopularMovieDaoTest {

    @get:Rule val room = RoomTestRule()
    private val dao get() = room.db.popularMovieDao()

    private val dummyEntity = PopularMoviesEntity(
        adult            = false,
        backdropPath     = "backdrop.jpg",
        genreIds         = listOf(12, 35),
        id               = 1,
        originalLanguage = "en",
        originalTitle    = "Foo",
        overview         = "bar",
        popularity       = 99.9,
        posterPath       = "poster.jpg",
        releaseDate      = "2025‑01‑01",
        title            = "Foo",
        video            = false,
        voteAverage      = 7.5,
        voteCount        = 100
    )

    @Test
    fun insertAndObservePopular() = runTest {
        // GIVEN
        dao.insert(dummyEntity)

        // WHEN / THEN
        dao.observePopular().test {
            val list = awaitItem()
            assertEquals(1, list.size)
            assertEquals(dummyEntity.id, list.first().id)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun clearDeletesEverything() = runTest {
        dao.insert(dummyEntity)
        dao.clear()
        dao.observePopular().test {
            assertTrue(awaitItem().isEmpty())
            cancelAndIgnoreRemainingEvents()
        }
    }
}
