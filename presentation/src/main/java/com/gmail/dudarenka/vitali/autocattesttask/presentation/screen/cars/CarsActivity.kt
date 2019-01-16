package com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.gmail.dudarenka.vitali.autocattesttask.R
import com.gmail.dudarenka.vitali.autocattesttask.databinding.ActivityCarsBinding
import com.gmail.dudarenka.vitali.autocattesttask.presentation.base.BaseMvvmActivity


private const val ID_EXTRA = "ID_EXTRA"

class CarsActivity : BaseMvvmActivity<CarsViewModel, CarsRouter, ActivityCarsBinding>() {


    override fun provideRouter(): CarsRouter {
        return CarsRouter(this)
    }

    override fun provideViewModel(): CarsViewModel {
        return ViewModelProviders.of(this).get(CarsViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.activity_cars

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.getStringExtra(ID_EXTRA) == null) {
            router.goToCarList()
        } else {
            router.goToCarDetails(intent.getStringExtra(ID_EXTRA))
        }
    }

}


