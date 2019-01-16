package com.gmail.dudarenka.vitali.autocattesttask.presentation.inject

import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.add.CarAddViewModel
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.details.CarDetailsViewModel
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.list.CarListViewModel
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(carListViewModel: CarListViewModel)
    fun inject(carDetailsViewModel: CarDetailsViewModel)
    fun inject(carAddViewModel: CarAddViewModel)
}