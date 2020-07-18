package com.gmail.cities.di.modules

import android.content.Context
import com.gmail.cities.App
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideApplicationContext(app: App): Context
}
