package com.buzzvil.android

import org.junit.Test

import kotlin.collections.ArrayList
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun randomAccess() {

        val imgs: ArrayList<Int> = arrayListOf(6, 4, 2, 1)

        val time = measureTimeMillis {
            // call your function here
            for (i in 0..100) {

                val totalWeight = (1..imgs.sum())
                val random = totalWeight.random()
                var last = 0
                for (i in imgs) {
                    val start = (last + 1)
                    val end = (last + i)
                    if ((start..end).contains(random)) {
                        println(i)
                    }
                    last = i
                }
            }

        }

        println("${time} millis")

    }
}