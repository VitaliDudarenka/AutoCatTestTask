package com.gmail.dudarenka.vitali.autocattesttask.data.db.dao

import android.arch.persistence.room.*
import com.gmail.dudarenka.vitali.autocattesttask.data.db.entity.CarDB
import io.reactivex.Flowable


@Dao
interface CarDao {
    @Insert
    fun insert(car: List<CarDB>)

    @Insert
    fun insert(car: CarDB): Long

    @Update
    fun update(car: CarDB): Int

    @Delete
    fun delete(car: CarDB)

    @Query("DELETE FROM car")
    fun deleteAll()

    @Query("SELECT * FROM car ORDER BY id DESC")
    fun getAll(): Flowable<List<CarDB>>

    @Query("SELECT * FROM car WHERE id = :id LIMIT 1")
    fun getById(id: String): Flowable<CarDB>

    @Query("DELETE FROM car WHERE id = :id")
    fun deleteById(id: String)
}