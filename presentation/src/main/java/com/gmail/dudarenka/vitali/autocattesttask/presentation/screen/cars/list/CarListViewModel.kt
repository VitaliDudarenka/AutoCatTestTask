package com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.list

import android.databinding.ObservableBoolean
import com.gmail.dudarenka.vitali.autocattesttask.domain.entity.Car
import com.gmail.dudarenka.vitali.autocattesttask.domain.usecases.GetCarsUseCase
import com.gmail.dudarenka.vitali.autocattesttask.presentation.app.App
import com.gmail.dudarenka.vitali.autocattesttask.presentation.base.BaseViewModel
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.CarsRouter
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class CarListViewModel : BaseViewModel<CarsRouter>() {
    var adapter: CarListAdapter? = CarListAdapter()
    val isProgressEnabled = ObservableBoolean(false)
    @Inject
    lateinit var getCarsUseCase: GetCarsUseCase

    init {
        App.appComponent.inject(this)
        isProgressEnabled.set(true)
        val disposable = getCarsUseCase.get().subscribeBy(
                onNext = {
                    adapter?.listData = it.toMutableList()
                    isProgressEnabled.set(false)
                },
                onError = {
                    isProgressEnabled.set(false)
                    router?.showError(it)
                }
        )
        addToDisposable(disposable)
        adapter?.onItemClickListener = object : CarListAdapter.OnItemClickListener {
            override fun onItemClick(car: Car) {
                router!!.goToCarDetails(car.id)
            }
        }
    }

    fun onClickAdd() {
        router!!.goToCarAdding()
    }


}