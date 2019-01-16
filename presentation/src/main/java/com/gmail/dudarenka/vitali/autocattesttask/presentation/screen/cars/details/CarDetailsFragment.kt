package com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.gmail.dudarenka.vitali.autocattesttask.R
import com.gmail.dudarenka.vitali.autocattesttask.databinding.FragmentCarDetailsBinding
import com.gmail.dudarenka.vitali.autocattesttask.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.CarsRouter


class CarDetailsFragment : BaseMvvmFragment<CarDetailsViewModel, CarsRouter, FragmentCarDetailsBinding>() {

    companion object {
        private const val ID_EXTRA = "ID_EXTRA"
        fun getInstance(login: String): CarDetailsFragment {
            val fragment = CarDetailsFragment()
            val bundle = Bundle()
            bundle.putString(ID_EXTRA, login)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun provideViewModel(): CarDetailsViewModel {
        return ViewModelProviders.of(this).get(CarDetailsViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_car_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getString(ID_EXTRA)
        if (id != null) {
            viewModel.setCarId(id)
        } else {
            router?.goBack()
        }
    }

}