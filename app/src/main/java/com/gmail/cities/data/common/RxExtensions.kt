package com.gmail.cities.data.common

import com.gmail.cities.domain.common.ResultState
import com.gmail.cities.domain.entity.ErrorState
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//Observable
fun <T> Observable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Observable<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Observable<T>.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//Flowable
fun <T> Flowable<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Flowable<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Flowable<T>.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//Single
fun <T> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Single<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//Maybe
fun <T> Maybe<T>.applyIoScheduler() = applyScheduler(Schedulers.io())

fun <T> Maybe<T>.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun <T> Maybe<T>.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//Completable
fun Completable.applyIoScheduler() = applyScheduler(Schedulers.io())

fun Completable.applyComputationScheduler() = applyScheduler(Schedulers.computation())

private fun Completable.applyScheduler(scheduler: Scheduler) =
        subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//onError
fun <T> Single<ResultState<T>>.mapDefaultErrors(): Single<ResultState<T>> =
        this.onErrorReturn { ResultState.Error((ErrorState(0, it.message.toString())), null) }

fun <T> Observable<ResultState<T>>.mapDefaultErrors(): Observable<ResultState<T>> =
        this.onErrorReturn { ResultState.Error((ErrorState(0, it.message.toString())), null) }
