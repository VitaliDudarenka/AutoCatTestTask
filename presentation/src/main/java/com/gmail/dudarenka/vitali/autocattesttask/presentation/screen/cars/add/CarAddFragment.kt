package com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.add

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.dudarenka.vitali.autocattesttask.R
import com.gmail.dudarenka.vitali.autocattesttask.databinding.FragmentCarAddBinding
import com.gmail.dudarenka.vitali.autocattesttask.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.CarsRouter


class CarAddFragment : BaseMvvmFragment<CarAddViewModel, CarsRouter, FragmentCarAddBinding>() {

    companion object {
        fun getInstance(): CarAddFragment {
            return CarAddFragment()
        }
    }

    override fun provideViewModel(): CarAddViewModel {
        return ViewModelProviders.of(this).get(CarAddViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_car_add

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}