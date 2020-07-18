package com.gmail.cities.di.modules

import com.gmail.cities.presentation.common.LoadingFragment
import com.gmail.cities.presentation.ui.cities_list.CitiesListFragment
import com.gmail.cities.presentation.ui.city_map_details.CityMapDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsModule {

    @ContributesAndroidInjector
    abstract fun getLoadingFragment(): LoadingFragment

    @ContributesAndroidInjector
    abstract fun getCitiesListFragment(): CitiesListFragment

    @ContributesAndroidInjector
    abstract fun getMapFragment(): CityMapDetailsFragment

}
