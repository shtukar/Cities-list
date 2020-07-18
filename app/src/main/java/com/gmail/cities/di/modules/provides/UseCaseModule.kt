package com.gmail.cities.di.modules.provides

import com.gmail.cities.domain.repository.CitiesRepository
import com.gmail.cities.domain.usecase.CitiesUseCase
import com.gmail.cities.domain.usecase.CitiesUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesCitiesRepository(repository: CitiesRepository): CitiesUseCase =
            CitiesUseCaseImpl(repository)
}