package com.wainow.mylibrary.domain

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import io.bidmachine.BidMachine
import io.bidmachine.banner.BannerRequest
import io.bidmachine.banner.BannerRequest.AdRequestListener
import io.bidmachine.models.AuctionResult
import io.bidmachine.utils.BMError

interface Interactor {
    fun numFrequency(input: Collection<Int>): Any
    fun mostFrequent(input: Collection<Number>): Collection<Int>
    fun doSomething(context: Context, sellerId: String)
}

class NumberInteractor: Interactor {
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
        val request = BannerRequest.Builder().enableHeaderBidding().build()
        request.addListener(object : AdRequestListener {
            override fun onRequestSuccess(p0: BannerRequest, p1: AuctionResult) {
                Log.d("DebugLogs", "onRequestSuccess")
            }

            override fun onRequestFailed(p0: BannerRequest, p1: BMError) {
                Log.d("DebugLogs", "onRequestFailed")
            }

            override fun onRequestExpired(p0: BannerRequest) {
                Log.d("DebugLogs", "onRequestExpired")
            }
        })
        request.request(context);
    }
}