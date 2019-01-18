package com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars

import android.widget.Toast
import com.gmail.dudarenka.vitali.autocattesttask.R
import com.gmail.dudarenka.vitali.autocattesttask.presentation.base.BaseRouter
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.add.CarAddFragment
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.details.CarDetailsFragment
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.list.CarListFragment


class CarsRouter(activity: CarsActivity) : BaseRouter<CarsActivity>(activity) {

    fun goToCarList() {
        replaceFragment(activity.supportFragmentManager, CarListFragment.getInstance(), R.id.container, false)
    }

    fun goToCarDetails(id: String) {
        replaceFragment(activity.supportFragmentManager, CarDetailsFragment.getInstance(id), R.id.container, true)
    }

    fun goToCarAdding() {
        replaceFragment(activity.supportFragmentManager, CarAddFragment.getInstance(), R.id.container, true)
    }

    fun showSaveError() {
        Toast.makeText(activity, activity.getString(R.string.fill_forms), Toast.LENGTH_SHORT).show()
    }

}