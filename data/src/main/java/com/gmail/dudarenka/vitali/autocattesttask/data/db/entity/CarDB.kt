package com.gmail.dudarenka.vitali.autocattesttask.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.time.LocalDate


@Entity(tableName = "car")
data class CarDB(@PrimaryKey val id: String, val make: String, val model: String,
                 val capacity: String, val gearBox: Boolean, val year: String, val price: String, val imgUrl: String?) {
}




