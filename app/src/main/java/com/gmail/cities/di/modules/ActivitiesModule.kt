package com.gmail.cities.di.modules

import com.gmail.cities.presentation.ui.MainActivity
import com.gmail.cities.presentation.ui.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesModule {

    @ContributesAndroidInjector
    abstract fun getSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector
    abstract fun getMainActivity(): MainActivity
}
