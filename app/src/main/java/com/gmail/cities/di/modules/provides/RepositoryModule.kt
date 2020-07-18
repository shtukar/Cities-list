package com.gmail.cities.di.modules.provides

import android.content.Context
import com.gmail.cities.data.repository.CitiesRepositoryImpl
import com.gmail.cities.domain.repository.CitiesRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesCitiesRepository(context: Context): CitiesRepository =
            CitiesRepositoryImpl(context)

}