package com.wainow.mylibrary.domain

object NumMapper {
    fun map(strings: Map<Int, Int>): ArrayList<Number> {
        val numbers = ArrayList<Number>()
        strings.forEach {
            numbers.add(
                Number(
                    num = it.key,
                    frequency = it.value
                )
            )
        }
        return numbers
    }
}