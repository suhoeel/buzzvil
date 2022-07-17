package com.buzzvil.core.utils

object RandomizedBinarySearch {

    /*init {
        val arr = intArrayOf(2, 3, 4, 10, 40)
        val key = 10
        val result = randomizedBinarySearch (arr, 0, arr.lastIndex, key);
    }*/

    fun getRandom(x: Int, y: Int): Int {
        return (x + (Math.random() % (y - x + 1)).toInt())
    }

    fun randomizedBinarySearch(arr: IntArray, low: Int, high: Int, key: Int): Int {
        if (high >= low) {

            val mid = getRandom(low, high);


            if (arr[mid] == key)
                return mid

            if (arr[mid] > key)
                return randomizedBinarySearch(arr, low, mid - 1, key);

            return randomizedBinarySearch(arr, mid + 1, high, key);
        }

        return -1;
    }

    /*// Driver code
    public static void main(String[] args)
    {

        System.out.println((result == -1)?"Element is not present in array":
        "Element is present at index " + result);
    }*/
}