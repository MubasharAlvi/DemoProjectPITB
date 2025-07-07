package com.example.newprojectforhamza

import android.content.Context
import com.example.newprojectforhamza.data.local.dao.PopularMovieDao
import com.example.newprojectforhamza.data.local.dao.TopRatedMovieDao
import com.example.newprojectforhamza.data.remote.apiService.ApiService
import com.example.newprojectforhamza.data.remote.secretKey.SecretProvider
import com.example.newprojectforhamza.data.repository.MovieRepositoryImp
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class MovieRepositoryImpTest {

    /** ---------- mocks ---------- */
    @Mock lateinit var api: ApiService
    @Mock lateinit var popularDao: PopularMovieDao
    @Mock lateinit var topDao: TopRatedMovieDao
    @Mock lateinit var secretProvider: SecretProvider      // returns keys from NDK

    /** The class under test */
    private lateinit var repository: MovieRepositoryImp

    @Before
    fun setUp() {
        /** context stub: getSystemService → null → isOnline() == false */
        val ctx: Context = mock {
            on { getSystemService(any()) }.thenReturn(null)
        }

        /** secretProvider stubs (only the one we need for this test) */
        whenever(secretProvider.apiKey).thenReturn("dummy_key")

        /** build repository */
        repository = MovieRepositoryImp(
            context        = ctx,
            api            = api,
            daoPopular     = popularDao,
            daoTop         = topDao,
            secretProvider = secretProvider
        )
    }

    @Test
    fun `when API throws, repository emits Success with cached data`() = runTest {
        /** GIVEN : API call fails */
        whenever(api.popularMovies(any(), any()))
            .thenThrow(IOException("Boom"))

        /** AND : DAO already has cached entities (empty list OK for this test) */
        whenever(popularDao.observePopular())
            .thenReturn(flowOf(emptyList()))

        /** WHEN */
        val firstState = repository.getPopularMovies().first()

        /** THEN */
        assertTrue(firstState is ResourceApiState.Success)
    }
}
