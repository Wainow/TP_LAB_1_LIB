package com.wainow.tp_lab_1.domain

import android.content.Context

import androidx.mynewmedia4.exoplayer.ExoPlayer

interface Interactor {
    fun numFrequency(input: Collection<Int>): Any
    fun mostFrequent(input: Collection<Number>): Collection<Int>
    fun getExoPlayer(context: Context): ExoPlayer
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

    override fun getExoPlayer(context: Context): ExoPlayer {
        return ExoPlayer.Builder(context).build();
    }
}