package com.gmail.dudarenka.vitali.autocattesttask.domain.entity


data class Car(val id: String, val make: String, val model: String,
               val capacity: String, val gearBox: Boolean, val year: String, val price: String, val imgUrl: String?) : DomainEntity {

}