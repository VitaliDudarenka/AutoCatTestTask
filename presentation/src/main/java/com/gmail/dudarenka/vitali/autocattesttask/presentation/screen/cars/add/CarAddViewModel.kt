package com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.add

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.gmail.dudarenka.vitali.autocattesttask.domain.entity.Car
import com.gmail.dudarenka.vitali.autocattesttask.domain.usecases.AddCarUseCase
import com.gmail.dudarenka.vitali.autocattesttask.domain.usecases.GetByIdUseCase
import com.gmail.dudarenka.vitali.autocattesttask.domain.usecases.UpdateCarUseCase
import com.gmail.dudarenka.vitali.autocattesttask.presentation.app.App
import com.gmail.dudarenka.vitali.autocattesttask.presentation.base.BaseViewModel
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.CarsRouter
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CarAddViewModel : BaseViewModel<CarsRouter>() {
    val make = ObservableField<String>("")
    val model = ObservableField<String>("")
    val imgUrl = ObservableField<String>("image url")
    val capacity = ObservableField<String>("")
    val year = ObservableField<String>("")
    val gearBox = ObservableField<Boolean>(true)
    val price = ObservableField<String>("")
    val isProgressEnabled = ObservableBoolean(false)
    @Inject
    lateinit var addCarUseCase: AddCarUseCase

    fun onClickSave() {
        if (!checkForm()) {
            router?.showSaveError()
            return
        }
        val car = Car(id = System.currentTimeMillis().toString(), make = make.get()!!, model = model.get()!!, capacity = capacity.get()!!,
                gearBox = gearBox.get()!!, year = year.get()!!, price = price.get()!!, imgUrl = imgUrl.get()!!)
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        Observable.fromCallable {
            addCarUseCase.add(car)
        }
                .subscribeOn(Schedulers.io())
                .subscribeBy(
                        onComplete = {
                            router!!.goToCarList()
                            isProgressEnabled.set(false)
                        },
                        onError = {
                            isProgressEnabled.set(false)
                            router?.showError(it)
                        }
                )
    }

    private fun checkForm(): Boolean {
        return !(make.get()!!.isEmpty() || model.get()!!.isEmpty() || year.get()!!.isEmpty() || price.get()!!.isEmpty()
                || capacity.get()!!.isEmpty() || imgUrl.get()!!.isEmpty())
    }

    fun gearBoxAuto() {
        gearBox.set(true)
    }

    fun gearBoxManual() {
        gearBox.set(false)
    }

}
