package com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.details

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.gmail.dudarenka.vitali.autocattesttask.domain.entity.Car
import com.gmail.dudarenka.vitali.autocattesttask.domain.usecases.GetByIdUseCase
import com.gmail.dudarenka.vitali.autocattesttask.domain.usecases.UpdateCarUseCase
import com.gmail.dudarenka.vitali.autocattesttask.presentation.app.App
import com.gmail.dudarenka.vitali.autocattesttask.presentation.base.BaseViewModel
import com.gmail.dudarenka.vitali.autocattesttask.presentation.executor.UIThread
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.CarsRouter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class CarDetailsViewModel : BaseViewModel<CarsRouter>() {
    val make = ObservableField<String>(" ")
    val model = ObservableField<String>(" ")
    val imgUrl = ObservableField<String>(" ")
    val capacity = ObservableField<String>(" ")
    val year = ObservableField<String>(" ")
    val gearBox = ObservableField<Boolean>(true)
    val price = ObservableField<String>(" ")
    private var carId: String? = null
    val isProgressEnabled = ObservableBoolean(false)
    @Inject
    lateinit var carByIdUseCase: GetByIdUseCase
    @Inject
    lateinit var updateCarUseCase: UpdateCarUseCase

    fun setCarId(id: String) {
        if (carId != null) return
        carId = id
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        val disposable = carByIdUseCase.getById(id).subscribeBy(
                onNext = {
                    make.set(it.make)
                    model.set(it.model)
                    imgUrl.set(it.imgUrl)
                    capacity.set(it.capacity)
                    year.set(it.year)
                    gearBox.set(it.gearBox)
                    price.set(it.price)
                    isProgressEnabled.set(false)
                },
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
    }

    fun onClickSave() {
        if (!checkForm()) {
            router?.showSaveError()
            return
        }
        val car = Car(id = this.carId!!, make = make.get()!!, model = model.get()!!, capacity = capacity.get()!!,
                gearBox = gearBox.get()!!, year = year.get()!!, price = price.get()!!, imgUrl = imgUrl.get()!!)
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        Observable.fromCallable {
            updateCarUseCase.update(car)
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
