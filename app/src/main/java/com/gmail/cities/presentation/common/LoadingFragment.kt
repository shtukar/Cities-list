package com.gmail.cities.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmail.cities.R
import dagger.android.support.DaggerFragment

/**
 * LoadingFragment is the class for displaying the progress bar and dim display.
 */
class LoadingFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_loading_view, container, false)

}
