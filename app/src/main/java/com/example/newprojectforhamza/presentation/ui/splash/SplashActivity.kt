package com.example.newprojectforhamza.presentation.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.newprojectforhamza.R
import com.example.newprojectforhamza.databinding.ActivitySplashBinding
import com.example.newprojectforhamza.presentation.ui.authentication.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    private val splashDuration = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash)

        setupAnimations()
        startProgressAnimation()
        navigateToMain()
        setContentView(binding.root)

    }

    private fun setupAnimations() {
        /** Logo animation */
        binding.logoLayoutId.animation = AnimationUtils.loadAnimation(this, R.anim.popup_animation_logo)

        /** Show progress bar with fade-in */
        binding.progressBarId.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).duration = 500
        }
    }

    private fun startProgressAnimation() {
        /** Smooth animated progress using ObjectAnimator */
        binding.progressBarId.apply {
            max = 100
            progress = 0
            animateProgress(100, splashDuration - 500) /** Finish slightly before timeout*/
        }
    }

    private fun navigateToMain() {
        lifecycleScope.launch {
            delay(splashDuration)
            startActivity(Intent(this@SplashActivity, AuthenticationActivity::class.java))
            finish()

            /** Optional: Add transition animation */
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }

    /** Extension function for smooth progress animation */
    private fun android.widget.ProgressBar.animateProgress(
        target: Int,
        duration: Long
    ) {
        android.animation.ObjectAnimator.ofInt(
            this,
            context.getString(R.string.progress),
            target
        ).apply {
            setDuration(duration)
            interpolator = android.view.animation.AccelerateInterpolator()
            start()
        }
    }
}