package com.gmail.cities.presentation.ui.city_map_details

import android.os.Bundle
import android.view.View
import com.gmail.cities.R
import com.gmail.cities.domain.entity.Coordinates
import com.gmail.cities.presentation.common.BaseFragment
import com.gmail.cities.presentation.extentions.doOnClick
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*

class CityMapDetailsFragment : BaseFragment() {

    companion object {
        fun newInstance(coordinates: Coordinates): CityMapDetailsFragment {
            val fragment = CityMapDetailsFragment()
            val args = Bundle()
            args.putParcelable(EXTRA_COORDINATES, coordinates)
            fragment.arguments = args
            return fragment
        }

        const val EXTRA_COORDINATES = "EXTRA_COORDINATES"
        const val MAP_ZOOM_VALUE = 12f
    }

    override val layoutId: Int = R.layout.fragment_map

    private var coordinates: Coordinates? = null

    private val mapCallback = OnMapReadyCallback { googleMap ->
        coordinates?.let { coordinates ->
            val sydney = LatLng(coordinates.lat, coordinates.lon)
            googleMap.addMarker(MarkerOptions().position(sydney))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, MAP_ZOOM_VALUE))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coordinates = arguments?.getParcelable(EXTRA_COORDINATES)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(mapCallback)

        icBack.doOnClick { parentActivity.onBackPressed() }
    }
}