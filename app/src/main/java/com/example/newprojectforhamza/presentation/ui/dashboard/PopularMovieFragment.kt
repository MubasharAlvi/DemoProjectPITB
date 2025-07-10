package com.example.newprojectforhamza.presentation.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newprojectforhamza.data.remote.secretKey.SecretProvider
import com.example.newprojectforhamza.presentation.ui.dashboard.viewModels.PopularViewModel
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade

@AndroidEntryPoint
class PopularMovieFragment : Fragment() {
    private val vm: PopularViewModel by viewModels()

    @Inject lateinit var secretProvider: SecretProvider

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MaterialTheme {
                AllMovieScreen(vm)
            }
        }
    }

    @Composable
    fun AllMovieScreen(vm: PopularViewModel) {
        val state by vm.moviesState.collectAsStateWithLifecycle()

        val listState = rememberSaveable(saver = LazyListState.Saver) {
            LazyListState()
        }
        LaunchedEffect(Unit) { vm.fetchMovies() }

        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is ResourceApiState.Loading -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )

                is ResourceApiState.Error -> {
                    Text(
                        text = state.message?: "Unknow",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                is ResourceApiState.Success -> {
                    val movies = state.data ?: emptyList()
                    LazyColumn(state =  listState) {
                        items(
                            items = movies,
                            key   = { it.id!! }
                        ) { mov ->
                            Card( modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(6.dp)
                                        .height(220.dp),
                                    shape = RoundedCornerShape(15.dp)
                                ) {
                            Box {
                                Poster(
                                    urlBase = secretProvider.urlImg,
                                    path = mov.backdropPath.toString(),
                                    modifier = Modifier.fillMaxSize()
                                )

                                Text(
                                    text =  "Hello",
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodyLarge,
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .background(Color(0x99000000))
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                )
                            } }
                            }
                        }
                    }
                }
            }
    }

    @Composable
    fun Poster(
        urlBase: String,
        path: String?,
        modifier: Modifier = Modifier
    ) {
        val ctx = LocalContext.current
        Log.e("TAG","IMG path===$path")
        AsyncImage(
            model = ImageRequest.Builder(ctx)
                .data(urlBase + (path ?: ""))
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }

}