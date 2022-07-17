package com.buzzvil.core.utils

object Utils {
    fun <T> swap(list: MutableList<T>, i: Int, j: Int) {
        val t = list[i]
        list[i] = list[j]
        list[j] = t
    }
}