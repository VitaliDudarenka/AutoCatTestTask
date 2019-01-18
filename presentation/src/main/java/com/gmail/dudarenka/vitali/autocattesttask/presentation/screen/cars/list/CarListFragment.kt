package com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.gmail.dudarenka.vitali.autocattesttask.R
import com.gmail.dudarenka.vitali.autocattesttask.databinding.FragmentCarListBinding
import com.gmail.dudarenka.vitali.autocattesttask.presentation.base.BaseMvvmFragment
import com.gmail.dudarenka.vitali.autocattesttask.presentation.screen.cars.CarsRouter
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.rxkotlin.subscribeBy


class CarListFragment : BaseMvvmFragment<CarListViewModel, CarsRouter, FragmentCarListBinding>() {
    private var layoutManager: LinearLayoutManager? = null

    companion object {
        fun getInstance(): CarListFragment {
            return CarListFragment()
        }
    }

    override fun provideViewModel(): CarListViewModel {
        return ViewModelProviders.of(this).get(CarListViewModel::class.java)
    }

    override fun provideLayoutId(): Int = R.layout.fragment_car_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listRecyclerView.adapter = viewModel.adapter
        layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.layoutManager = layoutManager
        binding.listRecyclerView.setHasFixedSize(true)
        RxTextView.textChanges(binding.searchEditText)
                .subscribeBy {
                    viewModel.search(it.toString())
                }
    }
}