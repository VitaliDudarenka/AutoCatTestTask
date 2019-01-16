package com.gmail.dudarenka.vitali.autocattesttask.domain.usecases

import com.gmail.dudarenka.vitali.autocattesttask.domain.entity.Car
import com.gmail.dudarenka.vitali.autocattesttask.domain.executor.PostExecutorThread
import com.gmail.dudarenka.vitali.autocattesttask.domain.repositories.CarRepository
import io.reactivex.Observable
import javax.inject.Inject

class UpdateCarUseCase @Inject constructor(postExecutorThread: PostExecutorThread,
                                           private val carRepository: CarRepository) : BaseUseCase(postExecutorThread) {

    fun update(car: Car): Observable<Int> {
        return carRepository.update(car).subscribeOn(workExecutorThread).observeOn(postExecutorThread)
    }

}



