package com.gmail.dudarenka.vitali.autocattesttask.domain.repositories

import com.gmail.dudarenka.vitali.autocattesttask.domain.entity.Car
import io.reactivex.Observable

interface CarRepository : BaseRepository {
    fun get(): Observable<List<Car>>
    fun getById(id: String): Observable<Car>
    fun update(car: Car): Observable<Int>
    fun add(car: Car): Observable<Long>
}