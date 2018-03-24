package com.test.uklon.base

import android.app.Activity
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun Activity?.showToast(block: () -> String?) {
    this?.let { block() }?.takeIf { it.isNotEmpty() }?.also { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
}

fun Fragment.showToast(block: () -> String?) = activity.showToast(block)

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

inline fun Any.debugMessage(block: () -> String) { Log.d(javaClass.name, block()) }

inline fun <T> T?.holder(block: () -> T): T = if (this == null) block() else if (this is String && this.isEmpty()) block() else this

fun <T> Observable<T>.onMainThread(): Observable<T> = this
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.onApiThread(): Observable<T> = this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

class Utils {
}