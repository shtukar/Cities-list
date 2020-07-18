package com.gmail.cities.di.modules

import com.gmail.cities.presentation.common.LoadingFragment
import com.gmail.cities.presentation.ui.cities_list.CitiesListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun getLoadingFragment(): LoadingFragment

    @ContributesAndroidInjector
    abstract fun getCitiesListFragment(): CitiesListFragment

}
