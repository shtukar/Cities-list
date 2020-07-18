package com.gmail.cities.di

import com.gmail.cities.App
import com.gmail.cities.di.modules.*
import com.gmail.cities.di.modules.provides.RepositoryModule
import com.gmail.cities.di.modules.provides.UseCaseModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ViewModelModule::class,
    FragmentsModule::class,
    ActivitiesModule::class,
    AppModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])

interface AppComponent : AndroidInjector<App> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App>
}