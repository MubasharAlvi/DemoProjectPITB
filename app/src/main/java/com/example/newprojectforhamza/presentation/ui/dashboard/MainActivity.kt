package com.example.newprojectforhamza.presentation.ui.dashboard

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (savedInstanceState == null) {
            supportFragmentManager.commit { replace(R.id.main_fragment, AllMovieFragment()) }
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.all      -> supportFragmentManager.commit {
                    replace(R.id.main_fragment, AllMovieFragment())
                }
                R.id.top      -> supportFragmentManager.commit {
                    replace(R.id.main_fragment, TopRatedMovieFragment())
                }
                R.id.popular  -> supportFragmentManager.commit {
                    replace(R.id.main_fragment, PopularMovieFragment())
                }
            }
            true
        }
    }
}