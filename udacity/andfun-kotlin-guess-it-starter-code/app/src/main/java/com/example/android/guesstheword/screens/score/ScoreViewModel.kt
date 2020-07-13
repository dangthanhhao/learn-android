package com.example.android.guesstheword.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(val finalScore: Int) : ViewModel() {
    private var _score: MutableLiveData<Int> = MutableLiveData()
    val score: LiveData<Int>
        get() = _score


    init {
        _score.value = finalScore
    }

    fun eventPlayAgain() {
        _score.value = 0
    }
}