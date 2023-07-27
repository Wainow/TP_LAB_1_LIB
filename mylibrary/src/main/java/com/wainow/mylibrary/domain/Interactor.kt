package com.wainow.tp_lab_1.domain

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import io.bidmachine.BidMachine
import io.bidmachine.InitializationCallback

interface Interactor {
    fun numFrequency(input: Collection<Int>): Any
    fun mostFrequent(input: Collection<Number>): Collection<Int>
    fun doSomething(context: Context, sellerId: String)
}

class NumberInteractor: Interactor{
    override fun numFrequency(input: Collection<Int>) =
        NumMapper.map(
            input
                .groupingBy { it }
                .eachCount()
        )

    override fun mostFrequent(input: Collection<Number>): Collection<Int> {
        val maxFrequency = input.maxByOrNull { it.frequency }!!.frequency
        return input
            .filter { it.frequency == maxFrequency }
            .map { it.num }
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun doSomething(context: Context, sellerId: String) {
        BidMachine.setLoggingEnabled(true)
        BidMachine.initialize(context, sellerId) {
            Log.d("DebugLogs", "initialize")
        }
    }
}