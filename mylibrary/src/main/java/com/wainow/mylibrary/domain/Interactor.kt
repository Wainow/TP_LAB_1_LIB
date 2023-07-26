package com.wainow.tp_lab_1.domain

import android.annotation.SuppressLint
import android.content.Context
import androidx.media3.common.C.ASCII_NAME
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.FilteringMediaSource
import androidx.media3.exoplayer.source.MediaSource

interface Interactor {
    fun numFrequency(input: Collection<Int>): Any
    fun mostFrequent(input: Collection<Number>): Collection<Int>
    fun getExoPlayer(context: Context): ExoPlayer
    fun getSomething(): String
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
    override fun getExoPlayer(context: Context): ExoPlayer {
        val exoplayer = ExoPlayer.Builder(context).build();
        exoplayer.retry()
        return exoplayer
    }

    @SuppressLint("UnsafeOptInUsageError")
    override fun getSomething(): String {
        return ASCII_NAME
    }
}