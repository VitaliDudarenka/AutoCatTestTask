package com.gmail.dudarenka.vitali.autocattesttask.data.repositories

import com.gmail.dudarenka.vitali.autocattesttask.data.db.dao.CarDao
import com.gmail.dudarenka.vitali.autocattesttask.data.db.entity.CarDB
import com.gmail.dudarenka.vitali.autocattesttask.data.db.entity.transformToDB
import com.gmail.dudarenka.vitali.autocattesttask.data.db.entity.transformToDomain
import com.gmail.dudarenka.vitali.autocattesttask.domain.entity.Car
import com.gmail.dudarenka.vitali.autocattesttask.domain.repositories.CarRepository
import io.reactivex.Observable

class CarRepositoryImpl(private val carDao: CarDao) : CarRepository {

    override fun get(): Observable<List<Car>> {
        return carDao.getAll()
                .take(1)
                .toObservable()
                .flatMap { carDBList ->
                    if (carDBList.isEmpty()) {
                        val list: List<CarDB> = listOf(CarDB("0", "Renault", "Duster", "2.0", true, "2105", "10500", "https://stimg.cardekho.com/images/carexteriorimages/630x420/Renault/Renault-Duster/4978/front-left-side-47.jpg"),
                                CarDB("1", "Renault", "Sandero", "1.6", true, "2016", "10000", "https://www.cdn.renault.com/content/dam/Renault/BY/personal-cars/sandero/b52-sandero/b52-sandero-phase1/design/renault-sandero-design-206_3072x1728.jpg.ximg.l_4_m.smart.jpg"),
                                CarDB("2", "Renault", "Duster", "1.6", false, "2014", "7500", "https://stimg.cardekho.com/images/carexteriorimages/630x420/Renault/Renault-Duster/4978/front-left-side-47.jpg"),
                                CarDB("3", "Kia", "Rio", "1.4", true, "2017", "10100", "https://d1ek71enupal89.cloudfront.net/images/blocks_png/KIA/RIO/5DR/17KiaRio15drWhiFL1_800.jpg"),
                                CarDB("4", "Kia", "Ceed", "1.6", false, "2017", "14300", "https://d1ek71enupal89.cloudfront.net/images/blocks_png/KIA/CEED/ESTATE/17KiaCeeGtlEstWhiFR1_800.jpg"),
                                CarDB("5", "Kia", "Ceed", "1.6", true, "2017", "12000", "https://d1ek71enupal89.cloudfront.net/images/blocks_png/KIA/CEED/ESTATE/17KiaCeeGtlEstWhiFR1_800.jpg"),
                                CarDB("6", "Opel", "Astra", "1.8", true, "2016", "12000", "https://ru.wikipedia.org/wiki/Opel_Astra_K#/media/File:Opel_Astra_1.6_CDTI_ecoFLEX_Edition_(K)_%E2%80%93_Frontansicht,_13._Oktober_2015,_D%C3%BCsseldorf.jpg"),
                                CarDB("7", "Opel", "Astra", "1.6", true, "2015", "11500", "https://ru.wikipedia.org/wiki/Opel_Astra_K#/media/File:Opel_Astra_1.6_CDTI_ecoFLEX_Edition_(K)_%E2%80%93_Frontansicht,_13._Oktober_2015,_D%C3%BCsseldorf.jpg"),
                                CarDB("8", "Volkswagen", "Polo", "1.6", false, "2017", "10200", "https://cdn.euroncap.com/media/30740/volkswagen-polo-359-235.jpg"),
                                CarDB("9", "Volkswagen", "Jetta", "2.0", true, "2016", "13500", "https://upload.wikimedia.org/wikipedia/commons/f/f5/VW_Jetta_VII_P4220682.jpg"))
                        carDao.insert(list)
                    }
                    Observable.just(carDBList).map { list ->
                        list.map { car -> car.transformToDomain() }
                    }
                }
    }

    override fun getById(id: String): Observable<Car> {
        return carDao.getById(id).take(1).toObservable().map {
            it.transformToDomain()
        }
    }

    override fun update(car: Car): Observable<Int> {
        return Observable.just(carDao.update(car.transformToDB()))
    }

    override fun add(car: Car): Observable<Long> {
        return Observable.just(carDao.insert(car.transformToDB()))
    }

}