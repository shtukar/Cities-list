package com.gmail.cities.presentation.common

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract val layoutId: Int

    private var loadingContainerId = android.R.id.content
    private var fragmentManager: FragmentManager = supportFragmentManager
    private var loadingFragment: Fragment = LoadingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContentView()
    }

    protected open fun setupContentView() {
        if (layoutId != 0) setContentView(layoutId)
    }

    fun showLoading() {
        if (!loadingFragment.isAdded) {
            fragmentManager
                    .beginTransaction()
                    .add(loadingContainerId, loadingFragment)
                    .commit()
        }
    }

    fun hideLoading() {
        fragmentManager
                .beginTransaction()
                .remove(loadingFragment)
                .commitAllowingStateLoss()
    }
}
