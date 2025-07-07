package com.example.newprojectforhamza.presentation.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.databinding.FragmentHomeBinding
import com.example.newprojectforhamza.domain.domainModels.Movie
import com.example.newprojectforhamza.presentation.ui.adapter.RecycleViewAdapter
import com.example.newprojectforhamza.presentation.ui.viewModels.MoviesViewModel
import com.example.newprojectforhamza.presentation.utils.ResourceApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), RecycleViewAdapter.InterfaceClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: RecycleViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** ----------  Create adapter and RecyclerView ---------- */
        moviesAdapter = RecycleViewAdapter(
            ctx = requireContext(),
            movieList = arrayListOf(),
            layout = R.layout.item_list,
            interfaceClickListener = this
        )

        binding.recycleId.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }

       /**----------  Kick off the first fetch ---------- */
      viewModel.fetchMovies()

        /** ----------  Observe the StateFlow ---------- */
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesState.collect { state ->
                    when (state) {
                        is ResourceApiState.Loading -> binding.progressbarId.visibility = View.VISIBLE
                        is ResourceApiState.Success -> {
                            binding.progressbarId.visibility = View.GONE
                            val data = state.data ?: return@collect
                            recycleViewFun(data as ArrayList<Movie>)
                        }
                        is ResourceApiState.Error -> {
                            binding.progressbarId.visibility = View.GONE
                            Toast.makeText(
                                requireContext(),
                                state.message ?: getString(R.string.unknown_errors),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
        }
    }

    /** ----------  Handle row clicks ---------- */
    override fun moviePlayer(movieModelListPosition: Movie, position: Int) {
        Toast.makeText(requireContext(), movieModelListPosition.title, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun recycleViewFun(movieList: ArrayList<Movie>) {
        binding.recycleId.layoutManager = LinearLayoutManager(context)
        moviesAdapter = RecycleViewAdapter(
            requireContext(), movieList,
            R.layout.item_list, this
        )
        binding.recycleId.adapter = moviesAdapter
        moviesAdapter!!.notifyDataSetChanged()
    }

}

