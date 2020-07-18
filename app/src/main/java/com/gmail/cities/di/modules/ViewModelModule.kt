package com.gmail.cities.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.cities.di.qualifier.ViewModelKey
import com.gmail.cities.presentation.ui.cities_list.CitiesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CitiesListViewModel::class)
    internal abstract fun bindCitiesListViewModel(viewModelStep: CitiesListViewModel): ViewModel
}
