package com.gmail.dudarenka.vitali.autocattesttask.presentation.inject

import android.content.Context
import com.gmail.dudarenka.vitali.autocattesttask.data.db.AppDataBase
import com.gmail.dudarenka.vitali.autocattesttask.data.db.dao.CarDao
import com.gmail.dudarenka.vitali.autocattesttask.data.repositories.CarRepositoryImpl
import com.gmail.dudarenka.vitali.autocattesttask.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.autocattesttask.domain.repositories.CarRepository
import com.gmail.dudarenka.vitali.autocattesttask.presentation.executor.UIThread
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideCarRepository(carDao: CarDao): CarRepository = CarRepositoryImpl(carDao)

    @Provides
    fun provideCarDao(appDataBase: AppDataBase): CarDao = appDataBase.getCarDao()

    @Provides
    fun provideAppDataBase(context: Context): AppDataBase = AppDataBase.getInstance(context)

    @Provides
    fun providePostExecutorThread(): PostExecutorThread = UIThread()

}