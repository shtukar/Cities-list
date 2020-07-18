package com.gmail.cities.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gmail.cities.R
import com.gmail.cities.presentation.common.BaseActivity
import com.gmail.cities.presentation.ui.cities_list.CitiesListFragment

fun Context.getMainActivityLaunchIntent(): Intent {
    val intent = Intent(this, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    return intent
}

class MainActivity : BaseActivity() {

    override val layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CitiesListFragment())
                .commit()
        }
    }
}
