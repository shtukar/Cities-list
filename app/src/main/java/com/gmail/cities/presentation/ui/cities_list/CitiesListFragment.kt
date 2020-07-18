package com.gmail.cities.presentation.ui.cities_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.cities.R
import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.City
import com.gmail.cities.presentation.common.BaseFragment
import com.gmail.cities.presentation.extentions.hide
import com.gmail.cities.presentation.extentions.observe
import com.gmail.cities.presentation.extentions.onTextChanged
import com.gmail.cities.presentation.extentions.show
import kotlinx.android.synthetic.main.fragment_cities_list.*
import javax.inject.Inject

class CitiesListFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_cities_list

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CitiesListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CitiesListViewModel::class.java)
    }

    private lateinit var citiesListAdapter: CitiesListAdapter
    private lateinit var layoutManager: LinearLayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(viewModel.getAllCitiesStatus(), ::onCities)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        showLoading()
        viewModel.getAllCities(null)
    }

    @SuppressLint("CheckResult")
    private fun setupView() {
        citiesListAdapter = CitiesListAdapter(parentActivity)
        layoutManager = LinearLayoutManager(context)
        rvCitiesList.layoutManager = layoutManager
        rvCitiesList.adapter = citiesListAdapter
        rvCitiesList.addItemDecoration(
                DividerItemDecoration(
                        parentActivity,
                        DividerItemDecoration.VERTICAL
                )
        )

        citiesListAdapter.itemClicked {
//            TODO
//            parentActivity.supportFragmentManager.beginTransaction()
//                .add(R.id.container, CityMapDetailsFragment.newInstance(it))
//                .addToBackStack(null)
//                .commit()
        }

        etFilter.onTextChanged { viewModel.getAllCities(it) }
    }

    private fun onCities(result: ResultState<List<City>>) {
        hideLoading()
        when (result) {
            is ResultState.Success -> {
                rvCitiesList.show()
                tvNoResult.hide()
                citiesListAdapter.items = result.data.toMutableList()
            }
            is ResultState.Loading -> {
                rvCitiesList.hide()
                tvNoResult.show()
            }
            is ResultState.Error -> showDefaultError()
        }
    }
}
