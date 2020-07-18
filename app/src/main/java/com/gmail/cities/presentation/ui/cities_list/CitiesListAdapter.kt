package com.gmail.cities.presentation.ui.cities_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gmail.cities.R
import com.gmail.cities.domain.entity.City
import com.gmail.cities.presentation.common.BaseRecyclerAdapter
import com.gmail.cities.presentation.common.BaseRecyclerHolder
import kotlinx.android.synthetic.main.item_city_info.view.*

class CitiesListAdapter(context: Context) : BaseRecyclerAdapter<City>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_city_info, parent, false)
        return BaseRecyclerHolder(view)
    }

    override fun onBindViewHolder(holder: BaseRecyclerHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.tvCityName.text = context.getString(R.string.text_city_and_country_code,
                items[position].name, items[position].country)
        holder.itemView.tvCoordinates.text = context.getString(R.string.text_coordinates,
                items[position].coordinates.lat, items[position].coordinates.lon)
    }
}