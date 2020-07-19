package com.gmail.cities

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations

open class BaseTest {
    companion object {
        @ClassRule
        @JvmField
        val schedulerRule = RxImmediateSchedulerRule()
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
    }
}