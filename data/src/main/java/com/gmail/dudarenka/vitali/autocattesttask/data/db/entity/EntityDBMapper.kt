package com.gmail.dudarenka.vitali.autocattesttask.data.db.entity

import com.gmail.dudarenka.vitali.autocattesttask.domain.entity.Car


fun CarDB.transformToDomain(): Car {
    return Car(id = id, make = make, model = model, capacity = capacity,
            imgUrl = imgUrl, gearBox = gearBox, year = year, price = price)
}

fun Car.transformToDB(): CarDB {
    return CarDB(id = id, make = make, model = model, capacity = capacity,
            imgUrl = imgUrl, gearBox = gearBox, year = year, price = price)
}


