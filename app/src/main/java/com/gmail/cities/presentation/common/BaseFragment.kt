package com.gmail.cities.presentation.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.gmail.cities.R
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    abstract val layoutId: Int

    private var loadingContainerId = android.R.id.content
    private var loadingFragment: Fragment = LoadingFragment()

    protected open val parentActivity: BaseActivity
        get() {
            if (activity !is BaseActivity) {
                throw IncorrectActivityType()
            }
            return activity as BaseActivity
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(layoutId, container, false)

    class IncorrectActivityType : RuntimeException("Fragment must be attached to BaseActivity")

    fun showLoading() {
        if (!loadingFragment.isAdded) {
            parentActivity.supportFragmentManager
                    .beginTransaction()
                    .add(loadingContainerId, loadingFragment)
                    .commit()
        }
    }

    fun hideLoading() {
        parentActivity.supportFragmentManager
                .beginTransaction()
                .remove(loadingFragment)
                .commitAllowingStateLoss()
    }

    fun showDefaultError() {
        if (isAdded) {
            Toast.makeText(context, getString(R.string.default_error), Toast.LENGTH_SHORT).show()
        }
    }

    open fun onBackPressed(): Boolean {
        if (loadingFragment.isAdded) {
            hideLoading()
        }
        return false
    }
}
