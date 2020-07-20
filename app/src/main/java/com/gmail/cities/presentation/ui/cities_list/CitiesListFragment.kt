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
import com.gmail.cities.domain.entity.CacheInfo
import com.gmail.cities.domain.entity.City
import com.gmail.cities.presentation.common.BaseFragment
import com.gmail.cities.presentation.extentions.hide
import com.gmail.cities.presentation.extentions.observe
import com.gmail.cities.presentation.extentions.onTextChanged
import com.gmail.cities.presentation.extentions.show
import com.gmail.cities.presentation.ui.city_map_details.CityMapDetailsFragment
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.fragment_cities_list.*
import javax.inject.Inject

class CitiesListFragment : BaseFragment() {

    companion object {
        const val EMPTY_STRING = ""
        const val UNEXPECTED_RESULT = -1
    }

    override val layoutId: Int = R.layout.fragment_cities_list

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: CitiesListViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(CitiesListViewModel::class.java)
    }

    private lateinit var citiesListAdapter: CitiesListAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private var citiesList = listOf<City>()
    private var cacheMap = mutableMapOf<String, CacheInfo>()
    private var previousFilterRequest = EMPTY_STRING

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observe(viewModel.getAllCitiesStatus(), ::onCities)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        showLoading()
        viewModel.getAllCities()
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
            hideKeyboard()
            parentActivity.supportFragmentManager.beginTransaction()
                    .add(R.id.container, CityMapDetailsFragment.newInstance(it.coordinates))
                    .addToBackStack(null)
                    .commit()
        }

        Observable.create(ObservableOnSubscribe<String> { subscriber ->
            etFilter.onTextChanged { subscriber.onNext(it) }
        })
                .distinctUntilChanged()
                .filter { query ->
                    val cachePair = cacheMap[query]
                    if (cachePair != null && cachePair.rangeFrom != UNEXPECTED_RESULT
                            && cachePair.rangeTo != UNEXPECTED_RESULT) {
                        onFilterResultRange(cachePair)
                        false
                    } else {
                        true
                    }
                }
                .switchMap {
                    val cachePair = cacheMap[previousFilterRequest]
                    if (cachePair != null && previousFilterRequest.isNotEmpty() &&
                            it != previousFilterRequest && it.startsWith(previousFilterRequest)) {
                        viewModel.getFilterResultRange(
                                citiesList.subList(cachePair.rangeFrom, cachePair.rangeTo), it)
                    } else {
                        viewModel.getFilterResultRange(citiesList, it)
                    }

                }
                .subscribe {
                    cacheMap[it.prefix] = it
                    onFilterResultRange(it)
                }
    }

    private fun onCities(result: ResultState<List<City>>) {
        hideLoading()
        when (result) {
            is ResultState.Success -> {
                rvCitiesList.show()
                tvNoResult.hide()
                citiesList = result.data
                citiesListAdapter.items = result.data.toMutableList()
            }
            is ResultState.Loading -> {
                rvCitiesList.hide()
                tvNoResult.show()
            }
            is ResultState.Error -> showDefaultError()
        }
    }

    private fun onFilterResultRange(result: CacheInfo) {
        if (result.rangeFrom != UNEXPECTED_RESULT || result.rangeTo != UNEXPECTED_RESULT) {
            citiesListAdapter.items.clear()
            citiesListAdapter.items.addAll(citiesList.subList(result.rangeFrom, result.rangeTo + 1))
            citiesListAdapter.notifyDataSetChanged()
            rvCitiesList.smoothScrollToPosition(0)
            rvCitiesList.show()
            tvNoResult.hide()
        } else {
            rvCitiesList.hide()
            tvNoResult.show()
        }
    }
}
