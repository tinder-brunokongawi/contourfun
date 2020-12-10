package com.tinder.contourfun.util

import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Invokes the block [onDistinctChange] only when new value is distinct from old.
 */
inline fun <T> distinctObservable(
    initialValue: T,
    crossinline onDistinctChange: (newValue: T) -> Unit
): ReadWriteProperty<Any?, T> = object : ObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        if (oldValue != newValue) {
            onDistinctChange(newValue)
        }
    }
}