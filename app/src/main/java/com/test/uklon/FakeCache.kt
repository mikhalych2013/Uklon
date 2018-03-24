package com.test.uklon

import java.lang.IllegalStateException
import kotlin.reflect.KClass

class FakeCache {

    companion object {
        val instance: FakeCache by lazy { FakeCache() }
    }

    private val cache: MutableMap<KClass<*>, MutableList<*>> = mutableMapOf()

    fun <T : Any> pushData(cls: KClass<T>, data: List<T>) {
        if (cache[cls] == null) cache[cls] = mutableListOf<T>()
        (cache[cls] as MutableList<T>).addAll(data)
    }

    fun <T : Any> setData(cls: KClass<T>, data: List<T>) {
        cache[cls] = data.toMutableList()
    }

    fun <T : Any> getData(cls: KClass<T>): List<T> {
        return cache[cls] as? List<T> ?: throw IllegalStateException()
    }

}