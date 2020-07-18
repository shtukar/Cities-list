package com.gmail.cities.presentation.ui.splash

import android.os.Bundle
import android.os.Handler
import com.gmail.cities.R
import com.gmail.cities.presentation.ui.getMainActivityLaunchIntent
import dagger.android.support.DaggerAppCompatActivity

class SplashScreenActivity : DaggerAppCompatActivity() {

    companion object {
        const val DELAY_2000 = 2000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler().postDelayed({
            startActivity(getMainActivityLaunchIntent())
            finishAffinity()
        }, DELAY_2000)
    }
}
