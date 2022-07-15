package com.buzzvil.campaign

import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun avgAccess() {
        randomAccess()
    }

    fun randomAccess() {
        // time complexity O(1)
        // space complexity bad
        // accuracy 100%
        // random 함수
        val imgs: ArrayList<Int> = arrayListOf(6, 4, 2, 1)
        /*
        val totalWeight = (imgs.sum())
        val cases = ArrayList<Int>(totalWeight)
        for (i in imgs) {
            for (j in 0 until i) {
                cases.add(i)
            }
        }


        for (i in 0..100) {
            val random = Random.nextInt(totalWeight)
            println(cases[random])
        }
        // 확률로 노출 후 큐에서 제거 나머지 노출*/

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